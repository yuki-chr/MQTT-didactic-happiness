import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Client.ClientRun;
import Server.ServerRun;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * rivedere i bottoni server
 * 
 * homepanel deve inizializzare ClientRun
 */

public class HomePanel extends ClassPanel{

    JPanel description, servSpace;
    JButton startServer_btn, connectServer_btn;
    JTextField ip_input, port_input;
    String text = "Hello World!\nHello World!\nHello World!\nHello World!\nHello World!\nHello World!\nHello World!\nHello World!\nHello World!\nHello World!\nHello World!\nHello World!\nHello World!\n";
    //final static String newline = "\n";
    ServerRun serverRun;

    public HomePanel(ClientRun cr){
        super(cr);
        this.title ="Home";
        this.setLayout(new FlowLayout());



        //description panel
            description = new JPanel();
            description.setBorder(new EmptyBorder(5, 5, 5, 5));
            description.setBackground(Util.bg);

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
            servSpace = new JPanel();
            servSpace.setLayout(new BorderLayout());
            servSpace.setBorder(new EmptyBorder(5, 5, 5, 5));
            

            //set server
                JPanel connectP = new JPanel();
                connectP.setLayout(new BorderLayout());

                JLabel l1 = new JLabel("Collegati a un server!", SwingConstants.CENTER);
                l1.setFont(new Font("SansSerif", Font.BOLD, 16));
                l1.setBackground(Util.bg);
                connectP.add(l1, BorderLayout.NORTH);

                ip_input = new JTextField(10);
                port_input = new JTextField(10);
                connectServer_btn = new JButton("Connetti");
                

                // Add labelled input fields to display
                JPanel inFieldPane = new JPanel();
                inFieldPane.setLayout(new GridLayout(2,2));
                inFieldPane.add(new JLabel("IP: "));
                inFieldPane.add(ip_input);
                ip_input.addActionListener(this);
                inFieldPane.add(new JLabel("Port: "));
                inFieldPane.add(port_input);
                port_input.addActionListener(this);
                connectP.add(inFieldPane, BorderLayout.CENTER);

                // Add submission button
                JPanel submitPane = new JPanel();
                submitPane.setLayout(new FlowLayout());
                connectServer_btn.addActionListener(this);
                submitPane.add(connectServer_btn);
                connectP.add(submitPane, BorderLayout.SOUTH);
            
                // Add Output fields
                /*
                * check se esiste veramente il server, guardare i ping
                */

            //create server
                JPanel createP = new JPanel();
                createP.setLayout(new BorderLayout());

                JLabel l2 = new JLabel("Crea un server!", SwingConstants.CENTER);
                l2.setFont(new Font("SansSerif", Font.BOLD, 16));
                l2.setBackground(Util.bg);
                createP.add(l2, BorderLayout.NORTH);

                //ip_output = new JTextField(10);
                //port_output = new JTextField(10);
                startServer_btn = new JButton("Start");

                // Add submission button
                JPanel submitPane2 = new JPanel();
                submitPane2.setLayout(new FlowLayout());
                startServer_btn.addActionListener(this);
                submitPane2.add(startServer_btn);
                createP.add(submitPane2, BorderLayout.CENTER);

                // Add Output fields
                /*
                * check se esiste veramente il server, guardare i ping
                */

            connectP.setBorder(BorderFactory.createLineBorder(Color.black));
            createP.setBorder(BorderFactory.createLineBorder(Color.black));
            servSpace.add(connectP, BorderLayout.NORTH);
            servSpace.add(createP, BorderLayout.CENTER);
            
        
        //add to big panel   
        this.add(description);
        this.add(servSpace);
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == connectServer_btn){

            try {
                InetAddress ip = InetAddress.getByName(ip_input.getText().trim());
                int port = Integer.parseInt(port_input.getText().trim());
                cr.startClient(ip, port);
                cr.start(); //the pingerrrrr
                Window.replaceContent(Window.messages);

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            }

        }else if(e.getSource() == startServer_btn){
            ServerRun server = new ServerRun();
            server.start();
        }
        
    }


}
