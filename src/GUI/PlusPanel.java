import Common.Message;
import Common.Message.MessageType;

import java.awt.event.ActionEvent;
import java.io.IOException;

import Client.ClientRun;

public class PlusPanel extends ClassPanel {
    public PlusPanel(ClientRun cr) {
        super(cr);
    }

    static void editTopics(String t, ClientRun cr){
        if(t!=null){
            String[] ts = t.split(" ");
            Message m = new Message(null,MessageType.UPDATE,ts,"");
            try{cr.sendMessage(m);}catch(IOException e){System.out.println("IOExc. " + e);}
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
