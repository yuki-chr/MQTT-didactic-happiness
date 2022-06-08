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
import javax.swing.border.CompoundBorder;

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
        JPanel t = new JPanel(new GridLayout(1,2));
        t.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JLabel topicL = new JLabel("Topic: ");
        t.add(topicL);
        topic = new JTextField(10);
        t.add(topic);
        p.add(t);

        // content input
        text = new JTextArea(5, 20);
        text.setBorder((BorderFactory.createLineBorder(Color.black)));
        text.setEditable(true);
        text.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(scrollPane);
        

        // btn
        sendMsg_btn = new JButton("send");
        sendMsg_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendMsg_btn.addActionListener(this);
        p.add(sendMsg_btn);

        // adding
        p.setBorder(new CompoundBorder((BorderFactory.createLineBorder(Color.black)), BorderFactory.createEmptyBorder(10, 20, 10, 20)));
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
