import java.awt.event.ActionEvent;
import java.util.ArrayList;
//import java.awt.*;

import javax.swing.*;

import Client.ClientRun;
import Common.Message;
/*
 * qui vengono visualizzati i messaggi grazie a un panel con un jScrollPane.
 * i messaggi ricevuti devono essere presentati in ordine, e devono indicare:
 * topic, content, etc...
 * 
 * i messaggi vengono ricevuti dal thread nel ClientRun.
 * 
 * 
 */
public class MessagePanel extends ClassPanel implements Runnable{

    JPanel p1;
    JButton sendMessage_btn;

    public MessagePanel(ClientRun cr){
        super(cr);
        this.title = "Messages";

        p1 = new JPanel();
        p1.setSize(500, 500);
        

        //p1.setLayout(new GridLayout());
        //p1.add(new JLabel("Hello World", SwingConstants.CENTER));

        

        JScrollPane scrollPane = new JScrollPane(p1);
        scrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
        scrollPane.setAlignmentY(JScrollPane.CENTER_ALIGNMENT);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setBounds(50, 30, 600, 400);
        p1.add(new JTextArea("text"));

        this.add(p1);
        
        JPanel sendP = new JPanel();
        sendMessage_btn = new JButton("Scrivi Messaggio");
        sendMessage_btn.addActionListener(this);
        sendMessage_btn.setAlignmentX(JScrollPane.RIGHT_ALIGNMENT);
        sendMessage_btn.setAlignmentY(JScrollPane.BOTTOM_ALIGNMENT);
        sendP.add(sendMessage_btn);
        this.add(sendP);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sendMessage_btn){
            Window.replaceContent(Window.sendmessages);
        }
        
    }

    public void displayMessage(Message mess){
        JPanel panel = new JPanel();
        //some code to display message data here
        
        p1.add(panel);
    }


    @Override
    public void run() {
        boolean running = true;
        ArrayList<String> logMessage = new ArrayList<String>();
        while(running){
            logMessage.addAll(cr.log(logMessage.size()));
        }

    }
    
    
}
