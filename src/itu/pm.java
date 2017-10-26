/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class pm  extends javax.swing.JFrame implements ActionListener {

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
       
                public pm(String name,String l) throws IOException
                {
                    setSize(800,400);
                    setLayout(null);

                        

                    setVisible(true);

                    login = l;
                    nm = name;
                    friends = new JLabel(name);
                    Send_text_field = new JTextField();
                    //chat_space = new JTextArea();
                    chat_space = new JTextPane();
                    send=new JButton("Send");
                    send.addActionListener(this);

                    smiley=new JButton("Smiley");
                    smiley.addActionListener(this);
                    
                    
                
                     doc = chat_space.getStyledDocument();
                    style = doc.addStyle("StyleName", null);
                    
                    Send_text_field.setBounds(40,280,500,100);
                   // chat_space.setBounds(40,20,500,250);
                    chat_space.setSize(500, 250);
                    smiley.setBounds(600,100,100,30);
                    send.setBounds(600,200,100,30);
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
                   // this.repaint();
                }
    public void paintComponent(Graphics g) {
            paintComponent(g);

        
            g.setColor(Color.red);
            g.fillRect(0, 0, 100, 100);
    
    }
    public StyledDocument docs()
    {
        return doc;
    }
    public void pipes(String user,  BufferedReader rd,PrintWriter wd)
    {
    
        read = rd;
        write = wd;
       
    }
   
    
    
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == send)
            {
                String empty = "";
                if ((Send_text_field.getText()).equals(empty)) 
                {
                    Send_text_field.setText("");
                    Send_text_field.requestFocus();
                } 
                else 
                {
                    try 
                    {

                       
                       write.println("SIC " + login + " " + nm + " /r/n " + Send_text_field.getText());
                       write.flush(); 
                       Send_text_field.setText("");
                       
                    Send_text_field.requestFocus();
                    } 
                    catch (Exception ex) 
                    {
                        //chat_space.append("Message was not sent. \n");
                    }
                    Send_text_field.setText("");
                    Send_text_field.requestFocus();
                }

                Send_text_field.setText("");
                Send_text_field.requestFocus();
            }
           else if (e.getSource() == smiley)
            {
             
                
                    
                    try {
                    smileys field = new smileys(write,"SIC " + login + " " + nm + " /r/n ");
                    field.setVisible(true);
                    
                    } catch (IOException ex) {
                    Logger.getLogger(idk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
                
                
            }
     
        }
    
    
   
    
    
}
