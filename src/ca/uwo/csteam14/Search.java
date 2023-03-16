package ca.uwo.csteam14;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Search {
    private String userInput;
    private LinkedList result;


    public Search() {
        JPanel searchTool = new JPanel();
        JTextField input = new JTextField("Search anything ...");
        input.setPreferredSize(new Dimension(260,40));
        JButton goButton = new JButton("Go");
        goButton.setPreferredSize(new Dimension(40,40));
        searchTool.setLayout(new GridBagLayout());
        searchTool.add(input);
        searchTool.add(goButton);
        GUI.primary.load(searchTool, 'L');
    }


    public String showResults(String userInput) {
        return userInput;
    }
}
