/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu;

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
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

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

    public smileys(PrintWriter p, String msg) throws IOException {
        setSize(400, 200);
        setLocation(500, 500);
        setLayout(null);
        String path = System.getProperty("user.dir");
        URL url = getClass().getResource(path);
        Image image = Toolkit.getDefaultToolkit().getImage("src/itu/1.jpg");

       // BufferedImage image = ImageIO.read(new File(path + "/src/itu/pozadi.png"));
        write = p;
        type = msg;

        smiley1 = new JButton(new ImageIcon(image));
        smiley1.addActionListener(this);
        smiley1.setBounds(0, 0, 40, 40);
        smiley1.setBorder(BorderFactory.createEmptyBorder());
        smiley1.setContentAreaFilled(false);

        smiley = new JButton(new ImageIcon(image));
        smiley.addActionListener(this);
        smiley.setBounds(60, 0, 40, 40);
        smiley.setBorder(BorderFactory.createEmptyBorder());
        smiley.setContentAreaFilled(false);

        smiley2 = new JButton(new ImageIcon(image));
        smiley2.addActionListener(this);
        smiley2.setBounds(120, 0, 40, 40);
        smiley2.setBorder(BorderFactory.createEmptyBorder());
        smiley2.setContentAreaFilled(false);

        smiley3 = new JButton(new ImageIcon(image));
        smiley3.addActionListener(this);
        smiley3.setBounds(0, 100, 40, 40);
        smiley3.setBorder(BorderFactory.createEmptyBorder());
        smiley3.setContentAreaFilled(false);

        smiley4 = new JButton(new ImageIcon(image));
        smiley4.addActionListener(this);
        smiley4.setBounds(60, 100, 40, 40);
        smiley4.setBorder(BorderFactory.createEmptyBorder());
        smiley4.setContentAreaFilled(false);

        smiley5 = new JButton(new ImageIcon(image));
        smiley5.addActionListener(this);
        smiley5.setBounds(120, 100, 80, 40);
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
            System.out.format("niggers1");
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == smiley) {
            write.println(type + "Sx(1)");
            System.out.format("niggers");
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == smiley2) {
            write.println(type + "Sx(2)");
            System.out.format("niggers2");
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == smiley3) {
            write.println(type + "Sx(3)");
            System.out.format("niggers3");
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == smiley4) {
            write.println(type + "Sx(4)");
            System.out.format("niggers4");
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        } else if (e.getSource() == smiley5) {
            write.println(type + "Sx(5)");
            System.out.format("niggers5");
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
