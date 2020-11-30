package org.teambd.sgp;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.sql.SQLException;

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
                try {
                    login = new FormLogin("Login"); // Al iniciar el programa creamos la ventana del Login
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
