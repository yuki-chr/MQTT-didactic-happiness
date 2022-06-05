import java.awt.*;
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 

public class Test { 
    public static void main(String[] args) { 
        JFrame frame = new JFrame(); 
        JPanel panel = new JPanel(); 
        panel.setLayout(new GridLayout(0,1));
        for (int i = 0; i < 10; i++) { 
            panel.add(new JButton("Hello-" + i)); 
        } 
        JScrollPane scrollPane = new JScrollPane(panel); 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scrollPane.setBounds(50, 30, 100, 100); 
        JPanel contentPane = new JPanel(null); 
        contentPane.setPreferredSize(new Dimension(500, 400)); 
        contentPane.add(scrollPane); 
        frame.setContentPane(contentPane); 
        frame.pack(); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frame.setVisible(true); 
    } 
} 