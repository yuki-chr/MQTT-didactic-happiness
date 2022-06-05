package Server;

import Common.*;
import Common.Message.MessageType;

import java.net.*;
//Scanner in = new Scanner(Syse.in);
public class ServerRun extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];
    private byte[] rebuf = new byte[256];

    public ServerRun() {
        try{
            socket = new DatagramSocket(4445);
        }catch(Exception err) {
            System.out.println(err);
        }
    }

    public void run() {
        running = true;

        while (running) {
            DatagramPacket packet 
              = new DatagramPacket(buf, buf.length);
            try{
                socket.receive(packet);
            }catch(Exception err) {
                System.out.println("02 "+ err);
            }
            
            //arguments of the message to return
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            System.out.println("address: "+address+" port: "+port);
            
            String received 
              = new String(packet.getData(), 0, packet.getLength()); 
            
            Message recMsg = new Message(received);
            recMsg.ip = address;
            
            switch (recMsg.type){
                case PING:
                    Message ping = new Message(null,MessageType.ACK,null,null);
                    String restr = ping.serialize();
                    rebuf = restr.getBytes();
                    break;
                case REGISTER:
                    break;
                case LOGIN:
                    break;
                case UPDATE:
                    break;
                case TEXT:
                    break;
                default:
            }
            
            packet = new DatagramPacket(rebuf, rebuf.length, address, port);
            
            /*
            PING,
        
            REGISTER,
            LOGIN,
            UPDATE,
            TEXT
             */

            //constructing the package and sending it back

            packet = new DatagramPacket(buf, buf.length, address, port);
            
            try{
                socket.send(packet);
            }catch(Exception err) {
                System.out.println(err);
            }
        }
        socket.close();
    }
    public static void main(String[] args){
        ServerRun server = new ServerRun();
        server.start();
    }
}
