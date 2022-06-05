import javax.swing.*;
import java.awt.*;

public class HomePanel extends ClassPanel{

    JPanel description, servSpace;
    JButton startServer_btn;
    String text = "Hello World!";
    final static String newline = "\n";

    public HomePanel(){
        this.title ="Home";

        this.setLayout(new FlowLayout());

        description = new JPanel();
        servSpace = new JPanel();

        writeDescription();
        startServer();
        
        
    }

    public void writeDescription(){

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

    }

    public void startServer(){
        JLabel label = new JLabel("Lancia il server!", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));

        //btn
        startServer_btn = new JButton("Start");

        //add to panel
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(label, BorderLayout.NORTH);
        p.add(startServer_btn, BorderLayout.CENTER);

        servSpace.setLayout(new BorderLayout());
        servSpace.add(p, BorderLayout.CENTER);

    }


}
