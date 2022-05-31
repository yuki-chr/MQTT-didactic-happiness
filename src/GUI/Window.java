import javax.swing.JFrame;

public class Window{
    public Window(ClassPanel panel){
        JFrame frame = new JFrame(panel.title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //set the start panel as content pane
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
