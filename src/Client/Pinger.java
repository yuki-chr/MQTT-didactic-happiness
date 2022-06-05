package Client;
import Common.*;
import Common.Message.MessageType;

import java.net.*;

public class Pinger extends Thread{
    DatagramSocket socket;
    InetAddress serverIP;
    int serverPort;
    private byte[] buf = new byte[1024];

    public Pinger(DatagramSocket socket, InetAddress ip, int port){
        this.serverIP = ip;
        this.socket = socket;
        this.serverPort = port;
    }

    @Override
    public void run(){
        boolean running = true;
        while(running){
            Message PING = new Message();
            PING.type = MessageType.PING;
            byte [] ping = PING.serialize().getBytes();
            DatagramPacket pingServer = new DatagramPacket(ping, ping.length, serverIP, serverPort);
            
            try{
                socket.send(pingServer);
                Thread.sleep(10000);
            }catch(Exception e){
                System.out.println("c'è qualquadra che non cosa");
                running = false;
            }
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try{
                socket.receive(packet);
                
            }catch(Exception e){
                running = false;
            }
            String tomato = packet.toString();
            Message toma = new Message(tomato);
            if(toma.type == MessageType.ACK){
                System.out.println("AAAAAACK");
            }
        }
    }

}
