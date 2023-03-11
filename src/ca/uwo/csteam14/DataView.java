package ca.uwo.csteam14;

import javax.swing.*;

public class DataView  extends JPanel {


    private JPanel viewer = new JPanel();


    //main class
    public DataView(String label, String[] dataString) {

        //create a new label
        JLabel listLabel = new JLabel(label);

        JList<? extends String> dataList = new JList<>(dataString);

        //set a selected index
        dataList.setSelectedIndex(0);

        viewer.add(listLabel);

        //add list to panel
        viewer.add(dataList);
    }

    public JPanel load() {
        return viewer;
    }
}
