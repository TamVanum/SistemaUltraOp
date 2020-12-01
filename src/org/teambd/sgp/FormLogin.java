package org.teambd.sgp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormLogin extends JFrame{
    private JPanel pnlLogin;
    private JTextField txtUser;
    private JPasswordField pswPass;
    private JLabel lblUser;
    private JLabel lblPass;
    private JButton btnLoggin;
    private JPanel pnlLoginContent;


    public FormLogin(){
        super("login");
        add(pnlLogin);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(300, 350));
        setPreferredSize(new Dimension(300, 350));
        setLocationRelativeTo(null);

        //color de fondo
        pnlLogin.setBackground(Color.decode("#c7c7c7"));
        pnlLoginContent.setBackground(Color.decode("#ffffff"));

        //colore del boton

        setVisible(true);

        btnLoggin.setBackground(Color.decode("#9E9E9E"));
        btnLoggin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnLoggin.setBackground(Color.decode("#9E9E9E"));
                btnLoggin.setForeground(Color.decode("#000000"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnLoggin.setBackground(Color.decode("#9E9E9E"));
                btnLoggin.setForeground(Color.decode("#000000"));
            }
        });




        btnLoggin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //codigo unu

            }
        });
    }
}
