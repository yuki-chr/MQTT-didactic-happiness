import java.awt.event.ActionEvent;
import java.awt.*;

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
public class MessagePanel extends ClassPanel {

    JPanel p1;
    

    public MessagePanel(ClientRun cr){
        super(cr);
        this.title = "Messages";

        p1 = new JPanel();
        p1.setSize(500, 500);
        

        p1.setLayout(new GridLayout(0,1));
        p1.add(new JLabel("Hello World", SwingConstants.CENTER));


        JScrollPane scrollPane = new JScrollPane(p1); 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setBounds(50, 30, 600, 400); 

        this.add(p1);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void displayMessage(Message mess){
        JPanel panel = new JPanel();
        //some code to display message data here
        p1.add(panel);
    }
    
    
}
