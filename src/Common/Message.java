import Gson;

public class Message {
    String ip;
    String type;
    String[] topics;
    String content;

    Gson gson = new Gson();

    public String serialize(){
        return gson.toJson(this);
    }

    public Message(String ip, String type, String[] topics, String content){
        this.ip = ip;
        this.type = type;
        this.topics = topics;
        this.content = content;
    }

    public Message(String json){
        //hmmmm SUS
        this = gson.fromJson(json, Message.class);
    }


}
