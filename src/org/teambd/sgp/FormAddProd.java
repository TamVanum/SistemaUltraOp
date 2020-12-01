package org.teambd.sgp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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



    }
}
