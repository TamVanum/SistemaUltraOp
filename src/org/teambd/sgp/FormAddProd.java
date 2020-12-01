package org.teambd.sgp;

import javax.swing.*;
import java.awt.*;

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

    public FormAddProd(){
        super("Add");
        add(pnlAgregar);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(350, 500));
        setPreferredSize(new Dimension(900, 500));
        setLocationRelativeTo(null);
        setVisible(true);


    }
}
