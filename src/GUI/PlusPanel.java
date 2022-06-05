import Common.Message;
import Common.Message.MessageType;

import java.awt.event.ActionEvent;

import Client.ClientRun;

public class PlusPanel extends ClassPanel {
    static void editTopics(String t){
        String[] ts = t.split(" ");
        Message m = new Message("",MessageType.UPDATE,ts,"");
        ClientRun cr = new ClientRun();
        cr.sendMessage(m);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
