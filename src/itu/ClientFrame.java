/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu;

//

import itu.panels.RegistrationPanel;
import itu.panels.MessengerPanel;
import itu.panels.LoginPanel;
import itu.panels.pm;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

//  Okno aplikace
//
public class ClientFrame extends JFrame  implements WindowListener,
                                            WindowFocusListener,
                                            WindowStateListener{
    
    private JPanel  content;
    private GridBagConstraints container;
    private ClientBean bean;
    
    
    ClientFrame() {
        
        bean = new ClientBean(this);
        
        // set layout
        initComponents();
        
        showLogin();
        //showMessenger();
        
    }
    
    private void initComponents() {
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        //this.setLayout(new GridBagLayout());
        container = new GridBagConstraints();
        container.gridwidth = GridBagConstraints.REMAINDER;
        container.fill = GridBagConstraints.HORIZONTAL;
        
        this.getContentPane().setBackground(Color.white);

        this.setPreferredSize(new Dimension(1000, 800));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        
        // set listeners
        addWindowListener(this);
        addWindowFocusListener(this);
        addWindowStateListener(this);
    }
    
    
    
    // zobrazeni login
    public void showLogin(){
        
        this.setLayout(new GridBagLayout());
        add(bean.getLogin(), container);
        bean.getLogin().show(true);
        bean.getRegistration().show(false);
        bean.getMessenger().show(false);
    }
    
    // zobrazeni registrace
    public void showRegistration() {
        
        this.setLayout(new GridBagLayout());
        add(bean.getRegistration(), container);
        bean.getRegistration().show(true);
        bean.getLogin().show(false);
        bean.getMessenger().show(false);
    }
    
    public void showMessenger() {
        
        
        this.setLayout(null);
        
        add(bean.getMessenger());
        bean.getLogin().show(false);
        bean.getRegistration().show(false);
        bean.getMessenger().show(true);
    }
    
    
    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // log off
        bean.getConnection().disconect();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
