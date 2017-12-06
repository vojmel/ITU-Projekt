/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu.panels;

import itu.ClientBean;
import itu.ClientFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author christian
 */
public class MessengerPanel extends javax.swing.JPanel {

    // odkaz na bean
    private ClientBean bean;
    
    
    // chat vypis zprav
    JTextPane chat_space;
    JScrollPane scroll2;
    
    GridBagConstraints content;
    

    public MessengerPanel(ClientBean bean) {
        this.bean = bean;
        
        initComponents();
    }

    
    private void initComponents() {
        
        this.setBackground(Color.white);
        
        // Center object in panel
        this.setLayout(new GridBagLayout());
        content = new GridBagConstraints();
        content.gridwidth = GridBagConstraints.REMAINDER;
        content.fill = GridBagConstraints.HORIZONTAL;
        
        
        // chat okno
        chat_space = new JTextPane();
        chat_space.setSize(200, 400);
        scroll2 = new JScrollPane(chat_space);
        scroll2.setBounds(40, 40, 200, 400);
        add(chat_space, content);
        
        // message okno
        
        
        
        // pratele
        // search
        
        // jednotlivy pratele
        
    }
    
    
    //
    // Pokud dojde od serveru nessage tak se zavola tato funkce
    //
    public void serverSendMessage() {
        
        // reload peoples
        
        // reload messages
        
    }
    
    
    private void loadData() {
        
    }

    @Override
    public void show(boolean b) {
        super.show(b); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
