package org.teambd.sgp;

import org.teambd.sgp.dao.*;
import org.teambd.sgp.models.Product;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.sql.SQLException;

public class FormMain extends JFrame{
    private JPanel pnlMain;
    private JPanel pnlContent;
    private JTabbedPane tbdOptions;
    private JPanel pnlProductos;
    private JPanel pnlAddFields;
    private JPanel pnlAddTable;
    private JTable tblProducts;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnCategorias;
    private JButton btnHistProd;
    private JTable tblHistorial;
    private JTable tblMarcas;
    private JTable tblCategoria;
    private JPanel pnlMarcas;
    private JPanel pnlCategoria;
    private JButton btnMarcas;
    private JPanel pnlHistorial;
    private JTextField txtCategorias;
    private JTextField txtMarcas;
    private JPanel pnlTablas;
    private JCheckBox chkConfirmBrand;
    private JButton btnDeleteBrand;
    private JCheckBox chkConfirmCat;
    private JButton btnDeleteCat;
    private JScrollPane tbdHistory;
    private JScrollPane tbdBrands;
    private JScrollPane tbdCategories;
    private JPanel pnlCachero;
    private JPanel pnlCacheroContent;
    private JButton btnMostrarCachero;
    private JTable table1;

    private MyConnection connection;
    private DAOProduct daoProduct;
    private DAOBrand daoBrand;
    private DAOCategory daoCategory;
    private DAOPriceHistory daoPriceHistory;


    //---
    private DefaultTableModel modelProducts;

    public FormMain(MyConnection connection){
        super("Menu");
        this.connection = connection;
        add(pnlMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(900, 500));
        setPreferredSize(new Dimension(900, 500));
        setLocationRelativeTo(null);
        setVisible(true);




        modelProducts = new DefaultTableModel();
        modelProducts.addColumn("Id");
        modelProducts.addColumn("Name");
        modelProducts.addColumn("Description");
        modelProducts.addColumn("Brand");
        modelProducts.addColumn("Category");
        modelProducts.addColumn("Elaboration_Date");
        modelProducts.addColumn("Expiration_Date");
        modelProducts.addColumn("Gross_Price");
        modelProducts.addColumn("Net_Price");
        modelProducts.addColumn("Stock");
        tblProducts.setModel(modelProducts);

        daoProduct = new DAOProduct(this.connection);
        daoBrand = new DAOBrand(this.connection);
        daoCategory = new DAOCategory(this.connection);

        try {
            refresh_table();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void refresh_table() throws SQLException {
        List<Product> products = daoProduct.getAll();
        if (products != null){
            for (Product product : products) {
                modelProducts.addRow(new Object[]{
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        daoBrand.getById(product.getBrandIdFk()).getName(),
                        daoCategory.getById(product.getCategoryIdFk()).getName(),
                        product.getElaborationDate(),
                        product.getExpirationDate(),
                        product.getGrossPrice(),
                        product.getNetPrice(),
                        product.getStock()
                });
            }
        }

    }
}
