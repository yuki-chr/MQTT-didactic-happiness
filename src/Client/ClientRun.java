package Client;

import Common.*;
import Common.Message.MessageType;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientRun extends Thread {
    InetAddress serverIP;
    int serverPort;
    DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[1024];
    ArrayList<String> totMessages = new ArrayList<>();

    public ClientRun(InetAddress ip, int port) {
        this.serverIP = ip;
        this.serverPort = port;
        try {
			socket = new DatagramSocket();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    public ClientRun() {

    }

    public void startClient(InetAddress ip, int port) {
        this.serverIP = ip;
        this.serverPort = port;
        try {
			socket = new DatagramSocket();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    public void sendMessage(Message MSG) throws IOException {
        byte[] msg = MSG.serialize().getBytes();
        DatagramPacket Out = new DatagramPacket(msg, msg.length, serverIP, serverPort);
        socket.send(Out);
    }

    @Override
    public void run() {

        Pinger ping = new Pinger(socket, serverIP, serverPort);
        ping.start();

        running = true;
        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);

            } catch (Exception e) {
                running = false;
            }
            String tomato = packet.toString();
            Message toma = new Message(tomato);
            if(toma.type == MessageType.ACK){
                System.out.println("AAAAAACK");
            }else{
                System.out.println("Server : " + tomato);
                updateLog(tomato);
            }
        }
    }

    public void updateLog(String message) {
        totMessages.add(message);
    }

    public ArrayList<String> log(int i) {
        ArrayList<String> newMessages = new ArrayList<String>(totMessages);
        newMessages.subList(0, i).clear();
        return newMessages;
    }

    public static void main(String[] args) throws IOException {
        String i = "79.52.133.115";
        InetAddress ia = InetAddress.getByName(i);
        ClientRun client = new ClientRun(ia, 4445);
        client.start();
        client.sendMessage(new Message(null, MessageType.PING, null, null));

    }
}