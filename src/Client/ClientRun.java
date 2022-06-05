package Client;
import java.io.*;
import java.net.*;

public class ClientRun{
    public static void main(String[] args) throws UnknownHostException{
        //Scanner in = new Scanner(System.in());
        String a = "Ciao";
        InetAddress ServerIP = InetAddress.getLocalHost();
        byte[] msg = a.getBytes();
        try (DatagramSocket socket = new DatagramSocket(6789)) {
            DatagramPacket packet = new DatagramPacket(msg,msg.length,ServerIP,4445);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}