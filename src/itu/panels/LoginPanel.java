/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu.panels;

import itu.ClientBean;
import itu.ClientFrame;
import itu.HintTextFieldUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

/**
 *
 * @author Meluzin
 */
public class LoginPanel extends javax.swing.JPanel {
    
    // odkaz na bean
    private ClientBean bean;
    String log = "";
    String pswd = "";

    
    public LoginPanel(ClientBean bean) {
        
        this.bean = bean;
        
        // inicializace a vykresleni
        initLoginPanel();
    }
    
    private void initLoginPanel() {
        
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JPasswordField();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        
        
        jButton2.setText("Don't have an account?");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Sing in");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        
        // init component
        int width = 150;
        
        // Center object in panel
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        this.setBackground(Color.white);
        
        
        // Error
        clearErrorMessage();
        jLabel5.setForeground(new Color(174,50,50));
        add(jLabel5, gbc);
        
               
        // Login input
        jTextField2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(204, 204, 204)));
        jTextField2.setUI(new HintTextFieldUI("Login", false));
        jTextField2.setPreferredSize(new Dimension(width, 30));
        add(jTextField2, gbc);
        
        javax.swing.JPanel vypln1 = new javax.swing.JPanel();
        vypln1.setBackground(Color.white);
        vypln1.setPreferredSize(new Dimension(15, 15));
        add(vypln1, gbc);
        
        // Password input
        jTextField1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(204, 204, 204)));
        jTextField1.setUI(new HintTextFieldUI("Password", false));
        jTextField1.setPreferredSize(new Dimension(width, 30));
        jTextField1.setEchoChar('*');
        add(jTextField1, gbc);
        
        javax.swing.JPanel vypln2 = new javax.swing.JPanel();
        vypln2.setBackground(Color.white);
        vypln2.setPreferredSize(new Dimension(30, 30));
        add(vypln2, gbc);
        
        // Login btn
        jButton1.setBackground(new Color(204, 204, 204));
        jButton1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        jButton1.setForeground(new Color(102, 102, 102));
        jButton1.setPreferredSize(new Dimension(width, 30));
        add(jButton1, gbc);
        
        javax.swing.JPanel vypln3 = new javax.swing.JPanel();
        vypln3.setBackground(Color.white);
        vypln3.setPreferredSize(new Dimension(40, 40));
        add(vypln3, gbc);
        
        // Registration btn
        jButton2.setBackground(Color.WHITE);
        jButton2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        jButton2.setForeground(new Color(102, 102, 102));
        jButton2.setMargin(new Insets(30, 30, 30, 30));
        add(jButton2, gbc);
    }

    @Override
    public void show(boolean b) {
        super.show(b); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String empty = "";
        clearErrorMessage();
        
        String password = String.copyValueOf(jTextField1.getPassword());
        
        if ((password).equals(empty)) 
        {
            jTextField1.setText("");
            jTextField1.requestFocus();
            showErrorMessage("Fill all fields");
        } 
        else 
        {
            pswd =password;
            
            if ((jTextField2.getText()).equals(empty)) 
            {
                jTextField2.setText("");
                jTextField2.requestFocus();
                showErrorMessage("Fill all fields");
            } 
            else 
            {
                log=jTextField2.getText();
                
                int res = bean.getConnection().try_connect(log, pswd,1);
                
                if ( res == 1) {
                    bean.getFrame().showMessenger();
                }
                else if (res == -6) {
                    
                    showErrorMessage("Wrong login or password");
                }
                else 
                {
                    showErrorMessage("Cant log in");
                }
            }
        }
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {     
        clearErrorMessage();    
        bean.getFrame().showRegistration();
    }                                        
    
     public void showErrorMessage(String msg) {
        jLabel5.setText(msg);
        jLabel5.setVisible(true);
    }
    
    public void clearErrorMessage(){
        jLabel5.setText("");
        jLabel5.setVisible(false);
    }
                      
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPasswordField jTextField1;
    
}
