import javax.swing.JFrame;

public class Window{

    JFrame frame;
    //JPanel panel;

    public Window(ClassPanel panel){
        //this.panel = panel;
        frame = new JFrame(panel.title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

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
}
