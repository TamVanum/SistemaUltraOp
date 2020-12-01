package org.teambd.sgp;

import org.teambd.sgp.dao.DAOUser;
import org.teambd.sgp.dao.MyConnection;
import org.teambd.sgp.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class FormLogin extends JFrame{
    private JPanel pnlLogin;
    private JTextField txtUser;
    private JPasswordField pswPass;
    private JLabel lblUser;
    private JLabel lblPass;
    private JButton btnLoggin;
    private JPanel pnlLoginContent;

    private MyConnection connection;

    public FormLogin(MyConnection connection){
        super("login");
        this.connection = connection;
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
                String username = txtUser.getText();
                char[] charPassword = pswPass.getPassword();
                String password = new String(charPassword);

                if ( !username.isBlank() && !password.isBlank() ) {
                    DAOUser daoUser = new DAOUser(connection);
                    try {
                        User user = daoUser.login(username, password);
                        if ( user != null ) {
                            SwingUtilities.invokeLater(() -> {
                                new FormMain(connection);
                            });
                        } else {
                            JOptionPane.showMessageDialog(null, "User not found in db", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                } else {
                   JOptionPane.showMessageDialog(null, "Empty Fields", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
