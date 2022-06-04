import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.*;

public class Window implements ActionListener{

    JFrame frame;
    JMenuBar mb;
    JMenu mHome, mTopic, mServer, mPlus, mSettings;
    JMenuItem plusMess, plusTop;

    HomePanel home;
    TopicPanel topic;
    ContactsPanel contacts;
    ServerPanel server;
    PlusPanel plus;
    SettingsPanel settings;

    public Window(ClassPanel panel){
        //this.panel = panel;
        frame = new JFrame(panel.title);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //menu
        mb = new JMenuBar();

        mHome = new JMenu("Home");
        mTopic = new JMenu("Topic");
        mServer = new JMenu("Server");
        mPlus = new JMenu(" + ");
        mSettings = new JMenu("Settings");

        plusMess = new JMenuItem("New Message");
        plusTop = new JMenuItem("New Topic");

        mHome.addActionListener(this);
        mTopic.addActionListener(this);
        mServer.addActionListener(this);
            plusMess.addActionListener(this);
            plusTop.addActionListener(this);
        mSettings.addActionListener(this);

        mPlus.add(plusMess);
        mPlus.add(plusTop);

        mb.add(mHome);
        mb.add(mTopic);
        mb.add(mServer);
        mb.add(mPlus);
        mb.add(mSettings);

        frame.setJMenuBar(mb);

        //initialise panels
        home = new HomePanel();
        topic = new TopicPanel();
        contacts = new ContactsPanel();
        server = new ServerPanel();
        plus = new PlusPanel();
        settings = new SettingsPanel();

        //set the start panel as content pane
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    //method to refresh window: it replaces old panel with a new one
    public void replaceContent(ClassPanel newPanel){
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

        }else if(e.getSource() == mTopic){

            replaceContent(topic);

        }else if(e.getSource() == mServer){

            replaceContent(server);

        }else if(e.getSource() == plusMess){

            replaceContent(messenger);

        }else if(e.getSource() == plusTop){

            plus.editTopics(t);

        }else if(e.getSource() == mSettings){

            replaceContent(settings);

        }
    }
}
