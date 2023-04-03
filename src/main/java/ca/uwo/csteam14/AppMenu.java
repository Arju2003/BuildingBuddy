package ca.uwo.csteam14;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Arrays;
import static java.awt.Font.BOLD;

/**
 * Generates an app menu bar.
 * This class creates a main menu in the app, displayed horizontally at the top of the GUI.
 *
 * @author Jason B. Shew
 * @version 1.0.0
 * @since 2023-03-07
 */

public class AppMenu extends JFrame {

    /** The menu bar to be added to the top of the GUI. */
    protected final JMenuBar mb = new JMenuBar(); // Creates a menu bar.


    /**
     * Constructs a new AppMenu object.
     */
    public AppMenu() {

        // Creates menubar layout.
        mb.setLayout(new FlowLayout());
        // Creates two menus that contain menu items.
        JMenu view = new JMenu("View");
        JMenu more = new JMenu("More");

        // Creates menu items and stylizes each of them.
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
        JMenuItem logout = new JMenuItem("Logout");
        JMenuItem changeKey = new JMenuItem("Change Security Key");
        JMenuItem nukeBookmarks = new JMenuItem("Nuke Bookmarks");
        JMenuItem nukeMyLocations = new JMenuItem("Nuke My Locations");
        JMenuItem nukeBuiltInPOIs = new JMenuItem("Nuke Built-In POIs");
        JMenuItem reset = new JMenuItem("Reset BuildingBuddy");
        nukeBookmarks.setForeground(Color.RED);
        nukeMyLocations.setForeground(Color.RED);
        nukeBuiltInPOIs.setForeground(Color.RED);
        reset.setForeground(Color.RED);
        developerTool.setForeground(Color.BLUE);
        // Adds ActionListener to menu buttons and menu items.
        start.addActionListener(e -> {
            clearWindows();
            // Reloads the splash screen.
            try {
                new Splash("./images/"+ Main.currentBuildingCode+"_hero.png").build();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        logout.addActionListener(e -> {
            // Restarts the program.
            clearWindows();
            try {
                Main.restartApplication();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        help.addActionListener(e -> {
            clearWindows(); // Closes all floating windows.
            // Creates a PopupView object to display the Help page.
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
                    
                    <h2>What are "Nuke Bookmarks", "Nuke My Locations", and "Reset BuildingBuddy?</h2>
                    <p>These three features will delete all your bookmarks, My Locations, and both. Your personalized data will be permanently erased, and the program will be restored to its default settings.</p>
                    <p>If you only choose to nuke all My Locations, they will also disappear from your Bookmarks if you have bookmarked them.</p>
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
                    <li>You can exit Developer Tool by hitting <b>Logout</b>; you can also click <b>Exit</b> (or <b>[X]</b>) to quit the program.</li>
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
            clearWindows(); // Closes all floating windows and displays the About page.
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
            clearWindows();
            // Quits the program.
            System.exit(0); // Exits the program with status code 0.
        });

        weather.addActionListener(e -> {
            // Displays current weather.
            clearWindows();
            try {
                new WeatherInfo();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        nukeBookmarks.addActionListener(e -> {
            // Deletes all user bookmarks.
            clearWindows();
            JWindow deletionAlert = new JWindow();
            deletionAlert.setSize(480, 100);
            deletionAlert.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    // Centers the window on the screen.
                    deletionAlert.setLocationRelativeTo(null);
                }
            });
            JPanel deletionAlertPanel = new JPanel();
            deletionAlertPanel.setForeground(new Color(220,50,32));
            deletionAlertPanel.setForeground(new Color(255,255,255));
            // Creates an alert to confirm deletion.
            JLabel message = new JLabel("DANGER ZONE: DELETE ALL BOOKMARKS PERMANENTLY?");
            message.setFont(new Font("Arial",BOLD,18));
            message.setForeground(new Color(220,50,32));
            deletionAlertPanel.add(message);
            JButton cancel = new JButton("NO");
            cancel.setEnabled(true);
            deletionAlert.setLocationRelativeTo(GUI.frame);
            cancel.addActionListener(e2-> deletionAlert.setVisible(false));
            JButton confirm = new JButton("YES");
            confirm.setForeground(new Color(220,50,32));
            confirm.addActionListener(e3-> {
                // If confirmed, then deletes all bookmarks and reboots the program.
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
                } // If deletion fails then gives a prompt and does nothing.
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
            // Same as above but works on user-created POIs.
            clearWindows();
            JWindow deletionAlert = new JWindow();
            deletionAlert.setSize(480, 100);
            deletionAlert.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    deletionAlert.setLocationRelativeTo(null);
                }
            });
            JPanel deletionAlertPanel = new JPanel();
            deletionAlertPanel.setForeground(new Color(220,50,32));
            deletionAlertPanel.setForeground(new Color(255,255,255));
            JLabel message = new JLabel("DANGER ZONE: DELETE ALL \"MY LOCATIONS\" PERMANENTLY?");
            message.setFont(new Font("Arial",BOLD,18));
            message.setForeground(new Color(220,50,32));
            deletionAlertPanel.add(message);
            JButton cancel = new JButton("NO");
            cancel.setEnabled(true);
            deletionAlert.setLocationRelativeTo(GUI.frame);
            cancel.addActionListener(e2-> deletionAlert.setVisible(false));
            JButton confirm = new JButton("YES");
            confirm.setForeground(new Color(220,50,32));
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

        reset.addActionListener(e -> {
            // Same as above, but works on both user bookmarks and user-created POIs.
            clearWindows();
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
            JLabel message = new JLabel("DANGER ZONE: ERASE ALL YOUR DATA AND RESTORE FACTORY SETTINGS?");
            message.setFont(new Font("Arial",BOLD,18));
            message.setForeground(new Color(220,50,32));
            deletionAlertPanel.add(message);
            JButton cancel = new JButton("NO");
            cancel.setEnabled(true);
            deletionAlert.setLocationRelativeTo(GUI.frame);
            cancel.addActionListener(e2-> deletionAlert.setVisible(false));
            JButton confirm = new JButton("YES");
            confirm.setForeground(new Color(220,50,32));
            confirm.addActionListener(e3-> {
                boolean result ;
                try {
                    result = Data.reset();
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
            // Same as above, but works on built-in POIs.
            // This is only intended for the developer.
            clearWindows();
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
            JLabel message = new JLabel("DANGER ZONE: DELETE ALL BUILT-IN DATA PERMANENTLY?");
            message.setFont(new Font("Arial",BOLD,18));
            message.setForeground(new Color(220,50,32));
            deletionAlertPanel.add(message);
            JButton cancel = new JButton("NO");
            cancel.setEnabled(true);
            deletionAlert.setLocationRelativeTo(GUI.frame);
            cancel.addActionListener(e2-> deletionAlert.setVisible(false));
            JButton confirm = new JButton("YES");
            confirm.setForeground(new Color(220,50,32));
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

        // Links the menu option with the bookmark editor.
        bookmarks.addActionListener(e -> {clearWindows(); new GUIForPOIs("BMK");});
        // Adds hotkey to this menu option.
        bookmarks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
        // Links the menu option with My Location editor.
        myLocations.addActionListener(e -> {clearWindows(); new GUIForPOIs("UDP");});
        // Adds hotkey to this menu option.
        myLocations.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));

        // Links the menu option with software version display.
        checkForUpdates.addActionListener(e -> {
            clearWindows();
            new PopupView("Software Update", """
                    <br><br><br><br><br><br><br><br>
                   <div style="font-family: Arial; font-size: 16px; text-align: center; color: green">""" +
                   Main.version + """
                    </div>
                    ""","BB_icon.png");
        });
        // Adds hotkey to this menu option.
        checkForUpdates.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));

        // Links the menu option with the Developer Tool.
        developerTool.addActionListener(e -> {
            clearWindows();
            JDialog devLogin = new JDialog();
            devLogin.setResizable(false);
            devLogin.setSize(new Dimension(280,390));
            devLogin.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    // Centers the window on the screen.
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

            // Makes pressing Enter act as clicking the Enter button.
            securityKey.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    // Do nothing
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        // Simulates a button click when the Enter key is pressed in the text field.
                        enter.doClick();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // Does nothing.
                }
            });
            enter.setBorderPainted(false);
            enter.setOpaque(true);
            enter.setBackground(new Color(0, 90, 181));
            enter.setForeground(Color.WHITE);
            enter.setFont(new Font("Arial",BOLD,12));
            enter.addActionListener( e3->{
                if (Arrays.equals(securityKey.getPassword(), Main.getSecurityKey())) {
                    // If security key is correct, then starts Developer Tool.
                    new GUIForPOIs("BIP");
                    // Clears user search results for privacy concerns.
                    Search.userInput = null;
                    // Removes certain items from the menu, so they are not accessible to the developer.
                    more.remove(developerTool);
                    more.remove(nukeBookmarks);
                    more.remove(nukeMyLocations);
                    more.remove(reset);
                    changeKey.addActionListener(e4->{
                        // A dialog for changing security key.
                        clearWindows();
                        JDialog changeKeyDialog = new JDialog();
                        changeKeyDialog.setResizable(false);
                        changeKeyDialog.setSize(480, 450);
                        changeKeyDialog.addComponentListener(new ComponentAdapter() {
                            @Override
                            public void componentShown(ComponentEvent e) {
                                // Centers the window on the screen.
                                changeKeyDialog.setLocationRelativeTo(null);
                            }
                        });
                        JPanel mainPanel2 = new JPanel();
                        mainPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        mainPanel2.setLayout(new GridLayout(0,1));
                        JLabel title2 = new JLabel("Change Security Key");
                        title2.setFont(new Font("Arial",Font.BOLD,18));
                        mainPanel2.add(title2);
                        // User has to enter a new security key twice to change it.
                        JLabel newKey1 = new JLabel("Please enter new security key:");
                        JPasswordField newKeyInput1 = new JPasswordField();
                        mainPanel2.add(newKey1);
                        mainPanel2.add(newKeyInput1);
                        JLabel newKey2 = new JLabel("Please enter new security key again:");
                        JPasswordField newKeyInput2 = new JPasswordField();
                        mainPanel2.add(newKey2);
                        mainPanel2.add(newKeyInput2);
                        newKeyInput1.setPreferredSize(new Dimension(260,40));
                        newKeyInput2.setPreferredSize(new Dimension(260,40));

                        newKey1.setPreferredSize(new Dimension(260,40));
                        newKeyInput1.setText("");

                        newKey2.setPreferredSize(new Dimension(260,40));
                        newKeyInput2.setText("");

                        newKeyInput1.setEditable(true);
                        newKeyInput2.setEditable(true);


                        JButton yes = new JButton("Confirm");
                        yes.setBackground(new Color(0, 90, 181));
                        yes.addActionListener(e5-> {
                            if (newKeyInput1.getPassword().length > 0 && Arrays.equals(newKeyInput1.getPassword(), newKeyInput2.getPassword())) {
                                // If the new security key is not empty and two inputs are identical then changes the key
                                Main.changeSecurityKey(newKeyInput1.getPassword());
                                POIEditor.resultDisplay("Successful! Remember your new Security Key!", Color.GREEN);
                                changeKeyDialog.dispose();
                            }
                            else
                                // Otherwise gives a prompt
                                newKey1.setText("<html><p><span style=\"color: red;\">Two inputs must match and neither can be empty.</span></p><br><p>Enter new security key:</p></html>");
                        });
                        JButton no = new JButton("Cancel");
                        no.addActionListener(e1 -> changeKeyDialog.dispose());


                        // Makes the second input of new security key respond to hitting Enter key.
                        newKeyInput2.addKeyListener(new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                // Does nothing.
                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                    // Simulates a button click when the Enter key is pressed in the text field.
                                    yes.doClick();
                                }
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                                // Does nothing.
                            }
                        });

                        mainPanel2.add(yes);
                        mainPanel2.add(no);
                        yes.setPreferredSize(new Dimension(60,40));
                        no.setPreferredSize(new Dimension(60,40));
                        changeKeyDialog.add(mainPanel2);
                        changeKeyDialog.setVisible(true);
                        changeKeyDialog.setLocationRelativeTo(devLogin);

                    });
                    changeKey.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
                    mb.remove(view);
                    mb.add(logout);
                    mb.remove(start);
                    more.add(changeKey);
                    more.add(nukeBuiltInPOIs);
                    // Sets the app mode to Dev Mode.
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
            cancel.setBorderPainted(false);
            cancel.setOpaque(true);
            cancel.setBackground(new Color(180,200,255));
            cancel.setForeground(Color.BLACK);
            cancel.setFont(new Font("Arial",BOLD,12));
            cancel.addActionListener( e2-> clearWindows());
        });

        developerTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

        // Adds menu items to menu.
        view.add(bookmarks);
        view.add(myLocations);
        more.add(checkForUpdates);
        more.add(nukeBookmarks);
        more.add(nukeMyLocations);
        more.add(reset);
        more.add(developerTool);

        // Adds menu to menu bar.
        mb.add(start);
        mb.add(view);
        mb.add(weather);
        mb.add(help);
        mb.add(about);
        mb.add(more);
        mb.add(exit);

        // Sets the font, size, and color of each menu item.
        Font font = new Font("Arial", Font.PLAIN, 16);
        start.setFont(font);
        view.setFont(font);
        weather.setFont(font);
        help.setFont(font);
        about.setFont(font);
        more.setFont(font);
        logout.setFont(font);
        exit.setFont(font);

        start.setPreferredSize(new Dimension(100, 20));
        view.setPreferredSize(new Dimension(80, 20));
        weather.setPreferredSize(new Dimension(100, 20));
        help.setPreferredSize(new Dimension(80, 20));
        about.setPreferredSize(new Dimension(100, 20));
        more.setPreferredSize(new Dimension(80, 20));
        logout.setPreferredSize(new Dimension(100, 20));
        exit.setPreferredSize(new Dimension(80, 20));

        start.setBackground(mb.getBackground());
        view.setBackground(mb.getBackground());
        weather.setBackground(mb.getBackground());
        help.setBackground(mb.getBackground());
        about.setBackground(mb.getBackground());
        more.setBackground(mb.getBackground());
        logout.setBackground(mb.getBackground());
        exit.setBackground(mb.getBackground());

        start.setForeground(mb.getForeground());
        view.setForeground(mb.getForeground());
        weather.setForeground(mb.getForeground());
        help.setForeground(mb.getForeground());
        about.setForeground(mb.getForeground());
        more.setForeground(mb.getForeground());
        logout.setForeground(mb.getForeground());
        exit.setForeground(mb.getForeground());
    }


    /**
     * This method loads the app menu bar onto the GUI.
     *
     * @return mb the menubar
     */
    public JMenuBar load() {
        return mb;
    }

    /**
     * This method closes all the floating windows on top of the GUI.
     */
    public static void clearWindows() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JWindow) {
                window.setVisible(false);
                // Makes all JWindow objects invisible.
            }

            if (window instanceof JDialog) {
                window.dispose();
                // Closes all JDialog objects.
            }
        }
    }
}