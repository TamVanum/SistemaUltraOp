package org.teambd.sgp;

import org.teambd.sgp.dao.*;
import org.teambd.sgp.models.Brand;
import org.teambd.sgp.models.Category;
import org.teambd.sgp.models.PriceHistory;
import org.teambd.sgp.models.Product;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import java.sql.Date;

import java.util.List;
import java.sql.SQLException;

public class FormMain extends JFrame{

    // region Components
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
    private JButton btnCategories;
    private JButton btnHistProd;
    private JTable tblHistory;
    private JTable tblMarcas;
    private JTable tblCategories;
    private JPanel pnlMarcas;
    private JPanel pnlCategoria;
    private JButton btnBrands;
    private JPanel pnlHistorial;
    private JTextField txtCategories;
    private JTextField txtBrands;
    private JPanel pnlTablas;
    private JCheckBox chkConfirmBrand;
    private JButton btnDeleteBrand;
    private JCheckBox chkConfirmCat;
    private JButton btnDeleteCat;
    private JScrollPane srcHistory;
    private JScrollPane tblBrands;
    private JScrollPane srcCategories;
    private JPanel pnlCachero;
    private JPanel pnlCacheroContent;
    private JButton btnMostrarCachero;
    private JTable table1;
    private JButton btnModifyCat;
    private JButton btnModifyBrand;

    private JButton btnActivateProd;
    private JComboBox cboActivateProd;
    // endregion

    private MyConnection connection;
    private DAOProduct daoProduct;
    private DAOBrand daoBrand;
    private DAOCategory daoCategory;
    private DAOPriceHistory daoPriceHistory;


    //models of tables
    private DefaultTableModel modelProducts;
    private DefaultTableModel modelCategories;
    private DefaultTableModel modelBrands;
    private DefaultTableModel modelHistory;

    public FormMain(MyConnection connection){
        super("Menu");
        this.connection = connection;
        add(pnlMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(900, 500));
        setPreferredSize(new Dimension(900, 500));
        setLocationRelativeTo(null);
        setVisible(true);

        /**INICIALIZA DAO's*/

        daoProduct = new DAOProduct(this.connection);
        daoBrand = new DAOBrand(this.connection);
        daoCategory = new DAOCategory(this.connection);
        daoPriceHistory = new DAOPriceHistory(this.connection);

        /** TABLA DE PRODUCTOS */
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

        try {
            refresh_table_product();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /** TABLA CATEGORIES */

        modelCategories = new DefaultTableModel();
        modelCategories.addColumn("Id");
        modelCategories.addColumn("Name");
        tblCategories.setModel(modelCategories);

        try {
            refresh_table_categories();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /** TABLA BRANDS */
        modelBrands = new DefaultTableModel();
        modelBrands.addColumn("Id");
        modelBrands.addColumn("Name");
        tblMarcas.setModel(modelBrands);

        try {
            refresh_table_brands();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /** TABLA HISTORIAL */
        modelHistory = new DefaultTableModel();
        modelHistory.addColumn("Id");
        modelHistory.addColumn("Product Name");
        modelHistory.addColumn("Old Price");
        modelHistory.addColumn("New Price");
        modelHistory.addColumn("Change Date");
        tblHistory.setModel(modelHistory);

        try {
            refresh_table_historyPrice();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        /** Boton actualizar de Category */
        btnCategories.addActionListener(this::addCategory);
        btnModifyCat.addActionListener(this::updateCategory);
        btnDeleteCat.setEnabled(false);

        btnBrands.addActionListener(this::addBrand);
        btnModifyBrand.addActionListener(this::updateBrand);
        btnDeleteBrand.addActionListener(this::deleteBrand);
        btnDeleteBrand.setEnabled(false);



        chkConfirmCat.setEnabled(false);
        chkConfirmBrand.setEnabled(false);

        chkConfirmBrand.setSelected(false);
        chkConfirmBrand.setSelected(false);

        chkConfirmBrand.addItemListener((e)-> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                btnDeleteBrand.setEnabled(true);
            } else {
                btnDeleteBrand.setEnabled(false);
            }
        });

        chkConfirmCat.addItemListener((e)-> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                btnDeleteCat.setEnabled(true);
            } else {
                btnDeleteCat.setEnabled(false);
            }
        });

        btnActualizar.setMnemonic(KeyEvent.VK_C);
        btnActualizar.addActionListener(this::updateProduct);
        btnHistProd.addActionListener(actionEvent -> {
            try {
                refresh_table_historyPrice();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


        tblCategories.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tblCategories.rowAtPoint(e.getPoint());
                if (row >= 0){
                    txtCategories.setText(String.valueOf(tblCategories.getValueAt(row , 1)));
                    if ( tblCategories.getSelectedRow() != -1 ) {
                        chkConfirmCat.setEnabled(true);
                    } else {
                        txtCategories.setText("");
                        chkConfirmCat.setEnabled(false);
                    }
                }

            }
        });

        tblMarcas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tblMarcas.rowAtPoint(e.getPoint());
                if (row >= 0){
                    txtBrands.setText(String.valueOf(tblMarcas.getValueAt(row , 1)));
                    if ( tblMarcas.getSelectedRow() != -1 ) {
                        chkConfirmBrand.setEnabled(true);
                    } else {
                        txtBrands.setText("");
                        chkConfirmBrand.setEnabled(false);
                    }
                }
            }
        });
    }

    private void deleteBrand(ActionEvent actionEvent) {
        int rowSelected = tblMarcas.getSelectedRow();
        if ( rowSelected != -1 ) {
            try {
                System.out.println((int) tblMarcas.getValueAt(rowSelected, 0));
                int rowsAffected = daoBrand.delete( (int) tblMarcas.getValueAt(rowSelected, 0) );
                System.out.println(rowsAffected);

                refresh_table_brands();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No selected row", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateBrand(ActionEvent actionEvent) {
        int rowSelected = tblMarcas.getSelectedRow();
        if (rowSelected != -1) {

            try {
                Brand brand = new Brand(
                        (int) tblMarcas.getValueAt(rowSelected, 0),
                        txtBrands.getText()
                );

                int rowsAffected = daoBrand.update(brand);
                System.out.println(rowsAffected);

                txtBrands.setText("");
                txtBrands.requestFocus();
                refresh_table_brands();
            } catch (SQLException throwables) {
                txtBrands.setText("");
                throwables.printStackTrace();
            } catch (IllegalArgumentException ex) {
                txtBrands.setText("");
                JOptionPane.showMessageDialog(this, "Empty field", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            txtBrands.setText("");
            JOptionPane.showMessageDialog(this, "No selected row", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addBrand(ActionEvent actionEvent) {
        int rowSelected = tblMarcas.getSelectedRow();
        if (rowSelected == -1) {
            Brand brand = new Brand();

            try {
                brand.setName( txtBrands.getText() );

                int rowsAffected = daoBrand.insert(brand);
                System.out.println(rowsAffected);

                txtBrands.setText("");
                txtBrands.requestFocus();
                refresh_table_brands();
            } catch (SQLException throwables) {
                txtBrands.setText("");
                throwables.printStackTrace();
            } catch (IllegalArgumentException ex) {
                txtBrands.setText("");
                JOptionPane.showMessageDialog(this, "Empty field", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            txtBrands.setText("");
        }
    }
        

    private void addCategory(ActionEvent actionEvent) {
        int rowSelected = tblCategories.getSelectedRow();
        if (rowSelected == -1) {
            Category category = new Category();

            try {
                category.setName( txtCategories.getText() );

                int rowsAffected = daoCategory.insert(category);
                System.out.println(rowsAffected);

                txtCategories.setText("");
                txtCategories.requestFocus();
                refresh_table_categories();
            } catch (SQLException throwables) {
                txtCategories.setText("");
                throwables.printStackTrace();
            } catch (IllegalArgumentException ex) {
                txtCategories.setText("");
                JOptionPane.showMessageDialog(this, "Empty field", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            txtCategories.setText("");
        }
    }

    private void updateCategory(ActionEvent actionEvent) {
        int rowSelected = tblCategories.getSelectedRow();
        if (rowSelected != -1) {

            try {
                Category category = new Category(
                        (int) tblCategories.getValueAt(rowSelected, 0),
                        txtCategories.getText()
                );

                int rowsAffected = daoCategory.update(category);
                System.out.println(rowsAffected);

                txtCategories.setText("");
                txtCategories.requestFocus();
                refresh_table_categories();
            } catch (SQLException throwables) {
                txtCategories.setText("");
                throwables.printStackTrace();
            } catch (IllegalArgumentException ex) {
                txtCategories.setText("");
                JOptionPane.showMessageDialog(this, "Empty field", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            txtCategories.setText("");
            JOptionPane.showMessageDialog(this, "No selected row", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateProduct(ActionEvent actionEvent) {
        int rowSelected = tblProducts.getSelectedRow();
        if ( rowSelected != -1 ) {

            Product product = new Product();
            Brand brand = null;
            Category category = null;

            try {
                brand = daoBrand.getByText( String.valueOf(tblProducts.getValueAt(rowSelected, 3)) );
                category = daoCategory.getByText( String.valueOf(tblProducts.getValueAt(rowSelected, 4)) );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            product.setId( Integer.parseInt( String.valueOf( tblProducts.getValueAt(rowSelected, 0) ) ) );
            product.setName( String.valueOf(tblProducts.getValueAt(rowSelected, 1)) );
            product.setDescription( String.valueOf(tblProducts.getValueAt(rowSelected, 2)) );
            product.setBrandIdFk( brand.getId() );
            product.setCategoryIdFk( category.getId() );
            product.setElaborationDate( (Date) tblProducts.getValueAt(rowSelected, 5));
            product.setExpirationDate( (Date) tblProducts.getValueAt(rowSelected, 6));
            product.setGrossPrice( Integer.parseInt( String.valueOf(tblProducts.getValueAt(rowSelected, 7)) ) );
            product.setNetPrice( Integer.parseInt( String.valueOf(tblProducts.getValueAt(rowSelected, 8)) ) );
            product.setStock( Integer.parseInt( String.valueOf(tblProducts.getValueAt(rowSelected, 9)) ) );


            SwingUtilities.invokeLater(() -> {
                new FormUpdateProd(
                        connection,
                        daoProduct,
                        daoBrand,
                        daoCategory,
                        product,
                        this
                );
            });
        } else {
            JOptionPane.showMessageDialog(this, "Dont selected row", "Warning", JOptionPane.WARNING_MESSAGE);
        }


    }


    public void refresh_table_product() throws SQLException {
        List<Product> products = daoProduct.getAll();
        if (products != null){
            int rowCount = modelProducts.getRowCount();
            for (int i = rowCount - 1; i > 0; i--) {
                modelProducts.removeRow(i);
            }

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

    public void refresh_table_categories() throws SQLException {
        List<Category> categories = daoCategory.getAll();
        if (categories != null){
            int rowCount = modelCategories.getRowCount();
            for (int i = rowCount - 1; i > 0; i--) {
                modelCategories.removeRow(i);
            }
            for (Category category : categories) {
                modelCategories.addRow(new Object[]{
                        category.getId(),
                        category.getName()
                });

            }
        }
    }

    public void refresh_table_brands() throws SQLException {
        List<Brand> brands = daoBrand.getAll();
        if (brands != null){
            int rowCount = modelBrands.getRowCount();
            for (int i = rowCount - 1; i > 0; i--) {
                modelBrands.removeRow(i);
            }
            for (Brand brand : brands) {
                modelBrands.addRow(new Object[]{
                        brand.getId(),
                        brand.getName()
                });
            }
        }
    }

    public void refresh_table_historyPrice() throws SQLException {
        List<PriceHistory> priceHistories = daoPriceHistory.getAll();
        if (priceHistories != null) {
            int rowCount = modelHistory.getRowCount();
            for (int i = rowCount - 1; i > 0; i--) {
                modelHistory.removeRow(i);
            }

            for (PriceHistory priceHistory : priceHistories) {
                modelHistory.addRow(new Object[]{
                        priceHistory.getId(),
                        daoProduct.getById(priceHistory.getProductIdFk()).getName(),
                        priceHistory.getActualPrice(),
                        priceHistory.getNewPrice(),
                        priceHistory.getUpdateDate()
                });
            }
        }
    }
}
