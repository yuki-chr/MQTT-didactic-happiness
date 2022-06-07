import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Client.ClientRun;
import Common.Accounts;
import Common.Message;

import java.awt.event.*;
import java.io.IOException;

public class Window implements ActionListener{

    static JFrame frame;
    JMenuBar mb;
    JMenuItem mHome, mMess, mServer, mPlus, mSettings, mLogin, mRegister;
    JMenuItem plusMess, plusTop;

    static HomePanel home;
    static MessagePanel messages;
    static SendMessagePanel sendmessages;
    static ServerPanel server;
    static PlusPanel plus;
    static SettingsPanel settings;
    static ClientRun cr;

    public Window(ClassPanel panel){
        //this.panel = panel;
        frame = new JFrame(panel.title);
        frame.setSize(Util.width, Util.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //menu
        mb = new JMenuBar();

        mHome = new JMenuItem("Home");
        mMess = new JMenuItem("Messages");
        mServer = new JMenuItem("Server");
        mPlus = new JMenu(" + ");
            plusMess = new JMenuItem("New Message");
            plusTop = new JMenuItem("New Topic");
        mSettings = new JMenuItem("Settings");
        mLogin = new JMenuItem("Login");
        mRegister = new JMenuItem("Register");

        mHome.addActionListener(this);
        mMess.addActionListener(this);
        mServer.addActionListener(this);
            plusMess.addActionListener(this);
            plusTop.addActionListener(this);
        mLogin.addActionListener(this);
        mRegister.addActionListener(this);
        mSettings.addActionListener(this);

        mPlus.add(plusMess);
        mPlus.add(plusTop);

        mb.add(mHome);
        mb.add(mMess);
        mb.add(mServer);
        mb.add(mPlus);
        mb.add(mLogin);
        mb.add(mRegister);
        mb.add(mSettings);

        frame.setJMenuBar(mb);

        //set the start panel as content pane
        frame.setContentPane(panel);
        //frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    //method to refresh window: it replaces old panel with a new one
    public static void replaceContent(ClassPanel newPanel){
        frame.getContentPane().removeAll();
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
           replaceContent(server);
           

        }else if(e.getSource() == plusMess){

            //plus.editMessage(t);

        }else if(e.getSource() == plusTop){

            //some code
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
        sendmessages = new SendMessagePanel(cr);
        home = new HomePanel(cr);
        new Window(home);
    }
}
