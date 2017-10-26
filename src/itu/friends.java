/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author christian
 */
public class friends extends JPanel implements ActionListener {
    
    
    public friends() throws IOException
    {
       setSize(100,300);
       setLocation(500,0);
    
      
       setLayout(null);
       setVisible(true);
       
        String path = System.getProperty("user.dir");
       BufferedImage image = ImageIO.read(new File(path + "/src/itu/pozadi.png"));
       JLabel picLabel = new JLabel(new ImageIcon(image));
       add(picLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
