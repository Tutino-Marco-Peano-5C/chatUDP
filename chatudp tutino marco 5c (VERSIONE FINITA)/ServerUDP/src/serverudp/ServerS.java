/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverudp;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author MULTIMEDIALE26
 */
public class ServerS {
         int porta;
    InetAddress indirizzo;
    String ip = "localhost";

      public ServerS(InetAddress indirizzo, int porta) throws UnknownHostException {
        this.porta = porta;
        indirizzo = InetAddress.getByName(ip);
    }
      
       ServerS(int porta) {
        this.porta = porta;
    }
}


