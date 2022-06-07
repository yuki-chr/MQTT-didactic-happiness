import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
 
public class Test extends JFrame {
 
    public Test() {
 
  super();
 
  MenuListener listener = new MenuListener() {
 
@Override
 
public void menuCanceled(MenuEvent event) {
 
    printEcentInfo("Canceled", event);
 
}
 
@Override
 
public void menuDeselected(MenuEvent event) {
 
    printEcentInfo("Deselected", event);
 
}
 
@Override
 
public void menuSelected(MenuEvent event) {
 
    printEcentInfo("Selected", event);
 
}
 
private void printEcentInfo(String s, MenuEvent event) {
 
    JMenu menu = (JMenu) event.getSource();
 
    System.out.println(s + ": " + menu.getText());
 
}
 
  };
 
  JMenu fMenu = new JMenu("File");
 
  fMenu.addMenuListener(listener);
 
  fMenu.add(new JMenuItem("Open"));
 
  fMenu.add(new JMenuItem("Close"));
 
  fMenu.add(new JMenuItem("Exit"));
 
  JMenu hmenu = new JMenu("Help");
 
  hmenu.addMenuListener(listener);
 
  hmenu.add(new JMenuItem("About MenuTest"));
 
  hmenu.add(new JMenuItem("Class Hierarchy"));
 
  hmenu.addSeparator();
 
  hmenu.add(new JCheckBoxMenuItem("More Help"));
 
  JMenu sub = new JMenu("Categories");
 
  sub.addMenuListener(listener);
 
  JRadioButtonMenuItem radioMenu;
 
  ButtonGroup buttonGroup = new ButtonGroup();
 
  sub.add(radioMenu = new JRadioButtonMenuItem("Some Help", true));
 
  buttonGroup.add(radioMenu);
 
  sub.add(radioMenu = new JRadioButtonMenuItem("Help"));
 
  buttonGroup.add(radioMenu);
 
  hmenu.add(sub);
 
  JMenuBar menuBar = new JMenuBar();
 
  menuBar.add(fMenu);
 
  menuBar.add(hmenu);
 
  setJMenuBar(menuBar);
    }
 
    public static void main(String args[]) {
 
  JFrame jFrame = new Test();
 
  jFrame.setSize(600, 400);
 
  jFrame.setVisible(true);
    }
}