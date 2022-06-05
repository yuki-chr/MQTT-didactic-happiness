package Client;

import java.util.*;
import java.net.*;

public class ClientRun{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Scrivi qualcosa : ");
        String txt = in.nextLine();
        byte[] msg = new byte[1024];
        msg = txt.getBytes();
        //String txtIP = "192.168.1.254";

        InetAddress ServerIP;
        DatagramSocket socket;
        try{
            ServerIP = InetAddress.getByName("79.52.133.115");
            socket = new DatagramSocket(6789);
            DatagramPacket packet = new DatagramPacket(msg,msg.length,ServerIP,4445);
            socket.send(packet);
        }catch(Exception e){
            System.out.println("problems with sending stuff: " + e);
        }
        in.close();
    }
}