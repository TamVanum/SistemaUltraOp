package org.teambd.sgp;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        System.out.println("Hola Mundo tiernesito");

        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormLogin login = null;
                /**
                try {
                    login = new FormLogin(""); // Al iniciar el programa creamos la ventana del Login
                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(null, "Error de Conexion: " + throwables.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "No se encuentran los drivers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
                 */
            }
        });

        new FormMain();
        new FormAddProd();
        new FormUpdateProd();

    }


}
