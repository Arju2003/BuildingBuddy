// Java program  to add a menubar
// and add menu items, submenu items and also add
// ActionListener and KeyListener to menu items
import javax.swing.*;
import java.awt.event.*;
public class AppMenu extends JFrame implements ActionListener, KeyListener {
    private final JMenuBar mb = new JMenuBar(); // create a menubar


    public AppMenu() {        // create an object of the class

        // create menu buttons
        JMenu start = new JMenu("Start");
        JMenu view = new JMenu("View");
        JMenu help = new JMenu("Help");
        JMenu about = new JMenu("About");
        JMenu more = new JMenu("More");
        JMenu exit = new JMenu("Exit");

        // create menu items
        JMenuItem bookmarks = new JMenuItem("" +
                "Bookmarks      CTRL+B");
        JMenuItem myLocations = new JMenuItem("" +
                "My Locations   CTRL+L");
        JMenuItem checkForUpdates = new JMenuItem("" +
                "Check for Updates   CTRL+U");
        JMenuItem developerTool = new JMenuItem("" +
                "Developer Tool        CTRL+X");


        // add ActionListener to menuItems
        bookmarks.addActionListener(this);
        myLocations.addActionListener(this);
        checkForUpdates.addActionListener(this);
        developerTool.addActionListener(this);

        // add KeyListener to menuItems
        bookmarks.addKeyListener(this);
        myLocations.addKeyListener(this);
        checkForUpdates.addKeyListener(this);
        developerTool.addKeyListener(this);


        // add menu items to menu
        view.add(bookmarks);
        view.add(myLocations);
        more.add(checkForUpdates);
        more.add(developerTool);

        // add menu to menu bar
        mb.add(start);
        mb.add(view);
        mb.add(help);
        mb.add(more);
        mb.add(about);
        mb.add(exit);
    }
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
    }

    public JMenuBar load() {
        return mb;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}