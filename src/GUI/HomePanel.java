import javax.swing.*;

import Client.ClientRun;
import Server.ServerRun;

import java.awt.*;
import java.awt.event.ActionEvent;

/*
 * rivedere i bottoni server
 * 
 * homepanel deve inizializzare ClientRun
 */

public class HomePanel extends ClassPanel{

    JPanel description, servSpace;
    JButton startServer_btn, connectServer_btn;
    String text = "Hello World!";
    final static String newline = "\n";
    ServerRun serverRun;

    public HomePanel(ClientRun cr){
        super(cr);
        this.title ="Home";
        this.setLayout(new FlowLayout());

        

        //description panel
        description = new JPanel();
        description.setBackground(Util.bg);
        description.setSize(Util.width-50, Util.height-50);
        
        
        servSpace = new JPanel();
        JLabel welcome = new JLabel("Benvenuto!", SwingConstants.CENTER);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcome.setBackground(Util.bg);

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setFont(new Font("SansSerif", Font.BOLD, 18));
        textArea.setBorder(BorderFactory.createLineBorder(Color.black));
        textArea.setEditable(false);
        textArea.append(text);

        description.setLayout(new BorderLayout());
        description.add(welcome, BorderLayout.NORTH);
        description.add(textArea, BorderLayout.CENTER);

        //server part
        JLabel launch = new JLabel("Lancia il server!", SwingConstants.CENTER);
        launch.setFont(new Font("SansSerif", Font.BOLD, 18));
        launch.setBackground(Util.bg);

        startServer_btn = new JButton("Start");
        startServer_btn.addActionListener(this);
        connectServer_btn = new JButton("Connect");
        connectServer_btn.addActionListener(this);
        JPanel btn_p = new JPanel();
        btn_p.setBackground(Util.bg);
        btn_p.add(startServer_btn);
        btn_p.add(connectServer_btn);

        JPanel p = new JPanel();
        p.setBackground(Util.bg);
        p.setLayout(new BorderLayout());
        p.add(launch, BorderLayout.CENTER);
        p.add(btn_p, BorderLayout.SOUTH);

        servSpace.setLayout(new BorderLayout());
        servSpace.add(p, BorderLayout.CENTER);

        //add to big panel   
        this.add(description);
        this.add(servSpace);
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == startServer_btn){

            //serverRun = new ServerRun();

        }
        
    }


}
