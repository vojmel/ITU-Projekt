package itu.panels.Components;

import itu.ClientBean;
import itu.pm;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ClientBean bean;
    
    public FriendComponent(String name, ClientBean bean) {
        super();
        this.name = name;
        this.bean = bean;
        
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
        //JOptionPane.showMessageDialog(null, "Show chat for user: "+name, "Clicked", JOptionPane.INFORMATION_MESSAGE);
        
        // todo open window with private chat
        pm mes; 
        try {
            mes = new pm(name, bean.getConnection().getLogin()); //mesaage[1] sender
            
            mes.pipes(bean.getConnection().getLogin(), bean.getParser().getRead(), bean.getParser().getWrite());
            bean.getParser().addToDocs(name, mes);
            bean.getParser().addToPms(name, mes);
        } catch (IOException ex) {
            Logger.getLogger(FriendComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
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
