import java.net.*;
//Scanner in = new Scanner(Syse.in);
public class ServerUDP extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public ServerUDP() {
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

            packet = new DatagramPacket(buf, buf.length, address, port);
            String received 
              = new String(packet.getData(), 0, packet.getLength()); 
            
            if (received.equals("end")) {
                running = false;
                continue;
            }
            try{
                socket.send(packet);
            }catch(Exception err) {
                System.out.println(err);
            }
        }
        socket.close();
    }
    public static void main(String[] args){
        ServerUDP server = new ServerUDP();
        server.start();
    }
}
