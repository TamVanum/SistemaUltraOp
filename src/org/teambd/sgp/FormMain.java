package org.teambd.sgp;

import org.teambd.sgp.dao.*;

import javax.swing.*;
import java.awt.*;

public class FormMain extends JFrame{
    private JPanel pnlMain;
    private JPanel pnlContent;
    private JTabbedPane tbdOptions;
    private JPanel pnlProductos;
    private JPanel pnlAddFields;
    private JPanel pnlAddTable;
    private JTable tbdProducts;
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

    private MyConnection connection;
    private DAOProduct daoProduct;
    private DAOBrand daoBrand;
    private DAOCategory daoCategory;
    private DAOPriceHistory daoPriceHistory;

    public FormMain(MyConnection connection){
        super("Menu");
        this.connection = connection;
        add(pnlMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(900, 500));
        setPreferredSize(new Dimension(900, 500));
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
