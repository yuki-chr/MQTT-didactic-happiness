import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import Client.ClientRun;
import Common.Message;
import Common.Message.MessageType;
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

    JPanel messPanel;
    JButton sendMessage_btn;

    public MessagePanel(ClientRun cr){
        super(cr);
        this.title = "Messages";
        this.setLayout(new BorderLayout());

        messPanel = new JPanel();
        messPanel.setSize(500, 500);
        messPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        messPanel.setLayout(new GridLayout(0,1));

        JScrollPane scrollPane = new JScrollPane(messPanel);
        scrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
        scrollPane.setAlignmentY(JScrollPane.CENTER_ALIGNMENT);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setBounds(50, 30, 600, 400);

        JPanel sendP = new JPanel();
        sendMessage_btn = new JButton("Scrivi Messaggio");
        sendMessage_btn.addActionListener(this);
        sendMessage_btn.setAlignmentX(JScrollPane.RIGHT_ALIGNMENT);
        sendMessage_btn.setAlignmentY(JScrollPane.BOTTOM_ALIGNMENT);
        sendP.add(sendMessage_btn);
        
        this.add(new JLabel("RECEIVED MESSAGES ", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(messPanel, BorderLayout.CENTER);
        this.add(sendP, BorderLayout.SOUTH);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sendMessage_btn){
            
            System.out.println("send message panel");
            Window.replaceContent(Window.sendmessages);
        }
        
    }

    public void displayMessage(String s){
        
        Message m = new Message(s);
        //some code to display message data here
        if(m.type == MessageType.TEXT){
            JPanel newMess = new JPanel();
            newMess.setLayout(new BorderLayout());
            JPanel top = new JPanel();
            top.setLayout(new BorderLayout());
            JLabel ipL = new JLabel("IP: " + m.ip.getHostAddress());
            JLabel topicL = new JLabel();
            String topicS = "Topics: ";
            for(int i = 0; i < m.topics.length; i++){
                topicS += m.topics[i];
                if(i < m.topics.length - 1) 
                topicS += (", ");     
                else
                topicS += ("."); 
            }
            topicL.setText(topicS);
            top.add(ipL, BorderLayout.NORTH);
            top.add(topicL, BorderLayout.CENTER);
            JTextArea text = new JTextArea(m.content);
            top.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
            newMess.add(top, BorderLayout.NORTH);
            newMess.add(text, BorderLayout.CENTER);
            messPanel.add(newMess);
            messPanel.revalidate();
            messPanel.repaint();
        }
    }


    @Override
    public void run() {
        boolean running = true;
        ArrayList<String> logMessage = new ArrayList<String>();
        
        while(running){
            ArrayList<String> tempMessage = new ArrayList<String>(cr.log(logMessage.size()));
            if(!(tempMessage.isEmpty())){
                logMessage.addAll(tempMessage);
                for(String s: tempMessage){
                    displayMessage(s);
                }
            }
            
        }
    }
}
