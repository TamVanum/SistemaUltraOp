package org.teambd.sgp;

import org.teambd.sgp.dao.DAOBrand;
import org.teambd.sgp.dao.DAOCategory;
import org.teambd.sgp.dao.DAOProduct;
import org.teambd.sgp.dao.MyConnection;
import org.teambd.sgp.models.Brand;
import org.teambd.sgp.models.Category;
import org.teambd.sgp.models.Product;

import java.sql.Date;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class FormUpdateProd extends JFrame{
    private JPanel pnlUpdate;
    private JPanel pnlAddData;
    private JTextField txtNameProduct;
    private JComboBox cboBrand;
    private JComboBox cboCategory;
    private JTextField txtElabDate;
    private JTextField txtExpDate;
    private JTextField txtGrossPrice;
    private JTextField txtStock;
    private JRadioButton rdbCacheroYeah;
    private JRadioButton rdbCacheroNope;
    private JTextArea txtaDescription;
    private JButton updateButton;

    private final MyConnection connection;
    private final DAOProduct daoProduct;
    private final DAOBrand daoBrand;
    private final DAOCategory daoCategory;
    private final Product product;
    private FormMain formMain;

    private DefaultComboBoxModel comboBoxModelBrands;
    private DefaultComboBoxModel comboBoxModelCategories;
    private ButtonGroup bgIsGreat;

    public FormUpdateProd(MyConnection connection, DAOProduct daoProduct, DAOBrand daoBrand,
                          DAOCategory daoCategory, Product product, FormMain formMain){
        super("Update");
        add(pnlUpdate);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(350, 500));
        setPreferredSize(new Dimension(350, 500));
        setLocationRelativeTo(null);
        setVisible(true);


        this.connection = connection;
        this.daoProduct = daoProduct;
        this.daoBrand = daoBrand;
        this.daoCategory = daoCategory;
        this.product = product;
        this.formMain = formMain;

        fillFormWithProduct();

        updateButton.setMnemonic(KeyEvent.VK_U);
        updateButton.addActionListener(this::updateProduct);
    }

    private void setComboboxes() {
        try {
            /* BRAND */
            List<Brand> brands =  daoBrand.getAll();

            comboBoxModelBrands = new DefaultComboBoxModel();
            for (Brand brand : brands) {
                comboBoxModelBrands.addElement(brand.getName());
            }

            cboBrand.setModel(comboBoxModelBrands);
            cboBrand.setSelectedIndex(product.getBrandIdFk());


            /* CATEGORY */
            List<Category> categories =  daoCategory.getAll();

            comboBoxModelCategories = new DefaultComboBoxModel();
            for (Category category : categories) {
                comboBoxModelCategories.addElement(category.getName());
            }

            cboCategory.setModel(comboBoxModelCategories);
            cboCategory.setSelectedIndex(product.getCategoryIdFk());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void fillFormWithProduct() {
        txtNameProduct.setText( product.getName() );
        txtaDescription.setText( product.getDescription() );
        setComboboxes();

        txtElabDate.setText(String.valueOf(product.getElaborationDate()));
        txtExpDate.setText(String.valueOf(product.getExpirationDate()));
        txtGrossPrice.setText(String.valueOf(product.getGrossPrice()));
        txtStock.setText(String.valueOf(product.getStock()));


        bgIsGreat = new ButtonGroup();
        bgIsGreat.add(rdbCacheroYeah);
        bgIsGreat.add(rdbCacheroNope);
        rdbCacheroYeah.setActionCommand("1");
        rdbCacheroNope.setActionCommand("0");
        rdbCacheroYeah.setSelected(false);
        rdbCacheroNope.setSelected(true);

    }

    private void updateProduct(ActionEvent actionEvent) {
        Product product = new Product();
        Brand brand = null;
        Category category = null;

        try {
            brand = daoBrand.getByText( (String) cboBrand.getSelectedItem() );
            category = daoCategory.getByText( (String) cboCategory.getSelectedItem() );


            product.setId( this.product.getId() );
            product.setName( txtNameProduct.getText() );
            product.setDescription( txtaDescription.getText() );
            product.setBrandIdFk( brand.getId() );
            product.setCategoryIdFk( category.getId() );
            product.setElaborationDate( Date.valueOf(txtElabDate.getText()) );
            product.setExpirationDate( Date.valueOf(txtExpDate.getText()) );
            product.setGrossPrice( Integer.parseInt(txtGrossPrice.getText()) );
            product.setStock( Integer.parseInt(txtStock.getText()) );
            product.setGreat(bgIsGreat.getSelection().getActionCommand().equalsIgnoreCase("1"));

            int rowsAffected = daoProduct.update(product);
            System.out.println(rowsAffected);


            formMain.refresh_table_product();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dispose();
    }
}