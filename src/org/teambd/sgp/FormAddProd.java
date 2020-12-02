package org.teambd.sgp;

import org.teambd.sgp.dao.*;
import org.teambd.sgp.models.Brand;
import org.teambd.sgp.models.Category;
import org.teambd.sgp.models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class FormAddProd extends JFrame{
    private JPanel pnlAgregar;
    private JTextField txtNameProduct;
    private JComboBox cboBrand;
    private JComboBox cboCategory;
    private JTextField txtElabDate;
    private JTextField txtExpDate;
    private JTextField txtGrossPrice;
    private JTextField txtStock;
    private JRadioButton rdbCacheroYeah;
    private JRadioButton rdbCacheroNope;
    private JPanel pnlAddData;
    private JTextArea txtareaDescription;
    private JButton btnAdd;


    private final MyConnection connection;
    private final DAOProduct daoProduct;
    private final DAOBrand daoBrand;
    private final DAOCategory daoCategory;
    private FormMain formMain;

    private DefaultComboBoxModel comboBoxModelBrands;
    private DefaultComboBoxModel comboBoxModelCategories;
    private ButtonGroup bgIsGreat;




    public FormAddProd(MyConnection connection, DAOProduct daoProduct, DAOBrand daoBrand,
                       DAOCategory daoCategory, FormMain formMain){
        super("Add");
        add(pnlAgregar);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(350, 500));
        setPreferredSize(new Dimension(900, 500));
        setLocationRelativeTo(null);
        setVisible(true);

        //color de fondo
        pnlAgregar.setBackground(Color.decode("#c7c7c7"));
        pnlAddData.setBackground(Color.decode("#ffffff"));

        //colore del boton

        setVisible(true);

        btnAdd.setBackground(Color.decode("#9E9E9E"));
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        this.connection = connection;
        this.daoProduct = daoProduct;
        this.daoBrand = daoBrand;
        this.daoCategory = daoCategory;
        this.formMain = formMain;



        setComboboxes();

        btnAdd.addActionListener(this::addProduct);





    }

    private void setComboboxes() {
        try {
            /* BRAND */
            java.util.List<Brand> brands =  daoBrand.getAll();

            comboBoxModelBrands = new DefaultComboBoxModel();
            for (Brand brand : brands) {
                comboBoxModelBrands.addElement(brand.getName());
            }

            cboBrand.setModel(comboBoxModelBrands);


            /* CATEGORY */
            List<Category> categories =  daoCategory.getAll();

            comboBoxModelCategories = new DefaultComboBoxModel();
            for (Category category : categories) {
                comboBoxModelCategories.addElement(category.getName());
            }

            cboCategory.setModel(comboBoxModelCategories);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void addProduct(ActionEvent actionEvent) {
        Product product = new Product();

        bgIsGreat = new ButtonGroup();
        bgIsGreat.add(rdbCacheroYeah);
        bgIsGreat.add(rdbCacheroNope);

        rdbCacheroYeah.setActionCommand("1");
        rdbCacheroNope.setActionCommand("0");
        rdbCacheroYeah.setSelected(false);
        rdbCacheroNope.setSelected(true);


        product.setName(txtNameProduct.getText());
        product.setDescription(txtareaDescription.getText());
        try {
            product.setBrandIdFk(daoBrand.getByText(String.valueOf(cboBrand.getSelectedItem())).getId());
            product.setCategoryIdFk(daoCategory.getByText(String.valueOf(cboCategory.getSelectedItem())).getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        product.setElaborationDate(Date.valueOf(txtElabDate.getText()));
        product.setExpirationDate(Date.valueOf(txtExpDate.getText()));
        product.setGrossPrice(Integer.parseInt(txtGrossPrice.getText()));
        product.setStock(Integer.parseInt(txtStock.getText()));
        product.setGreat(Boolean.parseBoolean(bgIsGreat.getSelection().getActionCommand()));


        int rowsAffected = 0;
        try {
            rowsAffected = daoProduct.insert(product);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(rowsAffected);


        try {
            formMain.refresh_table_product();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
