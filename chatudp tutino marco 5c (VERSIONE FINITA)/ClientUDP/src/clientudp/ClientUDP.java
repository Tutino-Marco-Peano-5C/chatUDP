/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientudp;

/**
 *
 * @author MULTIMEDIALE26
 */

/*librerie*/
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws UnknownHostException {
        // TODO code application logic here
        /*new runnable serve per lanciare il programma*/
          new Runnable(){
                  
                  @Override
              /*metodo per lanciare il programma e gestire le eccezioni(try catch)*/
              
            public void run() {
                try{
                    new ClientGUI();
                }catch(SocketException | UnknownHostException e){
                      Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }.run();
    }
    
}
    
   

