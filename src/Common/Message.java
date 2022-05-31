package Common;

import com.google.gson.Gson;

public class Message {
    String ip;
    String type;
    String[] topics;
    String content;

    Gson gson = new Gson();

    public String serialize(){
        return gson.toJson(this);
    }

    public Message(){
        //just for json constructor
    }

    public Message(String ip, String type, String[] topics, String content){
        this.ip = ip;
        this.type = type;
        this.topics = topics;
        this.content = content;
    }

    public Message(String json){
        Message temp = new Message();
        temp = gson.fromJson(json, Message.class);
        this.ip = temp.ip;
        this.type = temp.type;
        this.topics = temp.topics;
        this.content = temp.content;
    }


}
