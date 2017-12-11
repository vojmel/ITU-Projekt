/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu;

import itu.panels.pm;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

/**
 *
 * @author christian
 */
public class smileys extends JFrame implements ActionListener {

    JButton smiley;
    JButton smiley1;
    JButton smiley2;
    JButton smiley3;
    JButton smiley4;
    JButton smiley5;
    JTextField text;
    PrintWriter write;
    String type;
    String type2;
    int mode;
    pm pmuse;
    
    Image image = Toolkit.getDefaultToolkit().getImage("src/itu/1.png");
    Image image1 = Toolkit.getDefaultToolkit().getImage("src/itu/2.png");
    Image image2 = Toolkit.getDefaultToolkit().getImage("src/itu/3.png");
    Image image3 = Toolkit.getDefaultToolkit().getImage("src/itu/4.png");
    Image image4 = Toolkit.getDefaultToolkit().getImage("src/itu/5.png");
    Image image5 = Toolkit.getDefaultToolkit().getImage("src/itu/6.png");
    
    
    public smileys(PrintWriter p, String msg, pm personal,int mod) throws IOException {
        setSize(180, 160);
        setLocation(500, 500);
        setLayout(null);
        setBackground(Color.white);
        String path = System.getProperty("user.dir");
        URL url = getClass().getResource(path);
     

     
        write = p;
        type = msg;
   
       
        pmuse = personal;
        mode = mod;
        smiley1 = new JButton(new ImageIcon(image));
        smiley1.addActionListener(this);
        smiley1.setBounds(0, 0, 40, 40);
        smiley1.setBorder(BorderFactory.createEmptyBorder());
        smiley1.setContentAreaFilled(false);

        smiley = new JButton(new ImageIcon(image1));
        smiley.addActionListener(this);
        smiley.setBounds(60, 0, 40, 40);
        smiley.setBorder(BorderFactory.createEmptyBorder());
        smiley.setContentAreaFilled(false);

        smiley2 = new JButton(new ImageIcon(image2));
        smiley2.addActionListener(this);
        smiley2.setBounds(120, 0, 40, 40);
        smiley2.setBorder(BorderFactory.createEmptyBorder());
        smiley2.setContentAreaFilled(false);

        smiley3 = new JButton(new ImageIcon(image3));
        smiley3.addActionListener(this);
        smiley3.setBounds(0, 70, 40, 40);
        smiley3.setBorder(BorderFactory.createEmptyBorder());
        smiley3.setContentAreaFilled(false);

        smiley4 = new JButton(new ImageIcon(image4));
        smiley4.addActionListener(this);
        smiley4.setBounds(60, 70, 40, 40);
        smiley4.setBorder(BorderFactory.createEmptyBorder());
        smiley4.setContentAreaFilled(false);

        smiley5 = new JButton(new ImageIcon(image5));
        smiley5.addActionListener(this);
        smiley5.setBounds(120, 70, 40, 40);
        smiley5.setBorder(BorderFactory.createEmptyBorder());
        smiley5.setContentAreaFilled(false);

        add(smiley);
        add(smiley1);
        add(smiley2);
        add(smiley3);
        add(smiley4);
        add(smiley5);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == smiley1) {
            write.println(type + "Sx(fun)");
            if(mode == 2)
            {
                try {
                    pmuse.smileyShow("",image);
                   
                } catch (BadLocationException ex) {
                    Logger.getLogger(smileys.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            write.flush();
           
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == smiley) {
            write.println(type + "Sx(1)");
            if(mode == 2)
            {
                try {
                    pmuse.smileyShow("",image1);
                  
                } catch (BadLocationException ex) {
                    Logger.getLogger(smileys.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            write.flush();
           
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == smiley2) {
            write.println(type + "Sx(2)");
           if(mode == 2)
            {
                try {
                    pmuse.smileyShow("",image2);
                    
                } catch (BadLocationException ex) {
                    Logger.getLogger(smileys.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            write.flush();
           
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == smiley3) {
            write.println(type + "Sx(3)");
            if(mode == 2)
            {
                try {
                    pmuse.smileyShow("",image3);
                  
                } catch (BadLocationException ex) {
                    Logger.getLogger(smileys.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            write.flush();
        
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == smiley4) {
            write.println(type + "Sx(4)");
            if(mode == 2)
            {
                try {
                        pmuse.smileyShow("",image4);
                       
                    } catch (BadLocationException ex) {
                        Logger.getLogger(smileys.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            write.flush();
           
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        } else if (e.getSource() == smiley5) {
            write.println(type + "Sx(5)");
            if(mode == 2)
            {
             try {
                    pmuse.smileyShow("",image5);
                   
                } catch (BadLocationException ex) {
                    Logger.getLogger(smileys.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            write.flush();
         
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
