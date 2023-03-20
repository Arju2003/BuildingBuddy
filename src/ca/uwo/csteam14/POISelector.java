package ca.uwo.csteam14;

import javax.swing.*;
import java.awt.*;

public class POISelector extends JPanel {
    JPanel selector = new JPanel();

    public POISelector(Data collection) {
        selector.setLayout(new GridLayout(0,1));
        JLabel POIName = new JLabel();
        for (POI i: collection) {
            POIName.setText(i.name + i.belongsTo);
            selector.add(POIName);
        }
    }
}
