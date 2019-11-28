/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientudp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


/**
 *
 * @author MULTIMEDIALE26
 */
public class ClientGUI extends JFrame implements ActionListener{
     private JTextField Area; 
     private JTextField Area2;
     private JTextArea panel;
     private JButton invio = new JButton("INVIO");
     byte [] buffer;
     byte [] buffer2;
     
     DatagramSocket client;
     String messaggio;
    
     String ip = "localhost";
     
     
     JPanel pannello;
     JPanel pannello2;
     JPanel pannello3;
     InetAddress address = InetAddress.getByName(ip);
     
     
    

   
    public ClientGUI () throws  SocketException,UnknownHostException{
        
        
        
        setTitle("CHAT-UDP-TUTINO");
      
        Area = new JTextField();
        Area2 = new JTextField();
        buffer = new byte[1024];
        buffer2 = new byte[1024];
        panel = new JTextArea();
        
        Font fontA=new Font ("Red", Font. PLAIN, 30);
       
        pannello = new JPanel();
        pannello.setBorder(new TitledBorder("USERNAME                                                       MESSAGGIO"));
        pannello.setLayout(new GridLayout(1, 3));
        pannello.add(Area2);
        pannello.add(Area);
        pannello.add(invio);
        
        
        pannello.setFont(fontA);
        
        
        
        pannello2 = new JPanel();
        
        pannello2.setLayout(new GridLayout(1, 1));
        pannello2.add(panel);
                
                
                
        pannello3 = new JPanel();
        JScrollPane p = new JScrollPane(pannello3);
        
        pannello3.setLayout(new GridLayout(5, 5));
        this.setLayout(new GridLayout(5, 5));
        this.add(pannello);
        pannello3.add(pannello2);
        
        this.add(p);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        this.getContentPane().add(pannello3, BorderLayout.NORTH);
        invio.addActionListener(this);
        pack();
        setSize(700, 500);
        setVisible(true);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client = new DatagramSocket();
                    while (true) {
                        DatagramPacket pacchetto = new DatagramPacket(buffer, buffer.length);
                        client.receive(pacchetto);
                        String messaggio = new String(pacchetto.getData());
                        panel.append("Server: " + messaggio );
                    }
                } catch (Exception e) {
                }
            }
        }).start();

    }
        
        
    
    
     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(invio)) {
            try {
                String message = Area.getText();
                String username1 = Area2.getText();
                String messaggio = message + " & " + username1;
                buffer = messaggio.getBytes();
                DatagramPacket sendpack = new DatagramPacket(buffer, buffer.length, InetAddress.getLoopbackAddress(), 7676);
                client.send(sendpack);
                panel.append("l'utente e' " + username1 + " ed il messaggio e' : " + message );
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
}
    
    
    

