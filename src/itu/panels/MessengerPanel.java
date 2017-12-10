/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu.panels;

import itu.ClientBean;
import itu.ClientFrame;
import itu.HintTextFieldUI;
import itu.idk;
import itu.smileys;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.BorderFactory;
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
public class MessengerPanel extends javax.swing.JPanel implements ActionListener  {

    // odkaz na bean
    private ClientBean bean;
    //pipes
    Socket sock;
    BufferedReader read;
    PrintWriter write;
    
    // chat vypis zprav
    
    StyledDocument doc;
    JTextPane chat_space;
    JScrollPane scroll2;
    Style style;
    SimpleAttributeSet messageStyle;
    SimpleAttributeSet senderStyle;
    SimpleAttributeSet senderMe;
    
    JButton send;
    JButton smiley;
    JTextField Send_text_field;
    String login;
    String nm ="";
     private JScrollPane scrollPane;
    
    javax.swing.JPanel content;
    

    public MessengerPanel(ClientBean bean) {
        this.bean = bean;
        
        
        
        initComponents();
    }

    
    private void initComponents() {
        
        this.setLayout(null);
        
        content = new JPanel();
        //content.setBackground(Color.BLUE);
        content.setBounds(0, 0, 1000, 800);
        
        this.setBackground(Color.white);
        //this.add(content);
        
        // chat okno
        chat_space = new JTextPane();
        chat_space.setSize(200, 400);
        scroll2 = new JScrollPane(chat_space);
        scroll2.setBounds(10, 1, 690, 699);
        
       chat_space.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(204, 204, 204)));
       scroll2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(204, 204, 204)));
        	
        //chat_space.setBackground(Color.red);
       // chat_space.setBounds(20, 20, 670, 670);
        //add(chat_space);
        add(scroll2);
        
        

        
        doc = chat_space.getStyledDocument();
        chat_space.setEditable(false);
        
        style = doc.addStyle("StyleName", null);
        
	
        
        // Odesilate style
        senderStyle = new SimpleAttributeSet();
        StyleConstants.setLeftIndent(senderStyle, 20);
        StyleConstants.setFirstLineIndent(senderStyle, -20);
        StyleConstants.setForeground(senderStyle, new Color(150, 150, 150));
        StyleConstants.setFontSize(senderStyle, 15);
        
        senderMe = new SimpleAttributeSet();
        StyleConstants.setLeftIndent(senderMe, 20);
        StyleConstants.setFirstLineIndent(senderMe, -20);
        StyleConstants.setForeground(senderMe, new Color(150, 150, 150));
        StyleConstants.setFontSize(senderMe, 15);
        
        // Message style
        messageStyle = new SimpleAttributeSet();
        StyleConstants.setLeftIndent(messageStyle, 20);
        StyleConstants.setFirstLineIndent(messageStyle, -20);
        StyleConstants.setForeground(messageStyle, Color.black);
        StyleConstants.setFontSize(senderStyle, 18);
        
        
        
        
        
        // Message
        JPanel obalMessage = new JPanel();
        obalMessage.setLayout(null);
        obalMessage.setBounds(0, 680+20, 700, 60);
        obalMessage.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(204, 204, 204)));
        obalMessage.setBackground(Color.white);
        
        Send_text_field = new JTextField();
        Send_text_field.setBounds(20, 2, 500, 60);
        Send_text_field.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(204, 204, 204)));
        Send_text_field.setBackground(Color.white);
        
        Send_text_field.setUI(new HintTextFieldUI("Write message", false, new Color(150, 150, 150)));
  
        obalMessage.add(Send_text_field);
        
        // Smile btn
        smiley = new JButton("");
        smiley.setBounds(520, 18, 31, 32);
        smiley.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(204, 204, 204)));
        smiley.setBackground(Color.white);
        smiley.addActionListener(this);
        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResource("t.png"));
            //int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            //BufferedImage resizeImagePng = resizeImage(originalImage, type);
            smiley.setIcon(new ImageIcon(originalImage));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        obalMessage.add(smiley);
        
        // Send btn
        send = new JButton("Send message");
        send.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(204, 204, 204)));
        send.setBounds(570, 18, 100, 30);
        send.setBackground(Color.white);
        send.setForeground(new Color(102, 163, 255));
        send.addActionListener(this);
        obalMessage.add(send);
        
        add(obalMessage);
             
        // pratele
        FriendsPanel friends = bean.getFriendsList();
        add(friends);
        
        
        this.setBounds(0, 0, 1000, 800);
        //this.setBackground(Color.GREEN);
        
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));
        this.repaint();
        
    }
    
    //
    // Pokud dojde od serveru nessage tak se zavola tato funkce
    //
    public void serverSendMessage(String prt, int mode, Image image) throws BadLocationException {
        
        // Zprava
        if (mode == 1)
        {
            doc.insertString(doc.getLength(), prt, messageStyle);
            chat_space.setCaretPosition(chat_space.getDocument().getLength());
        }
          
        // smile
        else if (mode == 2) {

           StyleConstants.setIcon(style, new ImageIcon(image));
           doc.insertString(doc.getLength(), "ignoring", style);

        }
        // odesilatel
        else if (mode == 3) {

            if (prt.equalsIgnoreCase(login)) {
                
                doc.insertString(doc.getLength(), prt, senderMe);
                doc.insertString(doc.getLength(), "\n  ", senderMe);
            } 
            else {
                
                doc.insertString(doc.getLength(), prt, senderStyle);
                doc.insertString(doc.getLength(), "\n   ", senderStyle);
            }
        }
    }
    
    public void setName(String name)
    {
        login = name;
    }
    
    public void serverSendPersonalMessage(String prt, int mode,Image image) throws BadLocationException {
        
         if (mode == 1)
            doc.insertString(doc.getLength(), prt, null);
         
         else if (mode == 2)
         {
             
            StyleConstants.setIcon(style, new ImageIcon(image));
            doc.insertString(doc.getLength(), "ignoring", style);
        
         }
         
        // reload peoples
        
        // reload messages
        
    }
    
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send) {
            String empty = "";
            if ((Send_text_field.getText()).equals(empty)) {
               // write.println("MUC " + login + " /r/n " + "pfff");
                Send_text_field.setText("");
                Send_text_field.requestFocus();
            } else {
                try {
                    
                     write.println("MUC " + login + " /r/n " + Send_text_field.getText());
                   //  write.println("MUC " + login + " /r/n " + "pfff");
                   
                    write.flush();
                    Send_text_field.setText("");

                    Send_text_field.requestFocus();
                } catch (Exception ex) {
                    //chat_space.append("Message was not sent. \n");
                }
                Send_text_field.setText("");
                Send_text_field.requestFocus();
            }

            Send_text_field.setText("");
            Send_text_field.requestFocus();
        } else if (e.getSource() == smiley) {


            try {
                smileys field = new smileys(write, "MUC " + login + " /r/n ",null,1);
                    
                field.setVisible(true);

            } catch (IOException ex) {
                Logger.getLogger(idk.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
     
    public void pipes(String user, BufferedReader rd, PrintWriter wd) {

        read = rd;
        write = wd;

    }
    
    
    private void loadData() {
        
    }

    @Override
    public void show(boolean b) {
        super.show(b); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
