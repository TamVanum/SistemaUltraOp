package org.teambd.sgp;

import com.formdev.flatlaf.FlatIntelliJLaf;
import org.teambd.sgp.dao.MyConnection;

import javax.swing.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args){
        System.out.println("Hola Mundo tiernesito");

        String passwordSantiago = "1324";
        String passwordGaston = "";

        try {
            MyConnection connection = new MyConnection(
                    "localhost",
                    "inventary_stock",
                    "root",
                    passwordSantiago
            );

            try {
                UIManager.setLookAndFeel( new FlatIntelliJLaf());
            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FormLogin login = new FormLogin(connection);
                }
            });

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Drivers not found", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, "Connection failed", "Error", JOptionPane.ERROR_MESSAGE);
            throwables.printStackTrace();
        }

    }


}
