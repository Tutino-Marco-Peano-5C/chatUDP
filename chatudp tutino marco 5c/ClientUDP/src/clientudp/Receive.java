/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientudp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MULTIMEDIALE26
 */
public class Receive implements Runnable{
    
       DatagramSocket socket;

    public Receive(DatagramSocket socket) {
        this.socket = socket;
    }
    
    
    
 @Override
    public void run() {
        byte[] buffer = new byte[100];
        String ricevuto;
        DatagramPacket serverDatagram;

        try {

            serverDatagram = new DatagramPacket(buffer, buffer.length);

            while (!Thread.interrupted()) {
                socket.receive(serverDatagram);
                ricevuto = new String(serverDatagram.getData(), 0, serverDatagram.getLength(), "ISO-8859-1");
                System.out.println("Il server ha inviato /n" + ricevuto);
                
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    }
    
