package itu.panels.Components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

/**
 * Komponenta predstavuji jednoho cloveka v seznamu pratel
 * 
 * @author Meluzin
 */
public class FriendComponent extends javax.swing.JPanel  implements MouseListener {

    private String name;
    
    public FriendComponent(String name) {
        super();
        this.name = name;
        
        initComponents();
    }
    
    /**
     * Vytvoreni zakladnich komponent
     */
    private void initComponents() {
        
        this.setLayout(null);
        this.addMouseListener(this);
        
        JLabel friendNameText = new JLabel(name);
        friendNameText.setBounds(20, 20, 200, 20);
        
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(204, 204, 204)));
        
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR); 
        this.setCursor(cursor);
        
        this.add(friendNameText);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JOptionPane.showMessageDialog(null, "Show chat for user: "+name, "Clicked", JOptionPane.INFORMATION_MESSAGE);
        
        // todo open window with private chat
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
