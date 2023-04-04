/**
 * @author Jason
 * Search Class
 * Search for POIs in application, display results
 */

package ca.uwo.csteam14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class Search {

    /** The String the user searched for */
    protected static String userInput;
    /** The first result returned */
    protected static POI firstResult = null;
    /** The default text in the search box before user searches */
    private static final String defaultText = "Search anything . . .";

    private static boolean successful = false;

    /**
     *
     */
    public Search() {
        JPanel searchTool = new JPanel();
        JTextField input = new JTextField(defaultText);
        if (userInput != null && successful) input.setText(userInput);
        input.setPreferredSize(new Dimension(260,40));
        input.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                input.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        JButton goButton = new JButton("Go");
        goButton.setPreferredSize(new Dimension(40,40));
        goButton.addActionListener(e->{
            if (e.getSource() == goButton) {
                userInput = input.getText();
                LinkedList<POI> pl = searchResults(userInput);
                if (pl != null) {
                    new GUIForPOIs("SRC");
                    GUI.frame.setTitle("BuildingBuddy by " + Main.developerName + " – Version " + Main.currentAppVersion + " – Discovery Mode");
                    successful = true;
                    input.setText(userInput);
                }
                else {
                    AppMenu.clearWindows(); // close all floating windows (the WeatherInfo window, specifically)
                    // instantiate an object of the other class
                    new PopupView("Sorry...", """
                   <div style="font-family: Arial">
                   <br />
                   <br />
                   <br />
                   <br />
                   <br />
                   <h1>Uh-oh!</h1>
                   <h2>No search results for \""""
                            + userInput +
                   """
                   ". <br >
                   Try another set of keywords.</h2>
                    </div>
                    ""","sorry.png");
                    successful = false;
                    userInput = null;
                    input.setText(defaultText);
                }
            }
        });
        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Simulate a button click when the Enter key is pressed in the text field
                    goButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Do nothing
            }
        });
        searchTool.setLayout(new GridBagLayout());
        searchTool.add(input);
        searchTool.add(goButton);
        if (GUI.frame.getContentPane() == (GUI.canvas))
            GUI.canvas.load(searchTool, 'L');
        else if (GUI.frame.getContentPane() == (GUIForPOIs.secondary))
            GUIForPOIs.secondary.load(searchTool,'L');
    }

    /**
     * Creates a list of POIs that are returned as search results from the given search
     * @param userInput the String the user searched
     * @return a LinkedList of search results (POIs)
     */
    public static LinkedList<POI> searchResults(String userInput) {

        Search.userInput = userInput;

        ArrayList<POI> results = new ArrayList<>();

        if (userInput == null || userInput.equals(defaultText)) {
            results.addAll(Data.builtInPOIs);
            if (!Main.devMode)
                results.addAll(Data.userCreatedPOIs);
        }
        else  {
            for (POI p : Data.builtInPOIs) {
                if (!results.contains(p) && (p.name.toLowerCase().contains(userInput.toLowerCase().strip()) || userInput.toLowerCase().strip().contains(p.name.toLowerCase()))) {
                    results.add(p);
                    continue;
                }
                if (!results.contains(p) && (p.description.toLowerCase().contains(userInput.toLowerCase().strip()) || userInput.toLowerCase().strip().contains(p.description.toLowerCase()))) {
                    results.add(p);
                    continue;
                }

                if (!results.contains(p) && (p.category.toLowerCase().contains(userInput.toLowerCase().strip())) || (userInput.toLowerCase().strip().contains(p.category.toLowerCase())))
                    results.add(p);

                if (!results.contains(p) && (p.map.toLowerCase().contains(userInput.toLowerCase().strip())) || (userInput.toLowerCase().strip().contains(p.map.toLowerCase())))
                    results.add(p);
            }
            if (!Main.devMode) {
                for (POI p : Data.userCreatedPOIs) {
                    if (!results.contains(p) && (p.name.toLowerCase().contains(userInput.toLowerCase().strip()) || userInput.toLowerCase().strip().contains(p.name.toLowerCase()))) {
                        results.add(p);
                        continue;
                    }
                    if (!results.contains(p) && (p.description.toLowerCase().contains(userInput.toLowerCase().strip()) || userInput.toLowerCase().strip().contains(p.description.toLowerCase()))) {
                        results.add(p);
                    }
                }
            }
        }

        if (results.size() > 0) {
            firstResult = results.get(0);
            return new LinkedList<>(results);
        }
        return null;
    }
}
