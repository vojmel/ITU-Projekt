/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu.panels;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import itu.HintTextFieldUI;
import itu.panels.Components.FriendComponent;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Meluzin
 */
public class FriendsPanel extends javax.swing.JPanel {
    
    private double sirka = 300;
    
    private JPanel scrollPanel;
    private JTextField searchTextField;
   
    
    public FriendsPanel() {
        super();
        initComponents();
    }
    
    /**
     * Vytvoreni zakladnich komponent
     */
    private void initComponents() {

        show(true);
        
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(((int) (1000-sirka)), 0,((int) sirka), 800);
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));
        
        // Search
        JPanel search = new JPanel();
        search.setBackground(Color.white);
        search.setLayout(null);
        search.setBounds(0, 0, ((int) sirka)-5, 50);
        search.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, new Color(204, 204, 204)));
        add(search);
        
        searchTextField = new JTextField();
        searchTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(204, 204, 204)));
        searchTextField.setBackground(new Color(240, 240, 240));
        searchTextField.setBounds(10, 10, ((int) sirka)-25, 30);
        searchTextField.setUI(new HintTextFieldUI("Search", true, new Color(150, 150, 150)));
        search.add(searchTextField);
        
        
        
        // Panel pro pratele
        scrollPanel = new JPanel();
        //scrollPanel.setBackground(Color.blue);
        scrollPanel.setLayout(null);
        scrollPanel.setPreferredSize(new Dimension(((int) sirka), getNumberOfPeople()*50));
        
        // Vykresleni vsech pratel
        paintFriends();
        
        // Scroll k panelu pro pratele
        JScrollPane scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 50, ((int) sirka)-5, 715);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, new Color(204, 204, 204)));
        scrollPane.setBackground(Color.red);
        
       
        add(scrollPane);
    }
    
    private void paintFriends() {
        
        scrollPanel.removeAll();
        
        for (int i = 0; i < getNumberOfPeople(); i++) {
            
            // todo NAME
            JPanel pnIn = new FriendComponent(getNameOf(i));
            pnIn.setBounds(0, i*60, ((int) sirka), 60);
            
            scrollPanel.add(pnIn);
        }
        scrollPanel.repaint();
    }
    
    
    
    private int getNumberOfPeople() {
       // pocet pratel
        return 200;
    }
    
    
    private String getNameOf(int index) {
        // Jmeno na indexu
        return Integer.toString(index);
    }
    
    
   
    
    public void setNewPeople(ArrayList<String> newList) {
        
    }
    
    
    
    
}
