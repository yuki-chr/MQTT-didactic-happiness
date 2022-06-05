package Client;

import java.util.*;
import java.io.*;
import java.net.*;

public class ClientRun{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Scrivi qualcosa : ");
        String txt = in.nextLine();
        //String txtIP = "192.168.1.254";
        InetAddress ServerIP = InetAddress.getByName("192.168.1.254");
        byte[] msg = new byte[1024];
        msg = txt.getBytes();
        DatagramSocket socket = new DatagramSocket(6789);
        DatagramPacket packet = new DatagramPacket(msg,msg.length,ServerIP,4445);
        socket.send(packet);
    }
}