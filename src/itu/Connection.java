/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Meluzin
 */
public class Connection {
    String username, address = "localhost";
    ArrayList<String> connected = new ArrayList();
    List<JButton> container = new ArrayList<JButton>();
    //List <StyledDfrocument> doc_stash = new ArrayList<StyledDocument>();
    Map docs = new HashMap();

    Boolean connection = false;

    Socket sock;
    BufferedReader read;
    PrintWriter write;
    String login;

    public idk.draw canvas;
    int jump = 0;

    JButton send;
    JButton smiley;
    JButton tester;
    JButton disc;
    JTextField Send_text_field;
    // JTextArea chat_space;
    JTextArea online;
    JScrollPane scroll;
    JScrollPane scroll2;
    JTextPane chat_space;
    StyledDocument doc;
    SimpleAttributeSet keyWord = new SimpleAttributeSet();
    Style style;

    String path = System.getProperty("user.dir");
    URL url = getClass().getResource(path);
    Image image = Toolkit.getDefaultToolkit().getImage("src/itu/1.jpg");
    // BufferedImage image = ImageIO.read(new File(path + "/src/itu/1.jpg"));

    JPanel friends;

    int mover = 0;
    int jj = 0;
    
    
    

    public void Connection() {
        

    }


    public void un_people() {
        int ct = container.size();
        System.out.format("niggers " + ct);
        for (int i = 0; i < ct; i++) {
            /*JButton bt = container.get(i);
            remove(bt);
            bt = null;*/
            //revalidate();

        }
        //container.clear();
        //this.repaint();
    }

    public void ListenThread() {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }

    public class IncomingReader implements Runnable {

        @Override
        public void run() {

            String[] message;
            String stream;
            ArrayList<Integer> store = new ArrayList();
            ArrayList<Integer> store1 = new ArrayList();
            ArrayList<Integer> store2 = new ArrayList();
            ArrayList<Integer> store3 = new ArrayList();
            ArrayList<Integer> store4 = new ArrayList();
            ArrayList<Integer> store5 = new ArrayList();

            try {

                while ((stream = read.readLine()) != null) {

                    // doc.insertString(doc.getLength(), stream, null);
                    message = stream.split(":");
                    System.out.format(stream);

                    if (message[0].equals("MUC OK")) {

                        if (message[1].contains("Sx(fun)")) {
                            for (int i = -1; (i = message[1].indexOf("Sx(fun)", i + 1)) != -1; i++) { // doplni indexy s nalezenym retezcem
                                store.add(i);
                            }
                        } else if (message[1].contains("Sx(1)")) {
                            for (int i = -1; (i = message[1].indexOf("Sx(1)", i + 1)) != -1; i++) {
                                store.add(i);
                            }
                        } else if (message[1].contains("Sx(2)")) {
                            for (int i = -1; (i = message[1].indexOf("Sx(2)", i + 1)) != -1; i++) {
                                store.add(i);
                            }
                        } else if (message[1].contains("Sx(3)")) {
                            for (int i = -1; (i = message[1].indexOf("Sx(3)", i + 1)) != -1; i++) {
                                store.add(i);
                            }
                        } else if (message[1].contains("Sx(4)")) {
                            for (int i = -1; (i = message[1].indexOf("Sx(4)", i + 1)) != -1; i++) {
                                store.add(i);
                            }
                        } else if (message[1].contains("Sx(5)")) {
                            for (int i = -1; (i = message[1].indexOf("Sx(5)", i + 1)) != -1; i++) {
                                store.add(i);
                            }
                        }

                        for (int i = 0; i < message[1].length(); i++) {
                            String prt = "" + message[1].charAt(i);
                            if (store.contains(i)) // nahradi retezec smajlikem
                            {

                                i = i + 7;
                                StyleConstants.setIcon(style, new ImageIcon(image));
                                doc.insertString(doc.getLength(), "ignoring", style);
                                store.clear();
                            } else if (store1.contains(i)) {

                                i = i + 7;
                                StyleConstants.setIcon(style, new ImageIcon(image));
                                doc.insertString(doc.getLength(), "ignoring", style);
                            } else if (store2.contains(i)) {

                                i = i + 7;
                                StyleConstants.setIcon(style, new ImageIcon(image));
                                doc.insertString(doc.getLength(), "ignoring", style);
                            } else if (store3.contains(i)) {

                                i = i + 7;
                                StyleConstants.setIcon(style, new ImageIcon(image));
                                doc.insertString(doc.getLength(), "ignoring", style);
                            } else if (store4.contains(i)) {

                                i = i + 7;
                                StyleConstants.setIcon(style, new ImageIcon(image));
                                doc.insertString(doc.getLength(), "ignoring", style);
                            } else if (store5.contains(i)) {

                                i = i + 7;
                                StyleConstants.setIcon(style, new ImageIcon(image));
                                doc.insertString(doc.getLength(), "ignoring", style);
                            } else {
                                doc.insertString(doc.getLength(), prt, null);
                            }
                        }

                    } else if (message[0].equals("LOG OK")) {

                        for (String user : message[1].split(",")) {
                            connected.add(user);
                        }
                        
                        

                    } else if (message[0].equals("DIS OK")) {
                        System.out.format("niggersjewsci ");
                        System.out.format(message[1]);
                        connected.remove(message[1]);
                        
                    } else if (message[0].equals("SIC OK")) {
                        System.out.format("niggersjewsci ");
                        System.out.format(message[1]);
                        if (docs.containsKey(message[1])) {

                            StyledDocument per = (StyledDocument) docs.get(message[1]);
                            // todo printer(message[2], per);
                            //   per.insertString(per.getLength(),message[2], null); 
                        } else {
                            pm mes = new pm(message[1], login); //mesaage[1] sender
                            mes.pipes(username, read, write);
                            docs.put(message[1], mes.docs());
                            StyledDocument per = (StyledDocument) docs.get(message[1]);
                            // todo printer(message[2], per);
                            // per.insertString(per.getLength(),message[2], null); 
                        }

                    } else {

                        // doc.insertString(doc.getLength(), stream, null);
                        // chat_space.setCaretPosition(chat_space.getDocument().getLength());
                    }

                    doc.insertString(doc.getLength(), "\n", null);
                }
            } catch (Exception ex) {
            }
        }
    }

    public int try_connect(String log, String pswd, int mode) {
        String ans;
        String[] message;
        login = log;
        
        // neni connection
        if ( ! connection) {
            // connect
            try {
                //sock = new Socket("147.229.216.205", 21201);
                //sock = new Socket("192.168.0.47", 21201);
                sock = new Socket("12.12.12.2", 21201);
                //sock.setSoTimeout(10000);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                read = new BufferedReader(streamreader);
                write = new PrintWriter(sock.getOutputStream());
                
                connection = true;
                
            } catch (Exception ex) {

                ListenThread();
                //   doc.insertString(doc.getLength(), "cant connect", keyWord);
                //chat_space.append("Cannot Connect! Try Again. \n");
                

            }
        }
        

        if (connection) {
            try {
                if (mode == 0) {
                    write.println("REG " + log + " " + pswd);
                    write.flush();
                } else {
                    write.println("LOG " + log + " " + pswd);
                    write.flush();
                }
                
                ans = read.readLine();
                //   System.out.format(ans);
                message = ans.split(":");
                
                if (message[0].equals("NEW LOG OK")) {
                    
                    for (String user : message[1].split(",")) {
                        connected.add(user);
                    }
                    ListenThread();
                    // todo log ok
                    //people();
                    return 1;
                    
                }
                else if (ans.equals("REG: ERR Ussername already taken. Failed !")) {
                    return 5;
                }
                else if (ans.equals("REG OK: Registration completed !")) {
                    return 3;
                }
                else if (ans.equals("LOG: ERR ussername or password incorect. Failed !")) {
                    return -6;
                }else {
                    return 0;
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return -1; // not connected
    }

    public void disconect() {
        
        System.out.println("Log off");
        try {
            if (write != null) {
                write.println("DIS " + login);
                write.flush();
                sock.close();
            }
        } catch (IOException ex) {
            ;
        }
    }

}
