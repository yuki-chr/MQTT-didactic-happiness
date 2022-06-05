import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.*;

public class Window implements ActionListener{

    static JFrame frame;
    JMenuBar mb;
    JMenu mHome, mMess, mServer, mPlus, mSettings;
    JMenuItem plusMess, plusTop;

    static HomePanel home;
    static MessagePanel messages;
    static ServerPanel server;
    static PlusPanel plus;
    static SettingsPanel settings;

    public Window(ClassPanel panel){
        //this.panel = panel;
        frame = new JFrame(panel.title);
        frame.setSize(Util.width, Util.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //menu
        mb = new JMenuBar();

        mHome = new JMenu("Home");
        mMess = new JMenu("Messages");
        mServer = new JMenu("Server");
        mPlus = new JMenu(" + ");
        mSettings = new JMenu("Settings");

        plusMess = new JMenuItem("New Message");
        plusTop = new JMenuItem("New Topic");

        mHome.addActionListener(this);
        mMess.addActionListener(this);
        mServer.addActionListener(this);
            plusMess.addActionListener(this);
            plusTop.addActionListener(this);
        mSettings.addActionListener(this);

        mPlus.add(plusMess);
        mPlus.add(plusTop);

        mb.add(mHome);
        mb.add(mMess);
        mb.add(mServer);
        mb.add(mPlus);
        mb.add(mSettings);

        frame.setJMenuBar(mb);

        //initialise panels
        //home = new HomePanel(cr);
        //messages = new MessagePanel();
        //TestPanel test = new TestPanel();
        //server = new ServerPanel();
        //plus = new PlusPanel();
        //settings = new SettingsPanel();

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
        // TODO Auto-generated method stub
        if(e.getSource() == mHome){

            replaceContent(home);

        }else if(e.getSource() == mMess){

            replaceContent(messages);

        }else if(e.getSource() == mServer){

           replaceContent(server);

        }else if(e.getSource() == plusMess){

            //plus.editMessage(t);

        }else if(e.getSource() == plusTop){

            //some code
            String getMessage = JOptionPane.showInputDialog(frame, "Enter your message");
            JOptionPane.showMessageDialog(frame, "Your message: "+getMessage);
            //plus.editTopics(t);

        }else if(e.getSource() == mSettings){

            //replaceContent(settings);

        }
    }


    public static void main(String[] args) {
        home = new HomePanel(null);
        new Window(home);
    }
}
