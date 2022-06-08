package Server;

import Common.*;
import Common.Message.MessageType;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Scanner in = new Scanner(System.in);
public class ServerRun extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];
    private byte[] rebuf = new byte[256];
    Map<InetAddress, ArrayList<String>> topix = new HashMap<InetAddress, ArrayList<String>>();
    Users users;
    String file;

    public ServerRun() {
        try{
            socket = new DatagramSocket(4445);
        }catch(Exception err) {
            System.out.println(err);
        }
        System.out.println("Server Started!");

        file = "src/Server/users_list.txt";
        String json;

        try {
            json = Files.readString(Paths.get(file));
            //System.out.println("read: "+json);
            users = new Users(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @Override
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
            System.out.println("address: "+address.toString().replace("/","")+" port: "+port);
            
            String received 
              = new String(packet.getData()); 
            
            Message recMsg = new Message(received);
            recMsg.ip = address;
            
            Message reply = new Message();
            
            switch (recMsg.type){
                case PING:
                    reply = new Message(null,MessageType.ACK,null,null);
                    break;

                case REGISTER:
                    String yorname = recMsg.topics[0];
                    String psw = recMsg.content;
                    
                    //checking if username-psw alreay exists. else adding them
                    if (users.checkUname(yorname)){
                        reply = new Message(null,MessageType.ERROR,null,"Username already exists!");
                    }else{
                        users.newUser(yorname, psw);
                        reply = new Message(null,MessageType.SUCCESS,null,null);
                    }
                    
                    //updating the user_list file
                    try {
                        Files.writeString(Paths.get(file), users.serialize());
                        //System.out.println(users.serialize()); //CHECK
                    }
                    catch (IOException ex) {
                        System.out.print("Invalid Path");
                    }
                    System.out.println("username: "+yorname+", password: "+psw);
                    break;

                case LOGIN:
                    if (!users.checkUname(recMsg.topics[0])){
                        reply = new Message(null,MessageType.ERROR,null,"Account does not exist!");
                    }else{
                        if(recMsg.content.equals(users.getHashPw(recMsg.topics[0]))){
                            System.out.println("passwords match"); //CHECK
                            //if(topix.get(address).removeIf(topic -> topic.equals(recMsg.topics[0]))){/*SUS*/}else{topix.get(address).add(recMsg.topics[0]);}
                            topix.get(address).add(recMsg.topics[0]);
                            System.out.println(topix.get(address)); //CHECK
                            reply = new Message(null,MessageType.SUCCESS,null,null);
                        }else{
                            System.out.println("wrong password");
                            reply = new Message(null,MessageType.ERROR,null,"Wrong Password!");
                        }
                    }
                    System.out.println(topix);
                    break;

                case UPDATE:
                    for(String t : recMsg.topics){
                        if(topix.get(address)!=null){
                            if(topix.get(address).removeIf(topic -> topic.equals(t))){/*SUS*/}else{topix.get(address).add(t);}
                        }else{
                            topix.put(address, new ArrayList<String>());
                            topix.get(address).add(t);
                        }
                    }
                    reply = new Message(null,MessageType.SUCCESS,null,null);
                    System.out.println(topix);
                    break;

                case TEXT:
                    reply = new Message(null,MessageType.SUCCESS,null,null);
                    byte[] bigByte = recMsg.serialize().getBytes();
                    ArrayList<InetAddress> sent = new ArrayList<InetAddress>();
                    for(String t2 : recMsg.topics){
                        for(InetAddress i : topix.keySet()){
                            if(topix.get(i).contains(t2)){
                                if(!sent.contains(i)){
                                    sent.add(i);
                                }
                            }
                        }
                    }
                    System.out.println(recMsg.content);
                    for ( InetAddress i : sent){
                        System.out.println("SENDING address: "+ i +" port: 2417");
                        DatagramPacket bigPacket 
                        = new DatagramPacket(bigByte, bigByte.length, i, 4445);
                    try{
                        socket.send(bigPacket);
                    }catch(Exception err) {
                        System.out.println(err);
                    }
                    }
                    
                        break;
                    default:
                        reply = new Message(null,MessageType.ERROR,null,"SUS >:|");
                }

            rebuf = reply.serialize().getBytes();
            packet = new DatagramPacket(rebuf, rebuf.length, address, port);

            //constructing the package and sending it back

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
