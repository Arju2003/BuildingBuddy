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

public class AppMenu extends JFrame {
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
                    Click on any icon that represents a POI on the map and choose <b>Add Bookmark</b> or <b>Remove Bookmark</b> before pressing <b>Save Changes</b>.

                    <h2>What is My Location?</h2>
                    My Location is a POI defined by the user, not a built-in POI.
                    
                    <h2>How many My Locations are allowed to be created?</h2>
                    Currently, BuildingBuddy allows the user to create up to 999,999 POIs. If you create a new POI after reaching the limit, the oldest My Location will be overwritten.
                    
                    <h2>How can I create a location?</h2>
                    <p>As a user, you can only create a location when you're in Exploration mode (where you see a layer filter on the left). Click any empty space on the map. Edit the name and description for this location. Click <b>Save Changes</b> when you're done.</p>
                    <p>If you are viewing Bookmarks, My Locations, or search results, you cannot define your location. Click <b>Start</b> and <b>Explore</b> the building you hope to add a location in.</p>

                    <h2>How to view or edit bookmarks?</h2>
                    Enter <b>View</b> – <b>Bookmarks</b>, or press CTRL + B.

                    <h2>How to view or edit My Locations?</h2>
                    Enter <b>View</b> – <b>My Locations</b>, or press CTRL + L.
                    
                    <h2>How many bookmarks can I have?</h2>
                    A user can have as many bookmarks as they want.
                    
                    <h2>Can I bookmark a My Location?</h2>
                    Sure! You can bookmark anything on any floor map.
                    
                    <h2>Can I delete a bookmark or a My Location?</h2>
                    Sure! You can do that! Just bear in mind that if you delete a My Location that has been bookmarked, you lose that bookmark too.

                    <h2>How can I explore another building?</h2>
                    Click <b>Start</b> in the menu on top and use the building selector underneath the app logo.
                    
                    <h2>How can I get to a specific floor and see all the locations?</h2>
                    <p>Use the search bar at the bottom left, enter <i>building code + floor code</i>.</p>
                    <p>A building code is a building abbreviation:
                    <ul>
                    <li><b>MC</b> for Middlesex College</li>
                    <li><b>KB</b> for Kresge Building</li>
                    <li><b>PAB</b> for Physics & Astronomy Building</li>
                    </ul>
                    </p>
                    <p>A floor code is a single-digit number + F:
                    <ul>
                    <li><b>0F</b> for ground floor</li>
                    <li><b>1F</b> for first floor</li>
                    <li><b>2F</b> for second floor</li>
                    <li>...</li>
                    </ul>
                    </p>
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
        myLocations.addActionListener(e -> new GUIForPOIs("UDP"));
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
            title.setFont(new Font("Arial",Font.BOLD,18));
            mainPanel.add(title);
            JLabel prompt = new JLabel("Please enter security key:");
            mainPanel.add(prompt);

            JPasswordField securityKey = new JPasswordField();
            securityKey.setPreferredSize(new Dimension(260,40));
            securityKey.setText("");
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
                        newKeyInput1.setText("");

                        newKey2.setPreferredSize(new Dimension(260,40));
                        newKeyInput2.setText("");

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
                    changeKey.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
                    more.add(changeKey);
                    Main.devMode = true;
                    GUI.frame.setTitle("BuddyBuilding (Ver 1.0) *** Developer Mode ***");
                    devLogin.dispose();
                }
                else prompt.setText("Incorrect security key. Please try again:");
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

        Font font = new Font("Arial", Font.PLAIN, 16);
        start.setFont(font);
        view.setFont(font);
        weather.setFont(font);
        help.setFont(font);
        about.setFont(font);
        more.setFont(font);
        exit.setFont(font);

        start.setPreferredSize(new Dimension(100, 20));
        view.setPreferredSize(new Dimension(80, 20));
        weather.setPreferredSize(new Dimension(100, 20));
        help.setPreferredSize(new Dimension(80, 20));
        about.setPreferredSize(new Dimension(100, 20));
        more.setPreferredSize(new Dimension(80, 20));
        exit.setPreferredSize(new Dimension(100, 20));

        start.setBackground(mb.getBackground());
        view.setBackground(mb.getBackground());
        weather.setBackground(mb.getBackground());
        help.setBackground(mb.getBackground());
        about.setBackground(mb.getBackground());
        more.setBackground(mb.getBackground());
        exit.setBackground(mb.getBackground());

        start.setForeground(mb.getForeground());
        view.setForeground(mb.getForeground());
        weather.setForeground(mb.getForeground());
        help.setForeground(mb.getForeground());
        about.setForeground(mb.getForeground());
        more.setForeground(mb.getForeground());
        exit.setForeground(mb.getForeground());


    }


    /**
     * @return
     */
    public JMenuBar load() {
        return mb;
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