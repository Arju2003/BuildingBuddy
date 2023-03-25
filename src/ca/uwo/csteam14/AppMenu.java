package ca.uwo.csteam14;// Java program  to add a menubar
// and add menu items, submenu items and also add
// ActionListener and KeyListener to menu items
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static javax.swing.BoxLayout.Y_AXIS;

public class AppMenu extends JFrame implements ActionListener, KeyListener {
    protected final JMenuBar mb = new JMenuBar(); // create a menubar


    public AppMenu(String mode) {        // create an object of the class

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
        JMenuItem changeKey = new JMenuItem("Change Security Key");

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
        bookmarks.addActionListener(e -> {
            new GUIForPOIs("BMK", "Bookmarks");
        });
        myLocations.addActionListener(e -> {
            new GUIForPOIs("UDP", "My Locations");

        });
        checkForUpdates.addActionListener(e -> {
            clearWindows();
            new PopupView("Software Update", """
                   <div style="font-family: Arial; font-size: 16px; text-align: center; color: green">""" +
                   BuildingBuddy.version + """
                    </div>
                    """);
        });
        developerTool.addActionListener(e -> {
            JDialog window = new JDialog();
            window.setResizable(false);
            window.setSize(new Dimension(260,390));
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - window.getWidth()) / 2;
            int y = (screenSize.height - window.getHeight()) / 2;
            window.setLocation(x, y); // Set the position of the window to the center of the screen


            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(0,1));
            JLabel title = new JLabel("Developer Tool");
            mainPanel.add(title);
            JLabel prompt = new JLabel("Please enter security key");
            mainPanel.add(prompt);

            JTextField securityKey = new JTextField();
            securityKey.setPreferredSize(new Dimension(260,40));
            securityKey.setText("CS2212BB");
            securityKey.setVisible(true);
            securityKey.setEnabled(true);
            securityKey.setEditable(true);
            mainPanel.add(securityKey);

            JButton enter = new JButton("Enter");
            JButton cancel = new JButton("Cancel");

            mainPanel.add(enter);
            mainPanel.add(cancel);

            enter.addActionListener( e3->{
                if (securityKey.getText().equals(BuildingBuddy.getSecurityKey())) {
                    new GUIForPOIs("BIP", "Developer Tool");
                    start.setEnabled(false);
                    view.setEnabled(false);
                    myLocations.setEnabled(false);
                    bookmarks.setEnabled(false);
                    developerTool.setEnabled(false);
                    changeKey.addActionListener(e4->{
                        clearWindows();
                        JDialog changeKeyDialog = new JDialog();
                        changeKeyDialog.setResizable(false);
                        changeKeyDialog.setSize(600,200);
                        changeKeyDialog.setLocation(x, y); // Set the position of the window to the center of the screen
                        JPanel mainPanel2 = new JPanel();
                        mainPanel2.setLayout(new GridLayout(0,1));
                        JLabel title2 = new JLabel("Change Security Key");
                        mainPanel2.add(title2);
                        JLabel newKey1 = new JLabel("Please enter new security key:");
                        JTextField newKeyInput1 = new JTextField();
                        mainPanel2.add(newKey1);
                        mainPanel2.add(newKeyInput1);
                        JLabel newKey2 = new JLabel("Please enter new security key again:");
                        JTextField newKeyInput2 = new JTextField();
                        mainPanel2.add(newKey2);
                        mainPanel2.add(newKeyInput2);


                        newKey1.setPreferredSize(new Dimension(260,40));
                        newKeyInput1.setText("CS2212BB");

                        newKey2.setPreferredSize(new Dimension(260,40));
                        newKeyInput2.setText("CS2212BB");

                        newKeyInput1.setEditable(true);
                        newKeyInput2.setEditable(true);

                        JButton yes = new JButton("Confirm");
                        yes.addActionListener(e5-> {
                            if (newKeyInput1.getText().equals(newKeyInput2.getText())) {
                                newKey1.setText("Changing security is highly risky. BuildingBuddy Ver 2.0 will support that.");
                                newKeyInput1.setEnabled(false);
                                newKey2.setText("Thank you! Please press Cancel for now. :-)");
                                newKeyInput2.setEnabled(false);
                            }
                            else
                                newKey1.setText("Two inputs did not match. Try again. Enter new security key:");
                        });
                        JButton no = new JButton("Cancel");
                        no.addActionListener(e1 -> changeKeyDialog.dispose());

                        mainPanel2.add(yes);
                        mainPanel2.add(no);
                        changeKeyDialog.add(mainPanel2);
                        changeKeyDialog.setVisible(true);
                        changeKeyDialog.setLocationRelativeTo(window);

                    });
                    more.add(changeKey);
                    BuildingBuddy.devMode = true;
                    GUI.frame.setTitle("BuddyBuilding (Ver 1.0) *** Developer Mode ***");
                    window.dispose();
                }
                else prompt.setText("Incorrect security key. Please retry.");
            });

            window.add(mainPanel);
            window.pack();
            AppMenu.clearWindows();
            window.setVisible(true);
            window.setFocusableWindowState(true);

            cancel.addActionListener( e2-> {
                clearWindows();
            });
        });

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