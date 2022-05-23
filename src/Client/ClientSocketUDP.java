import java.io.*;
import java.net.*;
import java.net.InetAddress; 
/**
 * Aggiungi qui una descrizione della classe ClientSocketUDP
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public class ClientSocketUDP
{
    // variabili d'istanza 
    private String IPServer; // IP DEL SERVER (WAN)
    //String IPServer="localhost"; // IP DI UN SERVER IN LAN 
    private int PortServer;          // PORTA DI CONNESSIONE TC      
    // Stringa che mantiene l'ultimo messaggio ricevuto dal server
    private String stringaRicevutaDaServer; 
    private InetAddress IPServUDP;
    /**
     * Costruttore degli oggetti di classe  ClientSocketUDP
     */
    public ClientSocketUDP()
    {
         IPServer = "localhost";     
         PortServer = 6789;
    }
    /**
     * Costruttore degli oggetti di classe  ClientSocketTCP con parametri
     */
    public ClientSocketUDP(String IPConnect, int porta)
    {
        IPServer = IPConnect; // IP DEL SERVER DAD (WAN)
        PortServer = porta;          // PORTA DI CONNESSIONE TC
    } 
    // Metodi setter 
    public void setIPServer(String IP) {
       IPServer = IP;    
    } 
    
    public void setPortServer(int porta) {
       PortServer = porta;    
    }     
    // Metodi getter
    public String getIPServer() {
       return IPServer;            
    } 
    
    public int getPortServer() {
       return PortServer;            
    } 
    // Il metodo getStringaRisposta() rende accessibile all'esterno 
    // la stringa ricevuta in risposta dal server remoto
    public String getStringaRisposta() {
       return stringaRicevutaDaServer;
    }
    
    public void comunica(String value) 
    { 
        // Occorrono elcune variabili per gestire la comunicazione con la libreria UDP
        byte[] bufferOut = new byte[1024];
        byte[] bufferIn = new byte[1024];    
          
        try {
            IPServUDP = InetAddress.getByName(IPServer);

             // Creo un oggetto DatagramSocket (libreria java)
            DatagramSocket clientSockUDP = new DatagramSocket();
            
            // Trasformo la stringa Messaggio in bytes
            //value = value + '\n';
            bufferOut = value.getBytes();    
            // INVIO
            // Creo il packetto UDP da inviare
            // Uso un oggetto DatagraPacket (libreria java)
            DatagramPacket sendPacket = new DatagramPacket(bufferOut, bufferOut.length, IPServUDP, PortServer);      
            // Invio il pacchetto in rete direttamente
            clientSockUDP.send(sendPacket);
            
            // RICEZIONE
            // Attendo la ricezione della risposta dal server
            DatagramPacket receivePacket = new DatagramPacket(bufferIn,bufferIn.length);
            // Ricevo il pacchetto di risposta dal server
            clientSockUDP.receive(receivePacket);
            // Trasformo il pacchetto ricevuto in stringa
            stringaRicevutaDaServer = new String(receivePacket.getData());
            
            clientSockUDP.close();
        }
        catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore in connessione");
            System.exit(1);
        }
    }
}
