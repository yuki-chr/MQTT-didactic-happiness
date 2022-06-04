package Client;
import java.io.*;
import java.net.*;

class ClientUDP{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in());
        System.out.print("Scrivi qualcosa : ");
        String a = System.in();
        InetAdress IP = InetAddress.getByName("192.168.1.254");
        byte[] msg = new byte[1024];
        msg = a.getBytes()
        socket = new DatagramSocket(6789);
        packet = new datagramPacket(msg,msg.length,IP,4445);
        datagramSocket.send(packet);
    }
}