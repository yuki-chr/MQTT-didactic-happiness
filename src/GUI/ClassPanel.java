import javax.swing.*;

import Client.ClientRun;

import java.awt.Color;
import java.awt.event.*;
//a generic parent class for many child classes, contents of the main window
public abstract class ClassPanel extends JPanel implements ActionListener{

    //variables
    protected String title;
    protected ClientRun cr;
    //protected Font font = new Font("SansSerif", Font.BOLD, 17);
    public ClassPanel(ClientRun cr){
        this.cr = cr;
        setBackground(Color.WHITE);
    }
    //methods
    //?
}
