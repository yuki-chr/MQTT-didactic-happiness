package Common;

import Common.Message.MessageType;

public class Accounts {
    public static Message register(String name, String password){
        Message m = new Message();
        m.topics = new String[1];

        m.type = MessageType.REGISTER;
        m.topics[0] = name;
        m.content = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

        return m;
    }
    public static Message login(String name, String password){
        Message m = new Message();
        m.topics = new String[1];

        m.type = MessageType.LOGIN;
        m.topics[0] = name;
        m.content = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

        return m;
    }
}
