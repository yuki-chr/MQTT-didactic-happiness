package Client;
import java.util.*;
import java.io.*;
import java.net.*;

class ClientUDP{
    public static void main(String[] args){
        //Scanner in = new Scanner(System.in());
        System.out.print("Scrivi qualcosa : ");
        String a = "Ciao";
        String ServerIP = "192.168.1.254";
        byte[] msg = new byte[1024];
        msg = a.getBytes();
        DatagramSocket socket = new DatagramSocket(6789);
        DatagramPacket packet = new DatagramPacket(msg,msg.length,IP,4445);
        socket.send(packet);
    }
}