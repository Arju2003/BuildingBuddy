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

import static java.awt.Font.BOLD;

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
        JMenuItem reboot = new JMenuItem("Reboot");
        JMenuItem changeKey = new JMenuItem("Change Security Key");
        JMenuItem nukeBookmarks = new JMenuItem("Nuke Bookmarks");
        JMenuItem nukeMyLocations = new JMenuItem("Nuke My Locations");
        JMenuItem nukeBuiltInPOIs = new JMenuItem("Nuke Built-In POIs");
        nukeBookmarks.setForeground(Color.RED);
        nukeMyLocations.setForeground(Color.RED);
        nukeBuiltInPOIs.setForeground(Color.RED);
        developerTool.setForeground(Color.BLUE);
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
        reboot.addActionListener(e -> {
                    try {
                        Main.restartApplication();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
        help.addActionListener(e -> {
            clearWindows(); // close all floating windows (the WeatherInfo window, specifically)
            // instantiate an object of the other class
            new PopupView("Help", """
                   <div style="font-family: Arial; font-size: 18">
                  <h2>What is a POI?</h2>
                    <p>A POI is a point of interest, namely a location on the map.</p>
                    <hr>

                    <h2>What is a bookmark?</h2>
                    <p>A bookmark is one of your favourite POIs across all the maps in BuildingBuddy.</p>
                    <hr>
                    
                    <h2>What is My Location?</h2>
                    <p>My Location is a POI defined by the user, not a built-in POI.</p>
                    <hr>
                    
                    <h2>How can I bookmark / unbookmark a location?</h2>
                    <p>Click on any icon that represents a POI on the map and choose <b>Add Bookmark</b> or <b>Remove Bookmark</b> before pressing <b>Save Changes</b>.</p>
                    <hr>
                    
                    <h2>How many My Locations are allowed to be created?</h2>
                    <p>Currently, BuildingBuddy allows the user to create up to 999,999 POIs. If you create a new POI after reaching the limit, the oldest My Location will be overwritten.</p>
                    <p>While it may look like a really large number, but, mind you, even if you delete a My Location, that lot of memory will not be freed up for a new POI.</p>
                    <p>If you hope to make full use of the 999,999 quota, back up your data elsewhere, and try <b>More</b> – <b>Nuke My Locations</b>.</p>
                    <hr>
                    
                    <h2>How can I explore another building?</h2>
                    <p>Click <b>Start</b> in the menu on top and use the building selector underneath the app logo.</p>
                    <hr>
                    
                    <h2>How can I get to a specific floor and see all the locations?</h2>
                    <p>When you are not on the splash screen (the opening screen), you will see a search bar at the bottom left. Enter <i>building code + floor code</i> to explore any floor.</p>
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
                    <p>The search bar remembers your last successful search phrase, so you can simply click <b>Go</b> to stay on this floor.</p>
                    <hr>
                    
                    <h2>How can I create a location?</h2>
                    <p>As a user, you can only create a POI when you're in Exploration mode (where you see a layer filter on the left). Click on any empty spot on the map. Edit the name and description for this location. Click <b>Save Changes</b> when you're done.</p>
                    <p>If you are viewing Bookmarks, My Locations, or search results, you cannot add a new POI. Those interfaces are designed to manage existing POIs.</p>
                    <p>You can add a POI by clicking <b>Start</b> in the menu and <b>Explore</b> the building where you hope to add a POI.</p>
                    <hr>
                    
                    <h2>Why can't I edit the room numbers for My Locations?</h2>
                    <p>Room numbers are currently not available for My Locations, but you can write room numbers and any useful information in the <b>Description</b> text box. What's cool, they are searchable!</p>
                    <hr>
                    
                    <h2>How to view or edit bookmarks?</h2>
                    <p>Enter <b>View</b> – <b>Bookmarks</b>, or press CTRL + B.</p>
                    <hr>
                    
                    <h2>How to view or edit My Locations?</h2>
                    <p>Enter <b>View</b> – <b>My Locations</b>, or press CTRL + L.</p>
                    <hr>
                    
                    <h2>How many bookmarks can I have?</h2>
                    <p>A user can have as many bookmarks as they want.</p>
                    <hr>
                    
                    <h2>Can I bookmark a My Location?</h2>
                    <p>Sure! You can bookmark anything on any floor map.</p>
                    <hr>
                    
                    <h2>Can I delete My Location?</h2>
                    <p>Sure! You can do that! Just bear in mind that if you delete a My Location that has been bookmarked, you lose that bookmark too.</p>
                    <hr>
                    
                    <h2>What are "Nuke Bookmarks" and "Nuke My Locations"?</h2>
                    <p>These two features will delete all your bookmarks and My Locations, respectively. As such, your personalized data will be permanently erased, and the program will be restored to its default settings.</p>
                    <p>Again, if you choose to nuke all My Locations, they will also disappear from your Bookmarks if you have bookmarked them.</p>
                    <p>After you confirm your choice, the program will reboot automatically.</p>
                    <hr>
                    
                    <h2>How can I activate "Discovery Mode" to see all the POIs on all the floors in all the buildings?</h2>
                    <p>Use the search bar and click <b>Go</b> right away. You can either keep the placeholder phrase <i>Search Anything...</i> in the text bar or clear it before hitting <b>Go</b>.)</p>
                    <hr>
                    
                    <h2>Some of my bookmarks are gone unknowingly. Why?</h2>
                    <p>Sorry about that! Our developers are updating this app regularly, so some POIs may have been deleted. When they no longer exist, they disappear from your Bookmarks too.</p>
                    <hr>
                    
                    <h2>How to quit the application safely?</h2>
                    <p>Click <b>Exit</b> from the main menu, or just hit <b>[X]</b> on top of the window.</p>
                    <hr>
                    
                    <h2>I am a developer. Can I add / delete POIs or browse maps the same way?</h2>
                    <p>Yes, you can. Here's a few tips for developers:
                    <ol>
                    <li>Select <b>More</b> – <b>Developer Tool</b> and enter the correct security key to activate Development Mode</li>
                    <li>You can exit Developer Tool by hitting <b>Reboot</b>; you can also click <b>Exit</b> (or <b>[X]</b>) to quit the program.</li>
                    <li>You can only add, edit, or remove built-in POIs. </li>
                    <li>You can still take advantage of the search bar to search for a specific POI and view a specific floor map (refer to the <i>MC2F</i> example above). </li>
                    <li>For privacy concerns, developers cannot view the user's bookmarks or any non-built-in POIs.</li>
                    <li>If you forget your security key, refer to the README file or shoot us an email at <a href="mailto:jason@shew.cc">jason@shew.cc</a>.</li>
                    </ol>
                    </p>
                    <hr>
                    
                    <h2>I still need help!</h2>
                    <p>Feel free to write us: <a href="mailto:jason@shew.cc">jason@shew.cc</a>.</p>
                    <hr>
                    </div>
                    ""","help.png");

        });
        about.addActionListener(e -> {
            clearWindows();
            new PopupView("About", """
                    <div style="font-family: Arial; text-align: center;">
                    <br>
                    <br>
                    <br>
                    <br>
                    <h2><i>BuildingBuddy</i></h2>

                    <h3>Version: 1.0</h3>

                    <h4>Developed by <span style="color:red;">Team 14</span> at <span style="color:#6600cc;">UWO</span></h4>

                    <h4>Developers: Daniel, Robert, Joshua, Arjuna, Jason</h4>

                    <a href="https://wiki.csd.uwo.ca/display/COMPSCI2212W2023GROUP14/COMPSCI+2212+-+Winter+2023+-+Group+14+Home">Project Website</a> | <a href="https://github.com/dan1el5/BuildingBuddy">GitHub</a><br>
                    </div>""","BB_icon.png");

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

        nukeBookmarks.addActionListener(e -> {
            JWindow deletionAlert = new JWindow();
            deletionAlert.setSize(480, 100);
            deletionAlert.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    // Center the window on the screen
                    deletionAlert.setLocationRelativeTo(null);
                }
            });
            JPanel deletionAlertPanel = new JPanel();
            deletionAlertPanel.setForeground(new Color(220,50,32));
            deletionAlertPanel.setForeground(new Color(255,255,255));
            JLabel message = new JLabel("DANGER ZONE: DELETE ALL BOOKMARKS PERMANENTLY, AND REBOOT?");
            message.setFont(new Font("Arial",BOLD,18));
            message.setForeground(new Color(220,50,32));
            deletionAlertPanel.add(message);
            JButton cancel = new JButton("NO");
            cancel.setEnabled(true);
            deletionAlert.setLocationRelativeTo(GUI.frame);
            cancel.addActionListener(e2-> deletionAlert.setVisible(false));
            JButton confirm = new JButton("YES");
            confirm.setBackground(Color.RED);
            confirm.addActionListener(e3-> {
                boolean result ;
                try {
                    result = Data.nuke(Data.bookmarks);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (result) {
                    try {
                        Main.restartApplication();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    POIEditor.resultDisplay("Oops... Try again...",Color.PINK);
                    deletionAlert.dispose();
                }

            });
            deletionAlertPanel.add(confirm);
            deletionAlertPanel.add(cancel);
            deletionAlertPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            deletionAlert.add(deletionAlertPanel);
            deletionAlert.pack();
            deletionAlert.setAlwaysOnTop(true);
            deletionAlert.setFocusableWindowState(false);
            deletionAlert.setFocusable(false);
            deletionAlert.setVisible(true);
        });

        nukeMyLocations.addActionListener(e -> {
            JWindow deletionAlert = new JWindow();
            deletionAlert.setSize(480, 100);
            deletionAlert.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    // Center the window on the screen
                    deletionAlert.setLocationRelativeTo(null);
                }
            });
            JPanel deletionAlertPanel = new JPanel();
            deletionAlertPanel.setForeground(new Color(220,50,32));
            deletionAlertPanel.setForeground(new Color(255,255,255));
            JLabel message = new JLabel("DANGER ZONE: DELETE ALL MY LOCATIONS, AND REBOOT?");
            message.setFont(new Font("Arial",BOLD,18));
            message.setForeground(new Color(220,50,32));
            deletionAlertPanel.add(message);
            JButton cancel = new JButton("NO");
            cancel.setEnabled(true);
            deletionAlert.setLocationRelativeTo(GUI.frame);
            cancel.addActionListener(e2-> deletionAlert.setVisible(false));
            JButton confirm = new JButton("YES");
            confirm.setBackground(Color.RED);
            confirm.addActionListener(e3-> {
                boolean result ;
                try {
                    result = Data.nuke(Data.userCreatedPOIs);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (result) {
                    try {
                        Main.restartApplication();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    POIEditor.resultDisplay("Oops... Try again...",Color.PINK);
                    deletionAlert.dispose();
                }

            });
            deletionAlertPanel.add(confirm);
            deletionAlertPanel.add(cancel);
            deletionAlertPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            deletionAlert.add(deletionAlertPanel);
            deletionAlert.pack();
            deletionAlert.setAlwaysOnTop(true);
            deletionAlert.setFocusableWindowState(false);
            deletionAlert.setFocusable(false);
            deletionAlert.setVisible(true);
        });

        nukeBuiltInPOIs.addActionListener(e -> {
            JWindow deletionAlert = new JWindow();
            deletionAlert.setSize(480, 100);
            deletionAlert.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    // Center the window on the screen
                    deletionAlert.setLocationRelativeTo(null);
                }
            });
            JPanel deletionAlertPanel = new JPanel();
            deletionAlertPanel.setForeground(new Color(220,50,32));
            deletionAlertPanel.setForeground(new Color(255,255,255));
            JLabel message = new JLabel("DANGER ZONE: DELETE ALL BUILT-IN DATA PERMANENTLY, AND REBOOT?");
            message.setFont(new Font("Arial",BOLD,18));
            message.setForeground(new Color(220,50,32));
            deletionAlertPanel.add(message);
            JButton cancel = new JButton("NO");
            cancel.setEnabled(true);
            deletionAlert.setLocationRelativeTo(GUI.frame);
            cancel.addActionListener(e2-> deletionAlert.setVisible(false));
            JButton confirm = new JButton("YES");
            confirm.setBackground(Color.RED);
            confirm.addActionListener(e3-> {
                boolean result ;
                try {
                    result = Data.nuke(Data.builtInPOIs);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if (result) {
                    try {
                        Main.restartApplication();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    POIEditor.resultDisplay("Oops... Try again...",Color.PINK);
                    deletionAlert.dispose();
                }

            });
            deletionAlertPanel.add(confirm);
            deletionAlertPanel.add(cancel);
            deletionAlertPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            deletionAlert.add(deletionAlertPanel);
            deletionAlert.pack();
            deletionAlert.setAlwaysOnTop(true);
            deletionAlert.setFocusableWindowState(false);
            deletionAlert.setFocusable(false);
            deletionAlert.setVisible(true);
        });

        bookmarks.addActionListener(e -> new GUIForPOIs("BMK"));
        bookmarks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
        myLocations.addActionListener(e -> new GUIForPOIs("UDP"));
        myLocations.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        checkForUpdates.addActionListener(e -> {
            clearWindows();
            new PopupView("Software Update", """
                   <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                   <div style="font-family: Arial; font-size: 16px; text-align: center; color: green">""" +
                   Main.version + """
                    </div>
                    ""","BB_icon.png");
        });
        checkForUpdates.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));

        developerTool.addActionListener(e -> {
            JDialog devLogin = new JDialog();
            devLogin.setResizable(false);
            devLogin.setSize(new Dimension(280,390));
            devLogin.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    // Center the window on the screen
                    devLogin.setLocationRelativeTo(null);
                }
            });

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

            securityKey.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    // Do nothing
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        // Simulate a button click when the Enter key is pressed in the text field
                        enter.doClick();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // Do nothing
                }
            });

            enter.addActionListener( e3->{
                if (Arrays.equals(securityKey.getPassword(), Main.getSecurityKey())) {
                    new GUIForPOIs("BIP");
                    Search.userInput = null;
                    more.remove(developerTool);
                    more.remove(nukeBookmarks);
                    more.remove(nukeMyLocations);
                    changeKey.addActionListener(e4->{
                        clearWindows();
                        JDialog changeKeyDialog = new JDialog();
                        changeKeyDialog.setResizable(false);
                        changeKeyDialog.addComponentListener(new ComponentAdapter() {
                            @Override
                            public void componentShown(ComponentEvent e) {
                                // Center the window on the screen
                                changeKeyDialog.setLocationRelativeTo(null);
                            }
                        });
                        JPanel mainPanel2 = new JPanel();
                        mainPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        mainPanel2.setLayout(new GridLayout(0,1));
                        JLabel title2 = new JLabel("Change Security Key");
                        title2.setFont(new Font("Arial",Font.BOLD,18));
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
                            if (newKeyInput1.getPassword().length > 0 && Arrays.equals(newKeyInput1.getPassword(), newKeyInput2.getPassword())) {
                                title2.setText("THIS FEATURE'S COMING SOON");
                                newKey1.setText("BuildingBuddy Ver 2.0 will support changing your security key.");
                                newKeyInput1.setEnabled(false);
                                newKey2.setText("Please press Cancel for now. Thank you:-)");
                                newKeyInput2.setEnabled(false);
                            }
                            else
                                newKey1.setText("Two inputs must match and neither can be empty. Enter new security key:");
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
                    mb.remove(view);
                    mb.add(reboot);
                    mb.remove(start);
                    more.add(changeKey);
                    more.add(nukeBuiltInPOIs);
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
        more.add(nukeBookmarks);
        more.add(nukeMyLocations);
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
        reboot.setFont(font);
        exit.setFont(font);

        start.setPreferredSize(new Dimension(100, 20));
        view.setPreferredSize(new Dimension(80, 20));
        weather.setPreferredSize(new Dimension(100, 20));
        help.setPreferredSize(new Dimension(80, 20));
        about.setPreferredSize(new Dimension(100, 20));
        more.setPreferredSize(new Dimension(80, 20));
        reboot.setPreferredSize(new Dimension(100, 20));
        exit.setPreferredSize(new Dimension(80, 20));

        start.setBackground(mb.getBackground());
        view.setBackground(mb.getBackground());
        weather.setBackground(mb.getBackground());
        help.setBackground(mb.getBackground());
        about.setBackground(mb.getBackground());
        more.setBackground(mb.getBackground());
        reboot.setBackground(mb.getBackground());
        exit.setBackground(mb.getBackground());

        start.setForeground(mb.getForeground());
        view.setForeground(mb.getForeground());
        weather.setForeground(mb.getForeground());
        help.setForeground(mb.getForeground());
        about.setForeground(mb.getForeground());
        more.setForeground(mb.getForeground());
        reboot.setForeground(mb.getForeground());
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