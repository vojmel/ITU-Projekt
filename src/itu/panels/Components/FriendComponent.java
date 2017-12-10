package itu.panels.Components;

import itu.ClientBean;
import itu.panels.pm;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    private boolean notified;
    
    private JLabel dot;
    
    public FriendComponent(String name, ClientBean bean) {
        super();
        this.name = name;
        this.bean = bean;
        notified = false;
        
        initComponents();
    }
    
    
    public boolean hasName(String inName) {
        return name.equalsIgnoreCase(inName.trim());
    }
    
    public boolean nameContains(String test) {
        return name.contains(test);
    }
    
    public void setStateNotify(boolean state) {
        notified = state;
        
        updateByState();
    }
    
    private void updateByState() {
        
        dot.setVisible(notified);
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
        
        // Dot notifikace
        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResource("dot.png"));
            
            dot = new JLabel(new ImageIcon(originalImage));
            dot.setBounds(240, 20, 16, 15);
            dot.setVisible(false);
            
            add(dot);
        } catch (IOException ex) {
            //Logger.getLogger(FriendComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        updateByState();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //JOptionPane.showMessageDialog(null, "Show chat for user: "+name, "Clicked", JOptionPane.INFORMATION_MESSAGE);
        
        // todo open window with private chat
        
        // existuje uz okno
        if (bean.getParser().isPnExists(name)) {
            bean.getParser().getPmFrame(name).setVisible(true);
        }
        else {
            pm mes; 
            mes = new pm(bean, name, bean.getConnection().getLogin()); //mesaage[1] sender

            mes.pipes(bean.getConnection().getLogin(), bean.getParser().getRead(), bean.getParser().getWrite());
            bean.getParser().addToDocs(name, mes);
            bean.getParser().addToPms(name, mes);

            mes.setVisible(true);
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
