package Server;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Aggiungi qui una descrizione della classe ServerUDP
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public class ServerUDP
{
    // variabili d'istanza - sostituisci l'esempio che segue con il tuo
    // variabili d'istanza 
    public static ServerSocketUDP server;
    public static String timeStamp;

    public static void main(String[] args)
    {
        timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.println(timeStamp + " UDP Server startup ver 1.0");
    
        server = new ServerSocketUDP();
        do {
                server.attendi();
                //server.comunica("hello!");
        } while(true);    

    
    }

}
