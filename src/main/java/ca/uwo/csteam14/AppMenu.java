package ca.uwo.csteam14;
import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static java.awt.Font.BOLD;

/**
 * Generates an app menu bar.
 * This class creates a main menu in the app, displayed horizontally at the top of the GUI.
 *
 * @author Jason B. Shew
 * @version 1.0.0
 * @since 2023-03-07
 */
public class AppMenu {

    /** The menu bar to be added to the top of the GUI. */
    protected final JMenuBar mb = new JMenuBar(); // Creates a menu bar.


    /**
     * Constructs a new AppMenu object.
     */
    public AppMenu() {

        // Creates menubar layout.
        mb.setLayout(new FlowLayout());

        // Creates menus and menu items and stylizes each of them.
        JMenu start = new JMenu("Start");
        JMenu view = new JMenu("View");
        JMenu discover = new JMenu("Discover");
        JMenu more = new JMenu("More");
        JMenu help = new JMenu("Help");
        JMenu about = new JMenu("About");
        JMenu exit = new JMenu("Exit");
        JMenu weather = new JMenu("Weather");
        JMenu logout = new JMenu("Logout");

        JMenuItem bookmarks = new JMenuItem("" +
                "Bookmarks");
        JMenuItem myLocations = new JMenuItem("" +
                "My Locations");

        // Menu items in Discovery Mode begin
        JMenu mc = new JMenu("Middlesex College (MC)");
        JMenu kb = new JMenu("Kresge Building (KB)");
        JMenu pab = new JMenu("Physics & Astronomy Building (PAB)");
        JMenuItem mc0f = new JMenuItem("" +
                "Ground Floor (0F)");
        mc0f.addActionListener(e -> discovery ("MC0F"));

        JMenuItem mc1f = new JMenuItem("" +
                "First Floor (1F)");
        mc1f.addActionListener(e -> discovery("MC1F"));
        JMenuItem mc2f = new JMenuItem("" +
                "Second Floor (2F)");
        mc2f.addActionListener(e -> discovery("MC2F"));
        JMenuItem mc3f = new JMenuItem("" +
                "Third Floor (3F)");
        mc3f.addActionListener(e -> discovery("MC3F"));
        JMenuItem mc4f = new JMenuItem("" +
                "Fourth Floor (4F)");
        mc4f.addActionListener(e -> discovery("MC4F"));
        JMenuItem kb0f = new JMenuItem("" +
                "Ground Floor (0F)");
        kb0f.addActionListener(e -> discovery("KB0F"));
        JMenuItem kb1f = new JMenuItem("" +
                "First Floor (1F)");
        kb1f.addActionListener(e -> discovery("KB1F"));
        JMenuItem kb2f = new JMenuItem("" +
                "Second Floor (2F)");
        kb2f.addActionListener(e -> discovery("KB2F"));
        JMenuItem kb3f = new JMenuItem("" +
                "Third Floor (3F)");
        kb3f.addActionListener(e -> discovery("KB3F"));
        JMenuItem pab0f = new JMenuItem("" +
                "Ground Floor (0F)");
        pab0f.addActionListener(e -> discovery("PAB0F"));
        JMenuItem pab1f = new JMenuItem("" +
                "First Floor (1F)");
        pab1f.addActionListener(e -> discovery("PAB1F"));
        JMenuItem pab2f = new JMenuItem("" +
                "Second Floor (2F)");
        pab2f.addActionListener(e -> discovery("PAB2F"));
        JMenuItem pab3f = new JMenuItem("" +
                "Third Floor (3F)");
        pab3f.addActionListener(e -> discovery("PAB3F"));
        // Menu items in Discovery Mode end

        JMenuItem checkForUpdates = new JMenuItem("" +
                "Check for Updates");
        JMenuItem developerTool = new JMenuItem("" +
                "Developer Tool");

        JMenuItem changeKey = new JMenuItem("Change Security Key");
        JMenuItem nukeBookmarks = new JMenuItem("Nuke Bookmarks");
        JMenuItem nukeMyLocations = new JMenuItem("Nuke My Locations");
        JMenuItem nukeBuiltInPOIs = new JMenuItem("Nuke Built-In POIs");
        JMenuItem reset = new JMenuItem("Reset BuildingBuddy");

        // Adds ActionListener to menu buttons and menu items.
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearWindows();
                // Reloads the splash screen.
                try {
                    new Splash("./images/" + Main.currentBuildingCode + "_hero.png").build();
                    GUI.frame.setTitle("BuildingBuddy by " + Main.developerName + " – Version " + Main.currentAppVersion + " –");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Restarts the program.
                clearWindows();
                try {
                    Main.restartApplication();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        help.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              clearWindows(); // Closes all floating windows.
              // Creates a PopupView object to display the Help page.
              new PopupView("Help", """
                       <div style="font-family: Arial; font-size: 18">
                      <h2>What is a POI?</h2>
                        <p>A POI is a point of interest, namely a location on the map.</p>
                        <hr>

                        <h2>What is a bookmark?</h2>
                        <p>A bookmark is one of your favourite POIs that you hope to view conveniently at any time.</p>
                        <hr>
                        
                        <h2>What is My Location?</h2>
                        <p>My Location is a POI defined by the user, not a built-in POI.</p>
                        <hr>
                        
                        <h2>How can I bookmark / unbookmark a location?</h2>
                        <p>Click on any icon that represents a POI on the map and choose <b>Add Bookmark</b> or <b>Remove Bookmark</b> before pressing <b>Save Changes</b>.</p>
                        <hr>
                        
                        <h2>How many My Locations are allowed to be created?</h2>
                        <p>Currently, <i>BuildingBuddy</i> allows the user to create an unlimited number of POIs.</p>
                        <hr>
                        
                        <h2>How can I explore another building?</h2>
                        <p>Click <b>Start</b> in the menu on top and use the building selector underneath the app logo.</p>
                        <hr>
                        
                        <h2>How can I get to a specific floor and see all the locations? (Building + Floor Code Shortcut)</h2>
                        <p>A straightforward way to view a specific floor will be using the </b>Discover</b> button in the app menu (Windows user: <code>Alt + D</code>, Mac user: <code>control + option + D</code>). There you will be able to choose any floor you wish to visit.</p>
                        <p>Alternatively, when you are not on the splash screen (the opening screen), you will see a search bar at the bottom left. Use the <i>building code + floor code</i> shortcut to explore any floor.</p>
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
                        <p>For example, if you want to visit the second floor at Middlesex College, just enter <b>MC2F</b> and click <b>Go</b> (or hit <code>Enter</code>).</p>
                        <p>The search bar remembers your last successful search phrase, so you can simply click <b>Go</b> to stay on that floor.</p>
                        <hr>
                        
                        <h2>How can I create a location?</h2>
                        <p>As a user, you can only create a POI when you're in Exploration Mode (where you see a layer filter on the left). Click on any empty spot on the map. Edit the name and description for this location. Click <b>Save Changes</b> when you're done.</p>
                        <p>When the user is in Bookmark Manager, My Locations Manager, Search mode or Discovery mode, they cannot add new POIs. Those interfaces are designed to manage existing POIs.</p>
                        <p>You can add a POI by clicking <b>Start</b> in the menu and <b>Explore</b> the building where you hope to add a POI.</p>
                        <hr>
                        
                        <h2>Why can't I edit the room numbers for My Locations?</h2>
                        <p>Room numbers are currently not available for My Locations, but you can write room numbers and any useful information in the <b>Description</b> text box. What's cool, they are searchable!</p>
                        <hr>
                        
                        <h2>Why do I see only washrooms and elevators in Exploration Mode?</h2>
                        <p>The layer filter on the left panel shows only washrooms and accessible facilities by default. Click on other layers to see more categories of POIs. And yes, you can click on any layer again to toggle it off.</p>
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
                        
                        <h2>Can I bookmark a "My Location"?</h2>
                        <p>Sure! You can bookmark anything on any floor map.</p>
                        <hr>
                        
                        <h2>Can I delete My Location?</h2>
                        <p>Sure! You can do that! Just bear in mind that if you delete a My Location that has been bookmarked, you lose that bookmark too.</p>
                        <hr>
                        
                        <h2>What are "Nuke Bookmarks", "Nuke My Locations", and "Reset BuildingBuddy"?</h2>
                        <p>These three features will delete all your bookmarks, My Locations, and both. Your personalized data will be permanently erased, and the program will be restored to its default settings.</p>
                        <p>If you only choose to nuke all My Locations, they will also disappear from your Bookmarks if you have bookmarked them.</p>
                        <p>After you confirm your choice, the program will reboot automatically.</p>
                        <hr>
                        
                        <h2>Can I see all the POIs on all the floors in all the buildings?</h2>
                        <p>Use the search bar and click <b>Go</b> right away. You can either keep the placeholder phrase in the text bar or clear it before hitting <b>Go</b>.</p>
                        <hr>
                        
                        <h2>Some of my bookmarks are gone unknowingly. Why?</h2>
                        <p>Sorry about that! Our developers are updating this app regularly, so some POIs may have been deleted. When they no longer exist, they disappear from your Bookmarks too.</p>
                        <hr>
                        
                        <h2>How to quit the application safely?</h2>
                        <p>Click <b>Exit</b> from the main menu, or just hit <b>[X]</b> on top of the window.</p>
                        <hr>
                        
                        <h2>I am a developer. Can I add / edit / delete POIs or browse floor maps the same way?</h2>
                        <p>Yes, you can. Here's a few tips for developers:
                       <ol>
                       <li>Select <b>More</b> – <b>Developer Tool</b> (or hit <code>Ctrl + X</code>) and enter the correct security key to activate Development Mode.</li>
                       <li>You can exit Developer Tool by hitting <b>Logout</b>; you can also click <b>Exit</b> (or <b>[X]</b>) to quit the program.</li>
                       <li>You can only add, edit, or remove built-in POIs. </li>
                       <li>POI Name must not be empty; Room Number must be a positive integer; Category must be one of the following (case- and whitespace-sensitive): "Classroom","CompSci Spot", "Restaurant", "Lab", "Stairwell", "Elevator", "Entrance", "Exit", and "Washroom". If a facility is accessible, write in POI Description: "Accessible facility." Other notes also go to Description. (POI descriptions are searchable.)</li>
                       <li>You can still enter Discovery Mode or take advantage of the search bar and <i>building + floor code</i> shortcut (refer to the <b>MC2F</b> example above) to remain on a certain floor.</li>
                       <li>Developers cannot view the user's bookmarks, search queries, or any non-built-in POIs.</li>
                       <li>If you forget your security key, check <code>./data/security_key</code> or shoot us an email at <a href="mailto:jason@shew.cc">jason@shew.cc</a>.</li>
                       </ol>
                        </p>
                        <hr>
                        
                        <h2>I still need help!</h2>
                        <p>If you need further help or have spotted incorrect information, feel free to write us: <a href="mailto:jason@shew.cc">jason@shew.cc</a>.</p>
                        <hr>
                        </div>
                        """, "help.png");

            }
        });
        about.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               clearWindows(); // Closes all floating windows and displays the About page.
               new PopupView("About", """
                       <div style="font-family: Arial; text-align: center; font-size: 18">
                       <br>
                       <br>
                       <br>
                       <h1><i>BuildingBuddy</i></h1>

                       <h3>Version\s
                       """ + Main.currentAppVersion + """
                       </h3>

                       <h4>Developed by <span style="color:red;">""" + Main.developerName +
                       """
                       </span> at <span style="color:#6600cc;">UWO</span></h4>

                       <h3>Developers:
                       Arjuna Kadirgamar, Daniel Gomes, Robert Beemer, Jason Shew, Joshua Cini</h3>
                       <br>
                       <a href="https://wiki.csd.uwo.ca/display/COMPSCI2212W2023GROUP14/COMPSCI+2212+-+Winter+2023+-+Group+14+Home">Project Website</a> | <a href="https://github.com/dan1el5/BuildingBuddy">GitHub</a><br>
                       </div>
                       """, "BB_icon.png");

           }
        });
        exit.addMouseListener(new MouseAdapter() {
              @Override
              public void mouseClicked(MouseEvent e) {
                  clearWindows();
                  // Quits the program.
                  System.exit(0); // Exits the program with status code 0.
              }
        });

        weather.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 // Displays current weather.
                 clearWindows();
                 try {
                     new WeatherInfo();
                 } catch (IOException ex) {
                     throw new RuntimeException(ex);
                 }
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
        bookmarks.addActionListener(e -> {clearWindows(); new GUIForPOIs("BMK"); GUI.frame.setTitle("BuildingBuddy by " + Main.developerName + " – Version " + Main.currentAppVersion + " – Bookmark Manager");});
        // Adds hotkey to this menu option.
        bookmarks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
        // Links the menu option with My Location editor.
        myLocations.addActionListener(e -> {clearWindows(); new GUIForPOIs("UDP"); GUI.frame.setTitle("BuildingBuddy by " + Main.developerName + " – Version " + Main.currentAppVersion + " – My Locations Manager");});
        // Adds hotkey to this menu option.
        myLocations.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));

        view.setMnemonic('V');
        discover.setMnemonic('D');
        more.setMnemonic('M');

        // Links the menu option with software version display.
        checkForUpdates.addActionListener(e -> {
            clearWindows();
            String result;
            try {
                result = Main.updateChecker();
            } catch (IOException ex) {
                result = "Something's wrong with the connection between BuildingBuddy and the remote server.<br><br>Please try again later.";
            }
            new PopupView("Software Update", """
                     <br><br><br><br><br><br><br><br>
                    <div style="font-family: Arial; font-size: 16px; text-align: center; color: green">""" +
                    result + """
                    </div>
                    """, "BB_icon.png");
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
                    developerTool.setVisible(false);
                    nukeBookmarks.setVisible(false);
                    nukeMyLocations.setVisible(false);
                    reset.setVisible(false);
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
                    GUI.frame.setTitle("BuildingBuddy by " + Main.developerName + " – Version " + Main.currentAppVersion + " – *** Development Mode ***");
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
        discover.add(mc);
        mc.add(mc0f);
        mc.add(mc1f);
        mc.add(mc2f);
        mc.add(mc3f);
        mc.add(mc4f);
        discover.add(kb);
        kb.add(kb0f);
        kb.add(kb1f);
        kb.add(kb2f);
        kb.add(kb3f);
        discover.add(pab);
        pab.add(pab0f);
        pab.add(pab1f);
        pab.add(pab2f);
        pab.add(pab3f);
        more.add(checkForUpdates);
        more.add(nukeBookmarks);
        more.add(nukeMyLocations);
        more.add(reset);
        more.add(developerTool);

        // Adds menu to menu bar.
        mb.add(start);
        mb.add(view);
        mb.add(discover);
        mb.add(weather);
        mb.add(help);
        mb.add(about);
        mb.add(more);
        mb.add(exit);


        // Sets the font, size, and color of each menu item.
        Font menuFont = new Font("Arial", Font.PLAIN, 18);
        ArrayList<JMenu> itemArray = new ArrayList<>(Arrays.asList(start, view, discover, weather, help, about, more, exit, logout));
        for (JMenu item : itemArray) {
            item.setFont(menuFont);
            item.setBorder(null);
            item.setForeground(mb.getForeground());
            item.setBackground(mb.getBackground());
            item.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
            item.setOpaque(true);
            item.setBackground(new Color(250,250,250));
            item.setForeground(Color.BLACK);
            item.setUI(new BasicMenuUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    super.paint(g, c);

                    FontMetrics fm = g.getFontMetrics();
                    Rectangle rect = c.getBounds();

                    int textWidth = fm.stringWidth(item.getText());
                    int x = rect.x + (rect.width - textWidth) / 2;
                    int y = rect.y + ((rect.height - fm.getHeight()) / 2) + fm.getAscent();

                    g.drawString(item.getText(), x, y);
                }
            });
            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    item.setBackground(new Color(78,37,130)); // change the button background color when hovered
                    item.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    item.setBackground(new Color(250,250,250)); // restore the default button background color
                    item.setForeground(Color.BLACK);
                }
            });

            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    MapView.cancelHighlight();
                }
            });
        }

        ArrayList<JMenuItem> menuItemArray = new ArrayList<>(Arrays.asList(bookmarks, myLocations, mc, mc0f, mc1f, mc2f, mc3f, mc4f, kb,kb0f, kb1f, kb2f, kb3f, pab, pab0f, pab1f, pab2f,pab3f, checkForUpdates,nukeBookmarks,nukeMyLocations,nukeBuiltInPOIs,reset,changeKey,developerTool));

        for (JMenuItem elem : menuItemArray) {
            elem.setFont(menuFont);
        }

        mb.setVisible(true);

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

    /**
     *  This method is to update the POI list shown in the POI selector in Discovery Mode.
     *
     * @param buildingFloorCode a building and floor code in the format such as "MC0F"
     */
    public void discovery(String buildingFloorCode) {
        buildingFloorCode = buildingFloorCode.toUpperCase();
        // Updates the POI list to be shown
        POISelector.currentCollection = new LinkedList<>();
        for (POI targetPoi: Data.builtInPOIs) {
            if (targetPoi.map.equalsIgnoreCase(buildingFloorCode + ".png"))
                POISelector.currentCollection.add(targetPoi);
        }
        if (!Main.devMode) {
            for (POI targetPoi : Data.userCreatedPOIs) {
                if (targetPoi.map.equalsIgnoreCase(buildingFloorCode + ".png"))
                    POISelector.currentCollection.add(targetPoi);
            }
        }
        if (POISelector.currentCollection != null) {
            // Updates current cursors
            Main.currentFloor = buildingFloorCode;
            if (buildingFloorCode.contains("MC")) {
                Main.currentFloor_MC = buildingFloorCode;
                Main.currentBuildingCode = "MC";
            }
            else if (buildingFloorCode.contains("KB")) {
                Main.currentFloor_KB = buildingFloorCode;
                Main.currentBuildingCode = "KB";
            }
            else if (buildingFloorCode.contains("PAB")) {
                Main.currentFloor_PAB = buildingFloorCode;
                Main.currentBuildingCode = "PAB";
            }

            // Builds a GUI for discoveries
            new GUIForPOIs("DIS");
            // Updates the frame title
            GUI.frame.setTitle("BuildingBuddy by " + Main.developerName + " – Version " + Main.currentAppVersion + " – Discovery Mode");
            // Updates search bar for user's convenience
            Search.userInput = buildingFloorCode;
            Search.successful = true;
        }
        // If no POIs can be shown, a hint pops up.
        else POIEditor.resultDisplay("No POIs are found on this map!", Color.PINK);
    }
}