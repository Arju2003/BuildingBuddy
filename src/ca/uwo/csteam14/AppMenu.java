package ca.uwo.csteam14;// Java program  to add a menubar
// and add menu items, submenu items and also add
// ActionListener and KeyListener to menu items
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class AppMenu extends JFrame implements ActionListener, KeyListener {
    protected final JMenuBar mb = new JMenuBar(); // create a menubar


    public AppMenu() {        // create an object of the class

        // create menu buttons
        mb.setLayout(new FlowLayout());

        JMenu view = new JMenu("View");
        JMenu more = new JMenu("More");

        // create menu items
        JMenuItem start = new JMenuItem("Start");
        JMenuItem bookmarks = new JMenuItem("" +
                "Bookmarks      CTRL+B");
        JMenuItem myLocations = new JMenuItem("" +
                "My Locations   CTRL+L");
        JMenuItem checkForUpdates = new JMenuItem("" +
                "Check for Updates   CTRL+U");
        JMenuItem developerTool = new JMenuItem("" +
                "Developer Tool        CTRL+X");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem about = new JMenuItem("About");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem weather = new JMenuItem("Weather");

        // add ActionListener to menu buttons and menu items
        start.addActionListener(e -> {
            // instantiate an object of the other class
            Splash startAgain;
            try {
                startAgain = new Splash("./images/mc_hero.png");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                startAgain.build();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        help.addActionListener(e -> {
            clearWindows(); // close all floating windows (the WeatherInfo window, specifically)
            // instantiate an object of the other class
            PopupView popupView = new PopupView("Help");
            popupView.pack();
            popupView.setVisible(true);
        });
        about.addActionListener(e -> {
            clearWindows();
            PopupView popupView = new PopupView("About");
            popupView.pack();
            popupView.setVisible(true);
        });
        exit.addActionListener(e -> {
            System.exit(0); // exit the program with status code 0
        });
        weather.addActionListener(e -> {
            try {
                new WeatherInfo();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
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
        mb.add(weather);
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

    public void clearWindows() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JWindow) {
                // JWindow object found on screen
                window.setVisible(false);
                // Take appropriate action here, such as hiding or closing the window
            }
        }
    }
}