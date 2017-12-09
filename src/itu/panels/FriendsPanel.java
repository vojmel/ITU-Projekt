/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu.panels;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import itu.ClientBean;
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
    
    private ClientBean bean;
    
    private double sirka = 300;
    
    private JPanel scrollPanel;
    private JTextField searchTextField;
    private ArrayList<String> people;
   
    
    public FriendsPanel(ClientBean bean) {
        super();
        people = new ArrayList();
        this.bean = bean;
        
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
        searchTextField.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 0, new Color(240, 240, 240)));
        searchTextField.setBackground(new Color(240, 240, 240));
        searchTextField.setBounds(10, 10, ((int) sirka)-25, 30);
        searchTextField.setUI(new HintTextFieldUI("   Search", false, new Color(150, 150, 150)));
        search.add(searchTextField);
        
        
        
        // Panel pro pratele
        scrollPanel = new JPanel();
        scrollPanel.setLayout(null);
        scrollPanel.setPreferredSize(new Dimension(((int) sirka), getNumberOfPeople()*50));
        scrollPanel.setBackground(new Color(240, 240, 240));
        
        // Vykresleni vsech pratel
        paintFriends();
        
        // Scroll k panelu pro pratele
        JScrollPane scrollPane = new JScrollPane(scrollPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 50, ((int) sirka)-5, 715);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, new Color(204, 204, 204)));
        scrollPane.setBackground(new Color(240, 240, 240));
        
       
        add(scrollPane);
    }
    
    /**
     * Vykresleni/Prekresleni prihlasenych
     */
    private void paintFriends() {
        
        scrollPanel.removeAll();
        
        for (int i = 0; i < getNumberOfPeople(); i++) {
            
            // todo NAME
            JPanel pnIn = new FriendComponent(getNameOf(i), bean);
            pnIn.setBounds(0, i*60, ((int) sirka), 60);
            
            scrollPanel.add(pnIn);
        }
        
        if (people.size() == 0) {
            // show no logg users
            JLabel msg = new JLabel("No one is online");
            msg.setBounds(100, 0, 300, 100);
            scrollPanel.add(msg);
        }
        
        scrollPanel.repaint();
    }
    
    
    
    private int getNumberOfPeople() {
       // pocet pratel
        return people.size();
    }
    
    
    private String getNameOf(int index) {
        // Jmeno na indexu
        if (people.size() >= index) {
            return people.get(index);
        }
        return "";
    }
    
    
   
    
    public void setPeople(ArrayList<String> newList) {
        
        // Zmenilo se neco?
        if (newList.size() != people.size()) {
            people.clear();
            
            for (String person : newList) {
                person = person.trim();
                if ( ! person.equalsIgnoreCase(bean.getConnection().getLogin())) {
                    people.add(person);
                }
            }
            
            paintFriends(); // Znovu vykresleni
        }
    }
}
