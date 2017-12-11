/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu;

import itu.ClientBean;
import itu.panels.MessengerPanel;
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
    private ClientBean bean;
    MessengerPanel pan;
    parser par ;
   

    String path = System.getProperty("user.dir");
    URL url = getClass().getResource(path);
    Image image = Toolkit.getDefaultToolkit().getImage("src/itu/1.png");
    Image image1 = Toolkit.getDefaultToolkit().getImage("src/itu/2.png");
    Image image2 = Toolkit.getDefaultToolkit().getImage("src/itu/3.png");
    Image image3 = Toolkit.getDefaultToolkit().getImage("src/itu/4.png");
    Image image4 = Toolkit.getDefaultToolkit().getImage("src/itu/5.png");
    Image image5 = Toolkit.getDefaultToolkit().getImage("src/itu/6.png");
        
    // BufferedImage image = ImageIO.read(new File(path + "/src/itu/1.jpg"));

    JPanel friends;

    int mover = 0;
    int jj = 0;

  
    
    
    

    public Connection(ClientBean bean) {
      /*   doc = chat_space.getStyledDocument();
        style = doc.addStyle("StyleName", null);*/
        this.bean = bean;
        pan = bean.getMessenger();
        par = new parser(bean);

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
                 System.out.format("what");
                while ((stream = read.readLine()) != null) {

                
                    message = stream.split(":");
                    System.out.format(stream);
                    par.parse(message);
                    
                    

                }
            }
            catch (Exception ex) {
                
                System.err.println(ex.toString());
            }
        }
    }       

    public int try_connect(String log, String pswd, int mode) {
        String ans;
        String[] message;
        login = log;
        pan.setName(login);
        par.setName(login);
       
        
        // neni connection
        if ( ! connection) {
            // connect
            try {
                sock = new Socket("147.229.216.205", 21201);
                //sock = new Socket("12.12.12.6", 21201);
                //sock = new Socket("62.245.118.128", 21201);
            
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                read = new BufferedReader(streamreader);
                write = new PrintWriter(sock.getOutputStream());
                pan.pipes("", read, write);
                connection = true;
                
                par.pipes("", read, write);
                
            } catch (Exception ex) {

                ListenThread();
              

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
                    
                    // nastaveni novych lidi
                    bean.getFriendsList().setPeople(connected);
                    
                    ListenThread();
                    
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
                }
                else if (ans.equals("LOG: ERR Already logged in. Failed !")) {
                    
                    return -7;
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

    public String getLogin() {
        return login;
    }

    
    
}
