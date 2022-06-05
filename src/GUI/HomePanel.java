import javax.swing.*;

import Server.ServerRun;

import java.awt.*;
import java.awt.event.ActionEvent;

/*
 * rivedere i bottoni server
 */

public class HomePanel extends ClassPanel{

    JPanel description, servSpace;
    JButton startServer_btn, connectServer_btn;
    String text = "Hello World!";
    final static String newline = "\n";
    ServerRun serverRun;

    public HomePanel(){
        this.title ="Home";

        this.setLayout(new FlowLayout());

        description = new JPanel();
        servSpace = new JPanel();

        JLabel welcome = new JLabel("Benvenuto!", SwingConstants.CENTER);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 24));

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setFont(new Font("SansSerif", Font.BOLD, 18));
        textArea.setEditable(false);
        textArea.append(text);

        description.setLayout(new BorderLayout());
        description.add(welcome, BorderLayout.NORTH);
        description.add(textArea, BorderLayout.CENTER);
        //some code

        JLabel label = new JLabel("Lancia il server!", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));

        //btn
        startServer_btn = new JButton("Start");
        startServer_btn.addActionListener(this);
        connectServer_btn = new JButton("Connect");
        connectServer_btn.addActionListener(this);

        //add to panel
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(label, BorderLayout.NORTH);
        p.add(startServer_btn, BorderLayout.CENTER);

        servSpace.setLayout(new BorderLayout());
        servSpace.add(p, BorderLayout.CENTER);
        
        this.add(description);
        this.add(servSpace);
    }

    public void writeDescription(){

        

    }

    public void startServer(){
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == startServer_btn){

            //serverRun = new ServerRun();

        }
        
    }


}
