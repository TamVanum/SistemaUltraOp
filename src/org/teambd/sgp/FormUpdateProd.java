package org.teambd.sgp;

import javax.swing.*;
import java.awt.*;

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
    private JTextArea textArea1;
    private JButton updateButton;

    public FormUpdateProd(){
        super("Update");
        add(pnlUpdate);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(350, 500));
        setPreferredSize(new Dimension(350, 500));
        setLocationRelativeTo(null);
        setVisible(true);
        

    }
}