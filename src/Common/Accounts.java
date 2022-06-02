package Common;

import Common.Message.MessageType;

public class Accounts {
    public static Message register(String name, String password){
        Message m = new Message();

        m.type = MessageType.REGISTER;
        m.topics[0] = "u/" + name;
        m.content = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

        return m;
    }
}
