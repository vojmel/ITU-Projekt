/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu;

import itu.panels.MessengerPanel;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author K
 */
public class parser {

    StyledDocument doc;
    SimpleAttributeSet keyWord = new SimpleAttributeSet();
    Style style;
    private ClientBean bean;
    MessengerPanel pan;
    String username, address = "localhost";
    ArrayList<String> connected = new ArrayList();
    ArrayList<pm> pmsArray = new ArrayList();

    List<JButton> container = new ArrayList<JButton>();
    Map docs = new HashMap();
    Map pms = new HashMap();
    BufferedReader read;
    PrintWriter write;
    String login;

    String path = System.getProperty("user.dir");
    URL url = getClass().getResource(path);
    Image image = Toolkit.getDefaultToolkit().getImage("src/itu/1.jpg");
    Image image1 = Toolkit.getDefaultToolkit().getImage("src/itu/1.jpg");
    Image image2 = Toolkit.getDefaultToolkit().getImage("src/itu/1.jpg");
    Image image3 = Toolkit.getDefaultToolkit().getImage("src/itu/4.png");
    Image image4 = Toolkit.getDefaultToolkit().getImage("src/itu/5.png");
    Image image5 = Toolkit.getDefaultToolkit().getImage("src/itu/6.png");

    public parser(ClientBean bean) {

        this.bean = bean;
        pan = bean.getMessenger();
        bean.setParser(this);
    }
    
    public void setName(String log)
    {
        login = log;
    }

    public void printer(String message, StyledDocument docu, String who) throws BadLocationException {
        ArrayList<Integer> store = new ArrayList();
        ArrayList<Integer> store1 = new ArrayList();
        ArrayList<Integer> store2 = new ArrayList();
        ArrayList<Integer> store3 = new ArrayList();
        ArrayList<Integer> store4 = new ArrayList();
        ArrayList<Integer> store5 = new ArrayList();

        if (message.contains("Sx(fun)")) {
            for (int i = -1; (i = message.indexOf("Sx(fun)", i + 1)) != -1; i++) { // doplni indexy s nalezenym retezcem
                store.add(i);
            }
        } else if (message.contains("Sx(1)")) {
            for (int i = -1; (i = message.indexOf("Sx(1)", i + 1)) != -1; i++) {
                store1.add(i);
            }
        } else if (message.contains("Sx(2)")) {
            for (int i = -1; (i = message.indexOf("Sx(2)", i + 1)) != -1; i++) {
                store2.add(i);
            }
        } else if (message.contains("Sx(3)")) {
            for (int i = -1; (i = message.indexOf("Sx(3)", i + 1)) != -1; i++) {
                store3.add(i);
            }
        } else if (message.contains("Sx(4)")) {
            for (int i = -1; (i = message.indexOf("Sx(4)", i + 1)) != -1; i++) {
                store4.add(i);
            }
        } else if (message.contains("Sx(5)")) {
            for (int i = -1; (i = message.indexOf("Sx(5)", i + 1)) != -1; i++) {
                store5.add(i);
            }
        }
       
        docu.insertString(docu.getLength(), who, null);
        for (int i = 0; i < message.length(); i++) {
            String prt = "" + message.charAt(i);
            if (store.contains(i)) // nahradi retezec smajlikem
            {

                i = i + 7;
                StyleConstants.setIcon(style, new ImageIcon(image));
                docu.insertString(docu.getLength(), "ignoring", style);
                store.clear();
            } else if (store1.contains(i)) {

                i = i + 7;
                StyleConstants.setIcon(style, new ImageIcon(image1));
                docu.insertString(docu.getLength(), "ignoring", style);
                store1.clear();
            } else if (store2.contains(i)) {

                i = i + 7;
                StyleConstants.setIcon(style, new ImageIcon(image2));
                docu.insertString(docu.getLength(), "ignoring", style);
                store2.clear();
            } else if (store3.contains(i)) {

                i = i + 7;
                StyleConstants.setIcon(style, new ImageIcon(image3));
                docu.insertString(docu.getLength(), "ignoring", style);
                store3.clear();
            } else if (store4.contains(i)) {

                i = i + 7;
                StyleConstants.setIcon(style, new ImageIcon(image4));
                docu.insertString(docu.getLength(), "ignoring", style);
                store4.clear();
            } else if (store5.contains(i)) {

                i = i + 7;
                StyleConstants.setIcon(style, new ImageIcon(image5));
                docu.insertString(docu.getLength(), "ignoring", style);
                store5.clear();
            } else {
                docu.insertString(docu.getLength(), prt, null);
            }
            
        }
        docu.insertString(docu.getLength(), "\n", null);
    }

    public void pipes(String user, BufferedReader rd, PrintWriter wd) {

        read = rd;
        write = wd;

    }

    public void parse(String[] message) throws BadLocationException, IOException {

        ArrayList<Integer> store = new ArrayList();
        ArrayList<Integer> store1 = new ArrayList();
        ArrayList<Integer> store2 = new ArrayList();
        ArrayList<Integer> store3 = new ArrayList();
        ArrayList<Integer> store4 = new ArrayList();
        ArrayList<Integer> store5 = new ArrayList();

        if (message[0].equals("MUC OK")) {

            if (message[1].contains("Sx(fun)")) {
                for (int i = -1; (i = message[1].indexOf("Sx(fun)", i + 1)) != -1; i++) { // doplni indexy s nalezenym retezcem
                    store.add(i);
                }
            } else if (message[1].contains("Sx(1)")) {
                for (int i = -1; (i = message[1].indexOf("Sx(1)", i + 1)) != -1; i++) {
                    store1.add(i);
                }
            } else if (message[1].contains("Sx(2)")) {
                for (int i = -1; (i = message[1].indexOf("Sx(2)", i + 1)) != -1; i++) {
                    store2.add(i);
                }
            } else if (message[1].contains("Sx(3)")) {
                for (int i = -1; (i = message[1].indexOf("Sx(3)", i + 1)) != -1; i++) {
                    store3.add(i);
                }
            } else if (message[1].contains("Sx(4)")) {
                for (int i = -1; (i = message[1].indexOf("Sx(4)", i + 1)) != -1; i++) {
                    store4.add(i);
                }
            } else if (message[1].contains("Sx(5)")) {
                for (int i = -1; (i = message[1].indexOf("Sx(5)", i + 1)) != -1; i++) {
                    store5.add(i);
                }
            }

            // get sender
            // user messagecondtent
            // odstraneni pripadnych pocatecnich mezer
            String[] sender = message[1].replaceAll("^\\s+","").split("\\s+");
            
            // write sender
            pan.serverSendMessage(sender[0], 3, image);
            
            // oddelani odesilatele + 2 pocet mezer
            for (int i = sender[0].length()+2; i < message[1].length(); i++) {
                String prt = "" + message[1].charAt(i);

                //System.out.format(Integer.toString(message[1].length()));
                
                if (store.contains(i)) // nahradi retezec smajlikem
                {

                    i = i + 7;
                    /* StyleConstants.setIcon(style, new ImageIcon(image));
                     doc.insertString(doc.getLength(), "ignoring", style); */
                    pan.serverSendMessage(prt, 2, image);
                    store.clear();
                } else if (store1.contains(i)) {

                    i = i + 7;
                    pan.serverSendMessage(prt, 2, image1);
                    store1.clear();
                } else if (store2.contains(i)) {

                    i = i + 7;
                    pan.serverSendMessage(prt, 2, image2);
                    store2.clear();
                } else if (store3.contains(i)) {

                    i = i + 7;
                    pan.serverSendMessage(prt, 2, image3);
                    store3.clear();
                } else if (store4.contains(i)) {

                    i = i + 7;
                    pan.serverSendMessage(prt, 2, image4);
                    store4.clear();
                } else if (store5.contains(i)) {

                    i = i + 7;
                    pan.serverSendMessage(prt, 2, image5);
                    store5.clear();
                } else {
                    //System.out.format("mess");
                    pan.serverSendMessage(prt, 1, image);
                    // doc.insertString(doc.getLength(), prt, null);
                }
            }
            
            // posle znak konce radku
            pan.serverSendMessage("\n", 1, image);

        } else if (message[0].equals("LOG OK")) {  // logi nprobehl ok

            for (String user : message[1].split(",")) {
                connected.add(user);
            }
            
            // nastaveni novych lidi
            bean.getFriendsList().setPeople(connected);

        } else if (message[0].equals("DIS OK")) {  // odpojeni 
            
            System.out.format("niggersjewsci ");
            System.out.format(message[1]);
            connected.remove(message[1]);
            
            // nastaveni novych lidi
            bean.getFriendsList().setPeople(connected);

        } else if (message[0].equals("SIC OK")) { // soukroma zprava
            
            String sender = message[1].trim();
            
            System.out.format("niggersjewsci ");
            System.out.format(sender);
            
            // show upozorneni u pritele
            bean.getFriendsList().notifyOnFriend(sender, true);

            if (docs.containsKey(sender)) {

                // okno je vytvore
                StyledDocument per = (StyledDocument) docs.get(sender);
                printer(message[2], per, sender);
                //   per.insertString(per.getLength(),message[2], null);
                
            } else {
                
                pm mes = new pm(bean, sender, login); //mesaage[1] sender
                
                mes.pipes(username, read, write);
                docs.put(sender, mes.docs());
                pms.put(sender, mes);
                StyledDocument per = (StyledDocument) docs.get(sender);
                printer(message[2], per, sender);
                // per.insertString(per.getLength(),message[2], null); 
            }

        } else {

                        // doc.insertString(doc.getLength(), stream, null);
            // chat_space.setCaretPosition(chat_space.getDocument().getLength());
        }
        
        
   

    }

    public Map pms() {
        return pms;
    }

    public BufferedReader getRead() {
        return read;
    }

    public PrintWriter getWrite() {
        return write;
    }
    
    
    public void addToDocs(String user, pm mes) {
        docs.put(user, mes.docs());
    }

    public void addToPms(String user, pm mes) {
        pms.put(user, mes);
    }
    
    public boolean isPnExists(String name) {
        return docs.containsKey(name);
    }
    
    public pm getPmFrame(String name) {
        if (isPnExists(name)) {
            return (pm) pms.get(name);
        }
        return null;
    }
    
    public void deltePmFrame(String name) {
        if (isPnExists(name)) {
            docs.remove(name);
            pms.remove(name);
        }
    }
    
}

