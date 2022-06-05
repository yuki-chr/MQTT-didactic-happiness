import javax.swing.*;
import java.awt.*;

public class HomePanel extends ClassPanel{

    JPanel description, editServ;
    String text;
    final static String newline = "\n";

    public HomePanel(){
        this.title ="Home";

        this.setLayout(new FlowLayout());

        description = new JPanel();
        editServ = new JPanel();

        writeDescription();
        editServPanel();
        
        
    }

    public void writeDescription(){

        JLabel welcome = new JLabel("Benvenuto!", SwingConstants.CENTER);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 24));

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setFont(new Font("SansSerif", Font.BOLD, 18));
        textArea.setEditable(false);
        textArea.append(text);
        
        //some code 
    }

    public void editServPanel(){

    }


}
