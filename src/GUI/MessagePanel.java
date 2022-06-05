import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.*;

public class MessagePanel extends ClassPanel {

    JPanel p1;
    

    public MessagePanel(){
        this.title = "Messages";

        p1 = new JPanel();

        p1.setLayout(new GridLayout(0,1));
        for (int i = 0; i < 10; i++) { 
            p1.add(new JButton("")); 
        } 
        JScrollPane scrollPane = new JScrollPane(p1); 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setBounds(50, 30, 600, 400); 
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
