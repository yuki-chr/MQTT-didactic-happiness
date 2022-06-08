import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Common.Message.MessageType;
import Common.Message;
import Client.ClientRun;

public class SendMessagesPanel extends ClassPanel {
    
    JButton sendMsg_btn;
    JTextField topic;
    JTextArea text;

    public SendMessagesPanel(ClientRun cr) {
        super(cr);

        this.setLayout(new GridBagLayout());

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        // topic input
        
        JLabel topicL = new JLabel("Topic: ");
        p.add(topicL);
        topic = new JTextField(10);
        p.add(topic);

        // content input
        text = new JTextArea(5, 20);
        text.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 600, 400);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(text);
        

        // btn
        sendMsg_btn = new JButton("send");
        sendMsg_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(sendMsg_btn);

        // adding
        p.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        this.add(p);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendMsg_btn) {
            Message toSend = new Message();
            toSend.type = MessageType.TEXT;
            toSend.topics = topic.getText().split(" ");
            toSend.content = text.getText();
            try {
                cr.sendMessage(toSend);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
}
