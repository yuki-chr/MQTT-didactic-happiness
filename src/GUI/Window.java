import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import Client.ClientRun;
import Common.Accounts;
import Common.Message;
import Server.ServerRun;

import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Window implements ActionListener{

    static JFrame frame;
    JMenuBar mb;
    JToggleButton mHome, mMess, mServer, mSettings, mLogin, mRegister;
    JMenu mPlus;
    JMenuItem plusMess, plusTop;

    static HomePanel home;
    static MessagePanel messages;
    static SendMessagesPanel sendmessages;
    static ServerPanel server;
    static PlusPanel plus;
    static SettingsPanel settings;

    static ClientRun cr;
    static ServerRun sr;

    public Window(ClassPanel panel){
        //this.panel = panel;
        frame = new JFrame(panel.title);
        frame.setSize(Util.width, Util.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //menu
        mb = new JMenuBar();

        //toggle buttons
        mHome = new JToggleButton("Home");
        mMess = new JToggleButton("Messages");
        mServer = new JToggleButton("Server");
        mPlus = new JMenu(" + ");
            plusMess = new JMenuItem("New Message");
            plusTop = new JMenuItem("New Topic");
        mSettings = new JToggleButton("Settings");
            mSettings.setEnabled(false);    //not done yet
        mLogin = new JToggleButton("Login");
        mRegister = new JToggleButton("Register");

        //button group
        ButtonGroup bg = new ButtonGroup();
        bg.add(mHome);
        bg.add(mMess);
        bg.add(mServer);
        bg.add(mLogin);
        bg.add(mRegister);
        bg.add(mSettings);
        bg.add(mPlus);

        //actionlisteners
        mHome.addActionListener(this);
        mMess.addActionListener(this);
        mServer.addActionListener(this);
            plusMess.addActionListener(this);
            plusTop.addActionListener(this);
        mLogin.addActionListener(this);
        mRegister.addActionListener(this);
        mSettings.addActionListener(this);

        //adding
        mPlus.add(plusMess);
        mPlus.add(plusTop);

        mb.add(mHome);
        mb.add(mMess);
        mb.add(mServer);
        mb.add(mLogin);
        mb.add(mRegister);
        mb.add(mSettings);
        mb.add(mPlus);

        frame.setJMenuBar(mb);

        //set the start panel as content pane
        frame.setContentPane(panel);
        //frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    //method to refresh window: it replaces old panel with a new one
    public static void replaceContent(ClassPanel newPanel){
        //frame.getContentPane().removeAll();   //so it can back to others panels
        frame.setContentPane(newPanel);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mHome){
            System.out.println("home");
            replaceContent(home);
            

        }else if(e.getSource() == mMess){
            System.out.println("messages");
            replaceContent(messages);
            

        }else if(e.getSource() == mServer){
            System.out.println("server");
            a:{
                String[] options = {"Create", "Connect"};
                String[] title ={"New Server", "Connect to Server"};
                int x = JOptionPane.showOptionDialog(frame, "Do you want to create\n a new server or connect\n to an existing one?",
                    "Server options",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

                if (x != -1) {
                    System.out.println("Your choice was " + options[x]);
                } else {
                    System.out.println(":( no choice");
                    break a;    //exit the block
                }
                
                if(x == 0){
                    sr = new ServerRun();
                    sr.start();
                    
                }else if(x == 1){
                    try {
                        String getIP = JOptionPane.showInputDialog(frame, "IP: ");
                        String getPort = JOptionPane.showInputDialog(frame, "Port: ");
                        JOptionPane.showMessageDialog(frame, ("IP: "+getIP +"\nPort: "+getPort), title[x], JOptionPane.INFORMATION_MESSAGE);
                        InetAddress ip = InetAddress.getByName(getIP);
                        int port = Integer.parseInt(getPort);
                        cr.startClient(ip, port);
                        cr.start(); //the pingerrrrr
                        replaceContent(messages);
        
                    } catch (UnknownHostException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            
            
           

        }else if(e.getSource() == plusMess){
            
            //plus.editMessage(t);

        }else if(e.getSource() == plusTop){

            String getMessage = JOptionPane.showInputDialog(frame, "Add a new topic\n(already existing topics will be removed)");
            JOptionPane.showMessageDialog(frame, "New topic: "+ getMessage);
            plus.editTopics(getMessage, cr);

        }else if(e.getSource() == mLogin){
            System.out.println("login");  
            String getUsername = JOptionPane.showInputDialog(frame, "Username:");
            String getPassword = JOptionPane.showInputDialog(frame, "Password:");
            Message login = Accounts.login(getUsername, getPassword);
            try {
                cr.sendMessage(login);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else if(e.getSource() == mRegister){
            System.out.println("register");  
            String getUsername = JOptionPane.showInputDialog(frame, "Username:");
            String getPassword = JOptionPane.showInputDialog(frame, "Password:");
            Message register = Accounts.register(getUsername, getPassword);
            try {
                cr.sendMessage(register);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
        }else if(e.getSource() == mSettings){

            //replaceContent(settings);

        }
    }


    public static void main(String[] args) {
        cr = new ClientRun();
        //cr.start(); //THE PINGEEEEEEERRRRR
        messages = new MessagePanel(cr);
        sendmessages = new SendMessagesPanel(cr);
        home = new HomePanel(cr);
        plus = new PlusPanel(cr);
        new Window(home);
    }
}
