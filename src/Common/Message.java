package Common;

import com.google.gson.Gson;
import java.net.*;

public class Message {
    public InetAddress ip;
    public MessageType type;
    public String[] topics;
    public String content;
    //any programmer looking in here don't kill me

    public enum MessageType{
        PING,
        ACK,
        ERROR,
        SUCCESS,
        REGISTER,
        LOGIN,
        UPDATE,
        TEXT
    }

    Gson gson = new Gson();

    public String serialize(){
        StringBuilder sb = new StringBuilder();
        
        //ip
        sb.append(ip.toString()+"\n");
        
        //type
        String typeTemp;
        switch (type) {
            case PING:
                typeTemp = "PING";
                break;
            case ACK:
                typeTemp = "ACK";
                break;
            case ERROR:
                typeTemp = "ERROR";
                break;
            case SUCCESS:
                typeTemp = "SUCCESS";
                break;
            case REGISTER:
                typeTemp = "REGISTER";
                break;
            case LOGIN:
                typeTemp = "LOGIN";
                break;
            case UPDATE:
                typeTemp = "UPDATE";
                break;
            case TEXT:
                typeTemp = "TEXT";
                break;
            default:
                typeTemp = "PING";
        }
        sb.append(typeTemp+"\n");

        //topics
        for(int i = 0; i < topics.length; i++){
            sb.append(topics[i]);
            if(i < topics.length - 1) 
                sb.append(",");
            else
                sb.append("\n");            
        }
        //content
        sb.append(content);
        //return
        return sb.toString();
    }

    /*ip\ntype\ntopic1,topic2,...,topicn\ncontent*/

    public Message(){
        //just for json constructor and well when you need an empty Message
    }

    //use when creating a new Message
    public Message(InetAddress ip, MessageType type, String[] topics, String content){
        this.ip = ip;
        this.type = type;
        this.topics = topics;
        this.content = content;
    }

    //use when receiving Message as String
    public Message(String json){
        String[] s = json.split("\n");
        
        //ip
        try {
            this.ip = InetAddress.getByName(s[0]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        
        //type
        if(s[1].equals("PING")){
            this.type = MessageType.PING;
        }else if(s[1].equals("ACK")){
            this.type = MessageType.ACK;      
        }else if(s[1].equals("ERROR")){
            this.type = MessageType.ERROR;
        }else if(s[1].equals("SUCCESS")){
            this.type = MessageType.SUCCESS;
        }else if(s[1].equals("REGISTER")){
            this.type = MessageType.REGISTER;
        }else if(s[1].equals("LOGIN")){
            this.type = MessageType.LOGIN;
        }else if(s[1].equals("UPDATE")){
            this.type = MessageType.UPDATE;
        }else if(s[1].equals("TEXT")){
            this.type = MessageType.TEXT;
        }
    }
}