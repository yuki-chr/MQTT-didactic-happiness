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
        JTextArea textArea = new JTextArea(5, 20);
        //JScrollPane scrollPane = new JScrollPane(textArea); 
        textArea.setEditable(false);
        textArea.append(text);
    }

    public void editServPanel(){
        
    }


}
