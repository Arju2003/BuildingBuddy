package ca.uwo.csteam14;// Java program  to add a menubar
// and add menu items, submenu items and also add
// ActionListener and KeyListener to menu items
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

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
                startAgain = new Splash("./images/"+BuildingBuddy.currentBuildingCode+"_hero.png");
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
            String iconURL = "https://cdn-icons-png.flaticon.com/512/868/868681.png";
            new PopupView("Help", """
                   <div style="font-family: Arial">
                   <p style="text-align:right"><img src="
                    """ + iconURL + """
                    " width="150"></p>
                    
                    <h2>What is a POI?</h2>
                    A POI is a point of interest, namely a location on the map.

                    <h2>What is a bookmark?</h2>
                    A bookmark is one of your favourite POIs.

                    <h2>What is My Location?</h2>
                    My Location is a POI you defined, not a built-in POI.

                    <h2>How to view or edit bookmarks?</h2>
                    Enter View – Bookmarks, or press CTRL + B.

                    <h2>How to view or edit My Locations?</h2>
                    Enter View – My Locations, or press CTRL + L.

                    <h2>How to choose another building?</h2>
                    Click Start in the menu on top.
                    
                    <h2>How to quit the application safely?</h2>
                    Choose Exit from the main menu, or just click [X] on top of the window.

                    <h2>I still need help!</h2>
                    Feel free to write us: <a href="mailto:jason@shew.cc">jason@shew.cc</a>.
                    </div>
                    """);

        });
        about.addActionListener(e -> {
            clearWindows();
            String logoURL = "https://blotcdn.com/blog_708cbe8290984c03aff9e7a84d617b68/_image_cache/5aa729fb-9ca1-455c-9697-91e24575fca9.png";
            new PopupView("About", """
                    <div style="font-family: Arial; text-align: center;">
                    <img src="
                    """ + logoURL + """
                    " width="150">
                    <h2><i>BuildingBuddy</i></h2>

                    <h3>Version: 1.0</h3>

                    <h4>Developed by <span style="color:red;">Team 14</span> at <span style="color:#6600cc;">UWO</span></h4>

                    <h4>Developers: Daniel, Robert, Joshua, Arjuna, Jason</h4>

                    <a href="https://wiki.csd.uwo.ca/display/COMPSCI2212W2023GROUP14/COMPSCI+2212+-+Winter+2023+-+Group+14+Home">Project Website</a> | <a href="https://github.com/dan1el5/BuildingBuddy">GitHub</a><br>
                    </div>""");

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
        checkForUpdates.addActionListener(e -> {
            clearWindows();
            new PopupView("Software Update", """
                   <div style="font-family: Arial; font-size: 16px; text-align: center; color: green">""" +
                   BuildingBuddy.version + """
                    </div>
                    """);
        });
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
        mb.add(weather);
        mb.add(help);
        mb.add(about);
        mb.add(more);
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

    public static void clearWindows() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JWindow) {
                // JWindow object found on screen
                window.setVisible(false);
                // Take appropriate action here, such as hiding or closing the window
            }

            if (window instanceof JDialog) {
                window.dispose();
            }
        }
    }
}