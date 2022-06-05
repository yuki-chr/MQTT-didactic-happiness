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
        return gson.toJson(this);
    }

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
        Message temp = new Message();
        temp = gson.fromJson(json, Message.class);
        this.ip = temp.ip;
        this.type = temp.type;
        this.topics = temp.topics;
        this.content = temp.content;
    }


}