/**
 * @author Jason
 * AppMenu Class
 * Display About and Help Screens
 */

package ca.uwo.csteam14;// Java program  to add a menubar
// and add menu items, submenu items and also add
// ActionListener and KeyListener to menu items
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Arrays;

public class AppMenu extends JFrame implements ActionListener, KeyListener {
    protected final JMenuBar mb = new JMenuBar(); // create a menubar


    /**
     *
     */
    public AppMenu() {        // create an object of the class

        // create menu buttons
        mb.setLayout(new FlowLayout());

        JMenu view = new JMenu("View");
        JMenu more = new JMenu("More");

        // create menu items
        JMenuItem start = new JMenuItem("Start");
        JMenuItem bookmarks = new JMenuItem("" +
                "Bookmarks");
        JMenuItem myLocations = new JMenuItem("" +
                "My Locations");
        JMenuItem checkForUpdates = new JMenuItem("" +
                "Check for Updates");
        JMenuItem developerTool = new JMenuItem("" +
                "Developer Tool");
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
                startAgain = new Splash("./images/"+ Main.currentBuildingCode+"_hero.png");
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
                    A bookmark is one of your favourite POIs across all the maps in BuildingBuddy.
                    
                    <h2>How can I bookmark / unbookmark a location?</h2>
                    Click on any icon that represents a POI on the map and choose <b>Add Bookmark</b> or <b>Remove Bookmark</b> before pressing Save Changes.

                    <h2>What is My Location?</h2>
                    My Location is a POI you defined, not a built-in POI.
                    
                    <h2>How can I create a location?</h2>
                    <p>When you're in Building Exploration mode (where you see a layer filter on the left), click any empty space on the map. Edit the name and description for this location. Click <b>Save Changes</b> when you're done.</p>
                    <p>Please note that users cannot create a location when viewing Bookmarks, My Locations, or search results.</p>

                    <h2>How to view or edit bookmarks?</h2>
                    Enter <b>View</b> – <b>Bookmarks</b>, or press CTRL + B.

                    <h2>How to view or edit My Locations?</h2>
                    Enter <b>View</b> – <b>My Locations</b>, or press CTRL + L.
                    
                    <h2>Can I bookmark a POI I have created on my own?</h2>
                    Sure! You can bookmark anything on any floor map.
                    
                    <h2>Can I delete a bookmark or a My Location?</h2>
                    Sure! You can do that! Just bear in mind that if you delete a My Location that has been bookmarked, you lose that bookmark too.

                    <h2>How to choose another building?</h2>
                    Click <b>Start</b> in the menu on top and use the building selector underneath the app logo.
                    
                    <h2>How can I get to a specific floor and see all the locations?</h2>
                    <p>Use the search bar at the bottom left, enter <i>building code + floor number</i>.</p>
                    <p>Building codes are building abbreviations (MC for Middlesex College, KB for Kresge Building, and PAB for Physics & Astronomy Building).</p>
                    <p>A floor number is a single-digit number + F. The ground floor is 0F, first floor 1F, second floor 2F, etc.</p>
                    <p>For example, if you want to visit the second floor at Middlesex College, just enter <i>MC2F</i> and click <b>Go</b>.</p>
                    
                    <h2>How can I see all the locations on all the floors in all the building?</h2>
                    Again, use the search bar. Just click the <b>Go</b> button right away.
                    (Yes, you can either keep the placeholder phrase <i>Search Anything...</i> in the text bar or clear it before hitting Go.)
                    
                    <h2>Some of my bookmarks are gone unknowingly. Why?</h2>
                    Sorry about that! Our developers are updating this app regularly, so some POIs may have been deleted.
                    
                    <h2>How to quit the application safely?</h2>
                    Click <b>Exit</b> from the main menu, or just hit <b>[X]</b> on top of the window.
                    
                    <h2>I am a developer. Can I add / delete POIs or browse maps the same way?</h2>
                    Yes, you can. Here's a few tips for developers:
                    <ol>
                    <li>Select <b>More</b> – <b>Developer Tool</b> and enter the correct security key to activate Development Mode</li>
                    <li>You can only exit Developer Tool by hitting <b>[X]</b> or the <b>Exit</b> button. </li>
                    <li>You can only add, edit, or remove built-in POIs. </li>
                    <li>You can still take advantage of the search bar to search for a specific POI and view a specific floor map (refer to the MC2F example above). </li>
                    <li>For privacy concerns, developers cannot view the user's bookmarks or any non-built-in POIs.</li>
                    <li>If you forget your security key, shoot us an email.</li>
                    </ol>

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
        bookmarks.addActionListener(e -> new GUIForPOIs("BMK"));
        bookmarks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
        myLocations.addActionListener(e -> {new GUIForPOIs("UDP");});
        myLocations.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        checkForUpdates.addActionListener(e -> {
            clearWindows();
            new PopupView("Software Update", """
                   <div style="font-family: Arial; font-size: 16px; text-align: center; color: green">""" +
                   Main.version + """
                    </div>
                    """);
        });
        checkForUpdates.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));

        developerTool.addActionListener(e -> {
            JDialog devLogin = new JDialog();
            devLogin.setResizable(false);
            devLogin.setSize(new Dimension(280,390));
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - devLogin.getWidth()) / 2;
            int y = (screenSize.height - devLogin.getHeight()) / 2;
            devLogin.setLocation(x, y); // Set the position of the window to the center of the screen


            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(0,1));
            JLabel title = new JLabel("Developer Tool");
            mainPanel.add(title);
            JLabel prompt = new JLabel("Please enter security key");
            mainPanel.add(prompt);

            JPasswordField securityKey = new JPasswordField();
            securityKey.setPreferredSize(new Dimension(260,40));
            securityKey.setText(new String(new char[]{'C', 'S', '2', '2', '1', '2', 'B', 'B'}));
            securityKey.setVisible(true);
            securityKey.setEnabled(true);
            securityKey.setEditable(true);
            mainPanel.add(securityKey);

            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JButton enter = new JButton("Enter");
            JButton cancel = new JButton("Cancel");

            mainPanel.add(enter);
            mainPanel.add(cancel);

            enter.addActionListener( e3->{
                if (Arrays.equals(securityKey.getPassword(), Main.getSecurityKey())) {
                    new GUIForPOIs("BIP");
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
                        mainPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        mainPanel2.setLayout(new GridLayout(0,1));
                        JLabel title2 = new JLabel("Change Security Key");
                        mainPanel2.add(title2);
                        JLabel newKey1 = new JLabel("Please enter new security key:");
                        JPasswordField newKeyInput1 = new JPasswordField();
                        mainPanel2.add(newKey1);
                        mainPanel2.add(newKeyInput1);
                        JLabel newKey2 = new JLabel("Please enter new security key again:");
                        JPasswordField newKeyInput2 = new JPasswordField();
                        mainPanel2.add(newKey2);
                        mainPanel2.add(newKeyInput2);


                        newKey1.setPreferredSize(new Dimension(260,40));
                        newKeyInput1.setText(new String(new char[]{'C', 'S', '2', '2', '1', '2', 'B', 'B'}));

                        newKey2.setPreferredSize(new Dimension(260,40));
                        newKeyInput2.setText(new String(new char[]{'C', 'S', '2', '2', '1', '2', 'B', 'B'}));

                        newKeyInput1.setEditable(true);
                        newKeyInput2.setEditable(true);

                        JButton yes = new JButton("Confirm");
                        yes.addActionListener(e5-> {
                            if (Arrays.equals(newKeyInput1.getPassword(), newKeyInput2.getPassword())) {
                                title2.setText("THIS FEATURE IS FOR DEMONSTRATION PURPOSES ONLY");
                                newKey1.setText("BuildingBuddy Ver 2.0 will support changing your security key.");
                                newKeyInput1.setEnabled(false);
                                newKey2.setText("Please press Cancel for now. Thank you:-)");
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
                        changeKeyDialog.setLocationRelativeTo(devLogin);

                    });
                    more.add(changeKey);
                    Main.devMode = true;
                    GUI.frame.setTitle("BuddyBuilding (Ver 1.0) *** Developer Mode ***");
                    devLogin.dispose();
                }
                else prompt.setText("Incorrect security key. Please retry.");
            });

            devLogin.add(mainPanel);
            devLogin.pack();
            AppMenu.clearWindows();
            devLogin.setVisible(true);
            devLogin.setFocusableWindowState(true);
            cancel.addActionListener( e2-> clearWindows());
        });
        developerTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));



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

        start.setPreferredSize(new Dimension(100, 20));
        view.setPreferredSize(new Dimension(80, 20));
        weather.setPreferredSize(new Dimension(100, 20));
        help.setPreferredSize(new Dimension(80, 20));
        about.setPreferredSize(new Dimension(100, 20));
        more.setPreferredSize(new Dimension(80, 20));
        exit.setPreferredSize(new Dimension(100, 20));



    }

    /**
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {


    }

    /**
     * @return
     */
    public JMenuBar load() {
        return mb;
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     *
     */
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