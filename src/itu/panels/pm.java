/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu.panels;

import itu.ClientBean;
import itu.HintTextFieldUI;
import itu.idk;
import itu.smileys;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import static jdk.nashorn.internal.objects.NativeDebug.getClass;

/**
 *
 * @author christian
 */
public class pm extends javax.swing.JFrame implements ActionListener, FocusListener,  WindowListener,
                                            WindowFocusListener,
                                            WindowStateListener  {

    ClientBean bean;
    
    
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    Boolean isConnected = false;

    Socket sock;
    BufferedReader read;
    PrintWriter write;

    JScrollPane scroll;

    JTextPane chat_space;
    StyledDocument doc;
    SimpleAttributeSet keyWord = new SimpleAttributeSet();
    Style style;

    String path = System.getProperty("user.dir");
    URL url = getClass().getResource(path);
    Image image = Toolkit.getDefaultToolkit().getImage("src/itu/1.jpg");
    // BufferedImage image = ImageIO.read(new File(path + "/src/itu/1.jpg"));

    JButton send;
    JButton smiley;
    JTextField Send_text_field;

    JLabel friends;

    String login;
    String nm;
    
    
    SimpleAttributeSet messageStyle;
    SimpleAttributeSet senderStyle;
    SimpleAttributeSet senderMe;
    
    JScrollPane scroll2;
    
    
    javax.swing.JPanel content;

    public pm(ClientBean bean, String name, String l) {
        
        this.bean = bean;
        
        setSize(800, 600);
        setResizable(false);
        setLayout(null);

        // Viditelnost je ze zacatku fallse, nechceme ho hnedka videt
        setVisible(false);
        
        

        login = l;
        nm = name;
        
        this.setTitle("Chat with: "+nm);
        
        /*
        friends = new JLabel(name);
        Send_text_field = new JTextField();
        //chat_space = new JTextArea();
        chat_space = new JTextPane();
        send = new JButton("Send");
        send.addActionListener(this);

        smiley = new JButton("Smiley");
        smiley.addActionListener(this);

        doc = chat_space.getStyledDocument();
        style = doc.addStyle("StyleName", null);

        Send_text_field.setBounds(40, 280, 500, 100);
        // chat_space.setBounds(40,20,500,250);
        chat_space.setSize(500, 250);
        smiley.setBounds(600, 100, 100, 30);
        send.setBounds(600, 200, 100, 30);
        friends.setBounds(0, 0, 100, 20);
        scroll = new JScrollPane(chat_space);
        scroll.setBounds(0, 0, 500, 250);

        this.add(Send_text_field);
        this.add(chat_space);
        this.add(smiley);
        this.add(send);
        this.add(friends);
        this.add(scroll);

                  //  ListenThread();
        this.repaint();
        */
        
        initComponents();
        
        // set listeners
        addWindowListener(this);
        addWindowFocusListener(this);
        addWindowStateListener(this);
    }
    
    
    private void initComponents() {
        this.setLayout(null);
        this.setBackground(Color.red);
        
        content = new JPanel();
        content.setLayout(null);
        content.setBounds(0, 0, 800, 600);
        content.setBackground(Color.white);
        this.add(content);
        
        // chat okno
        chat_space = new JTextPane();
        chat_space.setSize(200, 400);
        scroll2 = new JScrollPane(chat_space);
        scroll2.setBounds(20, 20, 770, 470);
        //chat_space.setBackground(Color.red);
       // chat_space.setBounds(20, 20, 770, 470);
        scroll2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(204, 204, 204)));
        content.add(scroll2);
       // content.add(chat_space);
        

        
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
        obalMessage.setBounds(0, 470+20, 800, 60);
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
        smiley.setBounds(620, 18, 31, 32);
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
        send.setBounds(670, 18, 100, 30);
        send.setBackground(Color.white);
        send.setForeground(new Color(102, 163, 255));
        send.addActionListener(this);
        obalMessage.add(send);
        
        content.add(obalMessage);
    }
    
    public Style st()
    {
        return style;
    }
    
    public JTextPane chat()
    {
        return chat_space;
    }
    
     public void smileyShow(String prt,  Image image) throws BadLocationException {
        
     
           

     

            
                
                doc.insertString(doc.getLength(), login, senderMe);
               
                StyleConstants.setIcon(style, new ImageIcon(image));
                doc.insertString(doc.getLength(), "ignoring", style); 
                doc.insertString(doc.getLength(), "\n ", senderMe);
    }
    
    
    

    public void paintComponent(Graphics g) {
        paintComponent(g);

        g.setColor(Color.red);
        g.fillRect(0, 0, 100, 100);

    }

    public StyledDocument docs() {
        return doc;
    }

    public void pipes(String user, BufferedReader rd, PrintWriter wd) {

        read = rd;
        write = wd;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send) {
            String empty = "";
            if ((Send_text_field.getText()).equals(empty)) {
                Send_text_field.setText("");
                Send_text_field.requestFocus();
            } else {
                try {

                    write.println("SIC " + login + " " + nm + " /r/n " + Send_text_field.getText());
                    doc.insertString(doc.getLength(),login + " " + Send_text_field.getText() + "\n", senderMe);
                     chat_space.setCaretPosition(chat_space.getDocument().getLength());
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
                smileys field = new smileys(write, "SIC " + login + " " + nm + " /r/n ",this,2);
                field.setVisible(true);

            } catch (IOException ex) {
                Logger.getLogger(idk.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // Zrusni upozorneni u naseho
        bean.getFriendsList().notifyOnFriend(nm, false);
    }

    @Override
    public void focusLost(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        bean.getParser().deltePmFrame(nm);
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        bean.getFriendsList().notifyOnFriend(nm, false);
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
