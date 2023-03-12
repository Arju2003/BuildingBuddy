package ca.uwo.csteam14;

import javax.swing.*;
import java.awt.*;

public class DataView  extends JPanel {

    private JPanel viewer = new JPanel();

    //main class
    public DataView(String label, String[] dataString) {

        //create a new label
        JLabel listLabel = new JLabel(label);


        viewer.add(listLabel);

        JList<? extends String> dataList = new JList<>(dataString);

        // set a selected index
        dataList.setSelectedIndex(0);

        viewer.add(dataList, BorderLayout.CENTER);
    }

    public JPanel load() {
        return viewer;
    }
}
