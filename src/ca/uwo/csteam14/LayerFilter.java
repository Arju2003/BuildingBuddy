package ca.uwo.csteam14;

import javax.swing.*;
import java.awt.*;

public class LayerFilter  extends JPanel {

    private JPanel viewer = new JPanel();

    //main class
    public LayerFilter(String label, String[] dataString) {

        //create a new label
        JLabel listLabel = new JLabel(label);


        viewer.add(listLabel);

        JList<? extends String> dataList = new JList<>(dataString);


        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        Font font = new Font("Arial", Font.ROMAN_BASELINE, 18);

        JCheckBox[] checkboxes = new JCheckBox[dataString.length];


        for (int i = 0; i < checkboxes.length; ++i) {
            JCheckBox checkbox = new JCheckBox(dataString[i]);
            checkboxes[i] = checkbox;
        }

        for (int j = 0; j < checkboxes.length; ++j) {
            checkboxes[j].setFont(font);
            checkboxPanel.add(checkboxes[j]);
        }

        viewer.add(checkboxPanel, BorderLayout.CENTER);

    }

    public JPanel load() {
        return viewer;
    }
}
