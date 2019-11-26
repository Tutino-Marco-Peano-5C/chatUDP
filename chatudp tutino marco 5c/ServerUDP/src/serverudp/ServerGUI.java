/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverudp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 *
 * @author MULTIMEDIALE26
 */
public class ServerGUI extends JFrame implements ActionListener{
     ServerS client = new ServerS(7676);
     
     private JTextArea Area; 
    
     DatagramSocket Client;
     DatagramSocket server;
     byte [] buffer;
     String messaggio;
     JPanel pannello;
     JPanel pannello2;
     ArrayList<String> messaggi = new ArrayList<>();
    
     private HashMap<InetAddress, Integer> porte = new HashMap<>();

     
     
     
     public ServerGUI(int porta) throws SocketException, UnknownHostException{
        
         
         setTitle("CHAT-UDP");
      
        Area = new JTextArea(6,6);
        Area.setEditable(false);
       
        
        
        
        
         pannello = new JPanel();
         pannello.setLayout(new BorderLayout());
         pannello.setBorder(new TitledBorder("CONVERSAZIONE CON TUTTI I CLIENT"));
         pannello.add(Area, BorderLayout.NORTH);
         
         
         pannello2 = new JPanel();
         pannello2.setLayout(new GridLayout(1, 1));
         pannello2.add(pannello);
         buffer = new byte[1024];
         
         
        
         this.getContentPane().setBackground(Color.BLUE);
         this.getContentPane().add(pannello2, BorderLayout.CENTER);
         

         

        
         setSize(700, 500);
         setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
         
         
         
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    server = new DatagramSocket(7676);
                    while (true) {
                        DatagramPacket pacchetto = new DatagramPacket(buffer, buffer.length);
                        server.receive(pacchetto);
                        String messaggio = new String(pacchetto.getData());
                        Area.append("ricevuto:" + messaggio + " da: " + pacchetto.getAddress() + "" + pacchetto.getPort());
                        server.send(new DatagramPacket(pacchetto.getData(), pacchetto.getLength(), pacchetto.getAddress(), pacchetto.getPort()));
                        Area.append("inviato:" + messaggio + " a: " + pacchetto.getAddress() + "" + pacchetto.getPort());
                        if (!porte.containsKey(pacchetto.getAddress()) && !porte.containsValue(pacchetto.getPort())) {
                            Area.append("L'IP non risulta nell'elenco,ora lo salvo!");
                            porte.put(pacchetto.getAddress(), pacchetto.getPort());
                            if (messaggi.size() > 10) {
                                for (int i = messaggi.size() - 11; i < messaggi.size(); i++) {
                                    byte[] buff = messaggi.get(i).getBytes();
                                    DatagramPacket ultimiMex = new DatagramPacket(buff, buff.length, pacchetto.getAddress(), pacchetto.getPort());
                                    server.send(ultimiMex);
                                    Area.append("inviato:" + new String(buff) + " a: " + pacchetto.getAddress() + " " + pacchetto.getPort());
                                }
                            } else {
                                for (int i = 0; i < messaggi.size(); i++) {
                                    byte[] buffer2 = messaggi.get(i).getBytes();
                                    DatagramPacket ultimiMex = new DatagramPacket(buffer2, buffer2.length, pacchetto.getAddress(), pacchetto.getPort());
                                    server.send(ultimiMex);
                                    Area.append("inviato:" + new String(buffer2) + " a: " + pacchetto.getAddress() + " " + pacchetto.getPort());
                                }
                            }
                        }
                        messaggi.add(messaggio);
                    }
                } catch (IOException ex) {
                }
            }
        }).start();

    }
  

     @Override
    public void actionPerformed(ActionEvent e) {
    }
}
