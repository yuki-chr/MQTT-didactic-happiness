import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServerSocketUDP
{
    // Oggetto ServerSocket gestisce il servizio di connessione da client
    private DatagramSocket serverUDP = null;
    
    // Oggetto Socket gestisce il servizio di invio dati al client
    private String stringaRicevuta = null;
    
    private int errorCount = 0;
    private boolean ErrConn = false;  
    
    // Oggetto InetAddress per gestire gli indirizzi IP
    private InetAddress IAClient;
    private int PortClient;  
    private String IPClient;
    
    // Porta di lavoro
    private int PortServer = 6789;

    /**
     * Costruttore degli oggetti di classe  ServerSocketUDP
     */
    public ServerSocketUDP()
    {
    }
    
    public ServerSocketUDP(int porta)
    {
        PortServer = porta;
    }
    
    // Metodo attendi() avvia il servizio
    public void attendi()
    {
        ErrConn = false;
        try
        {
            // Con il calendar recupero data e orario (corrente)
            Calendar c1 = Calendar.getInstance();
            String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(c1.getTime());
            // Visualizzo avvio nel log
            System.out.println(timeStamp + " Server wait UDP connection on port " + PortServer);
 
            // Istanzio il DatagramSocket e avvio il servizio sulla porta PortServer
            serverUDP = new DatagramSocket(PortServer);
            
            byte[] bufferIn = new byte[1024];            
            DatagramPacket receivePacket = new DatagramPacket(bufferIn, bufferIn.length);         
            // Ricezione messaggio da client
            serverUDP.receive(receivePacket);
  
             
            stringaRicevuta = new String(receivePacket.getData());
            int numcar = receivePacket.getLength();
            stringaRicevuta = stringaRicevuta.substring(0, numcar);
           
            // Rilevo l'IP e la porta di connessione del client
            InetAddress IAClient = receivePacket.getAddress();
            PortClient =  receivePacket.getPort();
            IPClient = IAClient.getHostAddress();
            
             // LOG messaggio ricevuto
            String timeStamprx = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(c1.getTime());
            System.out.println(timeStamprx +" Server RX: [" + stringaRicevuta + "] from Client [" +IPClient+"]");
            
            String risposta = "HELLO";
            byte[] bufferOut = new byte[1024];
            bufferOut = risposta.getBytes();          
         
            System.out.println(timeStamprx + " Server TX: [" + risposta +"] to Client [" +IAClient.getHostName() +"],  " + PortClient);
          
            // Invio la risposta al client
            DatagramPacket sendPacket = new DatagramPacket(bufferOut, bufferOut.length, IAClient, PortClient);        
            serverUDP.send(sendPacket); 
            
            serverUDP.close();
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Error in connection");
            ErrConn = true;
            // chiudo connessione client                  
            serverUDP.close();
            errorCount++;
            if(errorCount>20)
                System.exit(1);
        }
        //return client;
    }
    
        // Metodo comunica() scambia dati con il client
    public void comunica(String RxMesg)
    {
        
        String risposta="UDP server Rx:";
        String timeStamprx;
        if(ErrConn) 
            return;
        
        try
        {
            // Con il calendar recupero data e orario (corrente)
            Calendar c2 = Calendar.getInstance();
            timeStamprx = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(c2.getTime());
            
            // Stringa di risposta al client (ECHO)
            // risposta = timeStamprx + risposta + " to Client [" +IPClient+"] : "+ RxMesg;
            /*
           risposta = RxMesg;
           
           bufferOut = risposta.getBytes();          
         
           System.out.println(timeStamprx + " Server TX: " + risposta +"] to Client [" +IAClient.getHostName() +"]" + PortClient);
          
            // Invio la risposta al client
            DatagramPacket sendPacket = new DatagramPacket(bufferOut, bufferOut.length, IAClient, PortClient);        
            serverUDP.send(sendPacket);
            
             System.out.println(" 6" ); 
             
            // LOG messaggio ricevuto
            System.out.println(timeStamprx + " Server TX: " + risposta);

            // chiudo connessione client                  
            serverUDP.close();
          */
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Error on comunication");
            // chiudo connessione client                  
            serverUDP.close();
            errorCount++;
            if(errorCount>20)
                System.exit(1);
        }
    }

}
