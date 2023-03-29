package ca.uwo.csteam14;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Search {

    protected static String userInput;
    protected static POI firstResult = null;

    private static final String defaultText = "Search anything ...";

    public Search() {
        JPanel searchTool = new JPanel();
        JTextField input = new JTextField(defaultText);
        input.setPreferredSize(new Dimension(260,40));

        JButton goButton = new JButton("Go");
        goButton.setPreferredSize(new Dimension(40,40));
        goButton.addActionListener(e->{
            if (e.getSource() == goButton) {
                userInput = input.getText();
                LinkedList<POI> pl = searchResults(userInput);
                if (pl != null) {
                    new GUIForPOIs("SRC");
                }
                else {
                    AppMenu.clearWindows(); // close all floating windows (the WeatherInfo window, specifically)
                    // instantiate an object of the other class
                    String iconURL = "https://png.pngtree.com/png-vector/20220616/ourlarge/pngtree-sad-apologizing-emoticon-holding-a-sign-with-the-text-sorry-png-image_5103588.png";
                    new PopupView("Sorry...", """
                   <div style="font-family: Arial">
                   <p style="text-align:right"><img src="
                    """ + iconURL + """
                    " width="150"></p>
                    <br />
                   <h1>Uh-oh!</h1>
                   <h2>No search results for \""""
                            + userInput +
                   """
                   ". <br >
                   Try another set of keywords.</h2>
                    </div>
                    """);

                }
            }
        });
        searchTool.setLayout(new GridBagLayout());
        searchTool.add(input);
        searchTool.add(goButton);
        if (GUI.frame.getContentPane().equals(GUI.canvas))
            GUI.canvas.load(searchTool, 'L');
        else if (GUI.frame.getContentPane().equals(GUIForPOIs.secondary))
            GUIForPOIs.secondary.load(searchTool,'L');
        else System.out.println(GUI.frame.getContentPane());
    }

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
