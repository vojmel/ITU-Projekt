/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itu;

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.JFrame;

import java.awt.event.WindowEvent;

/**
 *
 * @author christian
 */
public class Itu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        /*Login log = new Login();
        log.setVisible(true);
        idk id = new idk();
        id.setVisible(false);
         // client cl = new client();
        // log.get_client(cl);
        log.get_clien(id);
        //    id.setVisible(true);*/
        
        ClientFrame frame = new ClientFrame();
        frame.show();
        
        int ret;
        int end = 0;
    }

}
