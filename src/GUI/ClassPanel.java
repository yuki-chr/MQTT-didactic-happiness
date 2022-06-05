import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
//a generic parent class for many child classes, contents of the main window
public abstract class ClassPanel extends JPanel implements ActionListener{

    //variables
    protected String title;
    protected int width = 800, height = 600;
    //protected Font font = new Font("SansSerif", Font.BOLD, 17);
    public ClassPanel(){
        setBackground(Color.WHITE);
    }
    //methods
    //?
}
