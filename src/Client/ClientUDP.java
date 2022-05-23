
/**
 * Aggiungi qui una descrizione della classe ClientUDP
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public class ClientUDP
{
    public static void main(String[] args) 
    {
        String messaggio = "Invio Messaggio UDP";
        
        System.out.println('\n'+"***********************");
        
        ClientSocketUDP cliente  = new ClientSocketUDP("localhost", 6789);
          
        System.out.println("ClientUDP TX :"+messaggio);
        for(int i=0; i<10; i++) {
            cliente.comunica(messaggio);
        
            System.out.println("ClientUDP RX :"+'\n'+cliente.getStringaRisposta());
        }
        System.out.println("ClientUDP - end");
    }
}
