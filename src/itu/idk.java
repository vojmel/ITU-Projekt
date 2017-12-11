/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu;

import itu.panels.pm;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author christian
 */
public class idk extends JFrame implements ActionListener, WindowListener,
                                            WindowFocusListener,
                                            WindowStateListener {

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

    public draw canvas;
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


    
    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // log off
        disconect();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class ListenAdditionsScrolled implements ChangeListener {

        public void stateChanged(ChangeEvent e) {

            BoundedRangeModel scModel = scroll.getVerticalScrollBar().getModel();

            int ct = container.size();
            int jmp = 0;
            for (int i = 0; i < ct; i++) {
                JButton bt = container.get(i);

                bt.setLocation(400, 100 + jmp - scModel.getValue());
                jmp = jmp + 30;
            //revalidate();

            }

            tester.setLocation(600, 300 - scModel.getValue());
            /*   if (mover < scModel.getValue())
             {
             jj++;
             
             mover = scModel.getValue();
             }
             else
             {
             jj--;
             tester.setLocation(600, 300-jj);
             mover = scModel.getValue();
             }
             // additionsPane.revalidate();*/
            repaint();
        }
    }

    public idk() throws IOException {
        setSize(1000, 800);
                    //setLayout(null);

        JLabel picLabel = new JLabel(new ImageIcon(image));

                  //  friends = new JPanel();
        //    fr = new friends();
        Send_text_field = new JTextField();
        // chat_space = new JTextArea();
        chat_space = new JTextPane();
        send = new JButton("Send");
        send.addActionListener(this);

        disc = new JButton("disc");
        disc.addActionListener(this);

        tester = new JButton("tester");
        tester.addActionListener(this);

        smiley = new JButton("Smiley");
        smiley.addActionListener(this);

        online = new JTextArea(200, 400);
                 //   online.setSize(20,40);
        //  online.setLocation(400,0);

        canvas = new draw();
        doc = chat_space.getStyledDocument();
        style = doc.addStyle("StyleName", null);
                   // canvas.setPreferredSize(new Dimension(800, 400));
        // canvas.setSize(100,100);
        //   canvas.setLocation(0,0);
        // Container cp = getContentPane();

                   // canvas.setLayout(null);
        //setVisible(true);
        //   pack();    
        setVisible(true);
            //        friends.setBounds(240, 0, 100, 400);
        //       friends.setSize(100, 300);

              //      friends.setLocation(400,0);
        //friends.setLayout(null);
        //     friends.setVisible(true);
        System.out.format(path);

        //setComponentZOrder(friends, 0);
        Send_text_field.setBounds(40, 500, 300, 200);
        // chat_space.setBounds(40,40,300,400);
        chat_space.setSize(200, 400);
        smiley.setBounds(400, 600, 100, 30);
        send.setBounds(500, 600, 100, 40);
        tester.setBounds(600, 600, 100, 40);
        disc.setBounds(700, 600, 100, 40);
        picLabel.setBounds(10, 10, 60, 60);
        //online.setBounds(400, 0, 200, 400);
        scroll = new JScrollPane(online);
        scroll.setBounds(400, 0, 200, 400);
        scroll.getViewport().addChangeListener(new ListenAdditionsScrolled());

        scroll2 = new JScrollPane(chat_space);
        scroll2.setBounds(40, 40, 200, 400);
                    //scroll2.getViewport().addChangeListener(new ListenAdditionsScrolled());

                    //setComponentZOrder(online, 0);
        //     friends.add(picLabel);
                  //   friends.add(send);
        //   friends.setComponentZOrder(send, 0);
        canvas.setLayout(null);
        canvas.setOpaque(false);

        canvas.add(Send_text_field);
        //canvas.add(chat_space);
        canvas.add(smiley);
        canvas.add(send);
        canvas.add(tester);
        canvas.add(disc);
        canvas.add(scroll);
        canvas.add(scroll2);

        setContentPane(canvas);

        this.revalidate();
        this.repaint();

                   //pack();
                  //  JPanel contentPane = new JPanel(null);
        ////  contentPane.setPreferredSize(new Dimension(500, 400));
        //   contentPane.add(scroll);
        //   setContentPane(contentPane);
                 //  add(picLabel);
                   // this.add(fr);
        //  this.getContentPane().add(friends);
        
        addWindowListener(this);
        addWindowFocusListener(this);
        addWindowStateListener(this);
    }

    public class draw extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            setVisible(true);
                  //super.paintComponent(g);     // paint parent's background
            //
            // setBackground(Color.BLACK);

            String path = System.getProperty("user.dir");
              //  g.setColor(Color.red);
            // g.fillRect(0, 0, 100, 100);
            try {
                g.drawImage(ImageIO.read(new File(path + "/src/itu/pozadi.png")), (int) 0, 0, null);
            } catch (IOException ex) {
                Logger.getLogger(idk.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void people() {
        int i = 0;
        jump = 0;
        while (i < connected.size()) {

            System.out.format(connected.get(i));
            JButton but = new JButton(connected.get(i));
            but.setBounds(400, 100 + jump, 200, 30);
            but.addActionListener(this);
            but.setContentAreaFilled(false);
            canvas.add(but);
            canvas.setComponentZOrder(but, 0);
            //but.setOpaque(false);
            but.setBorderPainted(false);
            jump = jump + 30;

            container.add(but);
            i++;
            this.repaint();
            // this.revalidate();
        }

    }

    public void un_people() {
        int ct = container.size();
        System.out.format("niggers " + ct);
        for (int i = 0; i < ct; i++) {
            JButton bt = container.get(i);
            remove(bt);
            bt = null;
            //revalidate();

        }
        container.clear();
        this.repaint();

    }

    public void printer(String message, StyledDocument docu) throws BadLocationException {
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
                store.add(i);
            }
        } else if (message.contains("Sx(2)")) {
            for (int i = -1; (i = message.indexOf("Sx(2)", i + 1)) != -1; i++) {
                store.add(i);
            }
        } else if (message.contains("Sx(3)")) {
            for (int i = -1; (i = message.indexOf("Sx(3)", i + 1)) != -1; i++) {
                store.add(i);
            }
        } else if (message.contains("Sx(4)")) {
            for (int i = -1; (i = message.indexOf("Sx(4)", i + 1)) != -1; i++) {
                store.add(i);
            }
        } else if (message.contains("Sx(5)")) {
            for (int i = -1; (i = message.indexOf("Sx(5)", i + 1)) != -1; i++) {
                store.add(i);
            }
        }

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
                StyleConstants.setIcon(style, new ImageIcon(image));
                docu.insertString(docu.getLength(), "ignoring", style);
            } else if (store2.contains(i)) {

                i = i + 7;
                StyleConstants.setIcon(style, new ImageIcon(image));
                docu.insertString(docu.getLength(), "ignoring", style);
            } else if (store3.contains(i)) {

                i = i + 7;
                StyleConstants.setIcon(style, new ImageIcon(image));
                docu.insertString(docu.getLength(), "ignoring", style);
            } else if (store4.contains(i)) {

                i = i + 7;
                StyleConstants.setIcon(style, new ImageIcon(image));
                docu.insertString(docu.getLength(), "ignoring", style);
            } else if (store5.contains(i)) {

                i = i + 7;
                StyleConstants.setIcon(style, new ImageIcon(image));
                docu.insertString(docu.getLength(), "ignoring", style);
            } else {
                docu.insertString(docu.getLength(), prt, null);
            }
        }
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

                        // tady se plni all
                        
                        
                        for (String user : message[1].split(",")) {
                            connected.add(user);
                        }
                        un_people();
                        people();

                    } else if (message[0].equals("DIS OK")) {
                        
                        
                        // se umaze
                        
                        
                        System.out.format("niggersjewsci ");
                        System.out.format(message[1]);
                        connected.remove(message[1]);
                        un_people();
                        people();
                    } else if (message[0].equals("SIC OK")) {
                        System.out.format("niggersjewsci ");
                        System.out.format(message[1]);
                        if (docs.containsKey(message[1])) {

                            StyledDocument per = (StyledDocument) docs.get(message[1]);
                            printer(message[2], per);
                            //   per.insertString(per.getLength(),message[2], null); 
                        } else {
                            pm mes = new pm(null, message[1], login); //mesaage[1] sender
                            mes.pipes(username, read, write);
                            docs.put(message[1], mes.docs());
                            StyledDocument per = (StyledDocument) docs.get(message[1]);
                            printer(message[2], per);
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

        if (connection != true) {

            try {
                //sock = new Socket("147.229.216.205", 21201);
                //sock = new Socket("192.168.0.47", 21201);
                sock = new Socket("12.12.12.2", 21201);
                //sock.setSoTimeout(10000);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                read = new BufferedReader(streamreader);
                write = new PrintWriter(sock.getOutputStream());
                login = log;
                connection = true;

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

                    // tady 
                    for (String user : message[1].split(",")) {
                        connected.add(user);
                    }
                    ListenThread();
                    people();
                    return 1;
                } else if (ans.equals("REG OK: Registration completed !")) {

                    return 1;
                } else {
                    return 0;
                }

            } catch (Exception ex) {

                ListenThread();
                //   doc.insertString(doc.getLength(), "cant connect", keyWord);
                //chat_space.append("Cannot Connect! Try Again. \n");

            }
        } else {
            if (mode == 1) {
                try {

                    write.println("LOG " + log + " " + pswd);
                    write.flush();
                    ans = read.readLine();
                    //System.out.format(ans);
                    message = ans.split(":");

                    if (message[0].equals("NEW LOG OK")) {

                        for (String user : message[1].split(",")) {
                            connected.add(user);
                        }
                        ListenThread();
                        people();

                        return 1;
                    } else {
                        return 2;
                    }
                } catch (Exception ex) {

                }

            }
            return 1;
        }
        return 2;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton rnd;
        if (e.getSource() == send) {
            String empty = "";
            if ((Send_text_field.getText()).equals(empty)) {
                Send_text_field.setText("");
                Send_text_field.requestFocus();
            } else {
                try {

                    write.println("MUC " + login + " /r/n " + Send_text_field.getText());
                    write.flush();
                    Send_text_field.setText("");
                    Send_text_field.requestFocus();
                } catch (Exception ex) {
                    try {
                        doc.insertString(doc.getLength(), "mesage was not sent", null);
                        //chat_space.append("Message was not sent. \n");
                    } catch (BadLocationException ex1) {
                        Logger.getLogger(idk.class.getName()).log(Level.SEVERE, null, ex1);
                    }

                }
                Send_text_field.setText("");
                Send_text_field.requestFocus();
            }

            Send_text_field.setText("");
            Send_text_field.requestFocus();
        } else if (e.getSource() == smiley) {
            connected.add("schlomo");
            connected.add("goldberg");
            connected.add("oy");
            connected.add("vey");
            connected.add("vay");
            people();

            StyleConstants.setIcon(style, new ImageIcon(image));
            try {
                doc.insertString(doc.getLength(), "ignored text", style);
                doc.insertString(doc.getLength(), "ignored text \n", null);
          

                try {
                    smileys field = new smileys(write, "MUC " + login + " /r/n ",null,1);
                    field.setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(idk.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(idk.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (e.getSource() == tester) {

            un_people();

        } else if (e.getSource() == disc) {

            disconect();

        } else {
            for (int i = 0; i < container.size(); i++) {

                JButton b = container.get(i);
                if (b == e.getSource()) {
                    if (docs.containsKey(b.getText())) {

                    } else {
                        pm mes = new pm(null, b.getText(), login);
                        mes.pipes(username, read, write);
                        docs.put(b.getText(), mes.docs());
                    }
                }

            }

        }

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
            Logger.getLogger(idk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
