package Client;
import Common.*;

import java.io.*;
import java.net.*;

public class ClientRun extends Thread{
    InetAddress serverIP;
    int serverPort;
    DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[1024];

    public ClientRun(InetAddress ip, int port){
        this.serverIP = ip;
        this.serverPort = port;
    }
    
    public ClientRun(){

    }
    
    public void startClient(InetAddress ip, int port){
        this.serverIP = ip;
        this.serverPort = port;
    }

    public void sendMessage(Message MSG) throws IOException{
        byte[] msg = MSG.serialize().getBytes();
        DatagramPacket Out = new DatagramPacket(msg, msg.length, serverIP, serverPort);
        socket.send(Out);
    }

    @Override 
    public void run(){
        Pinger ping = new Pinger(socket, serverIP, serverPort);
        ping.start();
        
        running = true;
        while (running){
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try{
                socket.receive(packet);
                
            }catch(Exception e){
                running = false;
            }
            String tomato = packet.toString();
            System.out.println("Server : " + tomato);
        }
    }

    /*public static void main(String[] args){
        ClientRun client = new ClientRun(ip,port);
        client.start(); 
        
    }*/
}