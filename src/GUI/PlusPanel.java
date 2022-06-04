import Common.Message;
import Common.Message.MessageType;
import Client.ClientRun;

public class PlusPanel extends ClassPanel {
    static void editTopics(String t){
        String[] ts = t.split(" ");
        Message m = new Message("",MessageType.UPDATE,ts,"");
        ClientRun cr = new ClientRun();
        cr.sendMessage(m);
    }
}
