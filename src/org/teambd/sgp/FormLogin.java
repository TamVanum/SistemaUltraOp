package org.teambd.sgp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormLogin extends JFrame{
    private JPanel pnlLogin;
    private JTextField txtUser;
    private JPasswordField pswPass;
    private JLabel lblUser;
    private JLabel lblPass;
    private JButton btnLoggin;
    private JPanel pnlLoginContent;


    public FormLogin(String formLogin) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super("login");
        add(pnlLogin);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(300, 350));
        setPreferredSize(new Dimension(300, 350));
        setLocationRelativeTo(null);

        //color de fondo
        pnlLogin.setBackground(Color.decode("#455A64"));
        pnlLoginContent.setBackground(Color.decode("#607D8B"));

        //colore del boton
        btnLoggin.setBackground(Color.decode("#9E9E9E"));
        setVisible(true);


        btnLoggin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //codigo unu

            }
        });




    }
}
