import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Common.Message.MessageType;
import Common.Message;
import Client.ClientRun;

public class SendMessagePanel extends ClassPanel {

    JButton sendMsg_btn;
    JTextField topic;
    JTextArea text;

    public SendMessagePanel(ClientRun cr) {
        super(cr);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(0, 1));
        // topic input
        JPanel t = new JPanel();
        t.setLayout(new FlowLayout());
        t.add(new JLabel("Topic: "));
        topic = new JTextField(10);
        t.add(topic);

        // content input
        JPanel c = new JPanel();
        text = new JTextArea(5, 20);
        text.setEditable(true);
        c.add(text);
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 600, 400);
        // btn
        sendMsg_btn = new JButton("send");
        // adding
        p.add(topic);
        p.add(text);
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
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
