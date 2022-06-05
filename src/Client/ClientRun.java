package Client;
import Common.*;

import java.util.*;
import java.io.IOError;
import java.io.IOException;
import java.net.*;

public class ClientRun extends Thread{
    InetAddress ip;
    int port;
    DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public void sendMessage(Message MSG) throws IOException{
        byte[] msg = MSG.serialize().getBytes();
        DatagramPacket Out = new DatagramPacket(msg,msg.length,ip,port);
        socket.send(Out);
    }

    @Override
    public void run(){
        running = true;
        while (running){
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try{
            socket.receive(packet);
            String a = packet.toString();
            System.out.println("Server : " + a);
        }catch(Exception e){
            running = false;
        }
        }
    }




    public static void main(String[] args){
        ClientRun ClientRun = new ClientRun(ip, port);
        Scanner in = new Scanner(System.in);
        System.out.print("Scrivi qualcosa : ");
        String txt = in.nextLine();
        byte[] msg = new byte[1024];
        msg = txt.getBytes();
        //String txtIP = "192.168.1.254";
         try{
            DatagramPacket packet = new DatagramPacket(msg,msg.length,ip,port);
            socket.send(packet);
        }catch(Exception e){
            System.out.println("problems with sending stuff: " + e);
        }
        in.close();
    }

    public ClientRun(InetAddress ip, int port){
        this.ip = ip;
        this.port = port;
    }
}