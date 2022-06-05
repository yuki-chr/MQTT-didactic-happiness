import Common.Message;
import Common.Message.MessageType;

import java.awt.event.ActionEvent;

import Client.ClientRun;

public class PlusPanel extends ClassPanel {
    public PlusPanel(ClientRun cr) {
        super(cr);
        //TODO Auto-generated constructor stub
    }

    static void editTopics(String t, ClientRun cr){
        String[] ts = t.split(" ");
        Message m = new Message(null,MessageType.UPDATE,ts,"");
        cr.sendMessage(m);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
