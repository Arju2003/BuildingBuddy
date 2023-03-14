package ca.uwo.csteam14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class PopupView extends JDialog {
    private JPanel contentPane;
    private JButton button;
    private JLabel title;

    private JScrollPane sp;
    private JTextArea content;

    public PopupView(String type) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(button);
        setSize(600,800); // Set the size of the dialog

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

// Calculate the center position
        int dialogX = (screenSize.width - getWidth()) / 2;
        int dialogY = (screenSize.height - getHeight()) / 2;

// Set the position of the dialog to the center of the screen
        setLocation(dialogX, dialogY);

        if (Objects.equals(type, "Help")) {
            title.setText("HELP");
            content.setText("""
                    What is a POI?

                    A POI is a point of interest, namely a location on the map.

                    What is a bookmark?

                    A bookmark is one of your favourite POIs

                    What is My Location?

                    My Location is a POI you defined, not a built-in POI.

                    How to view or edit bookmarks?

                    Enter View – Bookmarks, or press CTRL + B.

                    How to view or edit My Locations?

                    Enter View – My Locations, or press CTRL + L.

                    How to choose another building?

                    Click Start in the menu on top.
                    How to quit the application safely?

                    Choose Exit from the main menu, or just click [X] on top of the window.

                    I still need help!
                    Feel free to write us: jason@shew.cc""");
        }
        else if (Objects.equals(type, "About")) {
            title.setText("ABOUT");
            content.setText("""
                    BuildingBuddy

                    Version: 1.0

                    Developed by Team 14 at UWO

                    Developers: Daniel, Robert, Joshua, Arjuna, Jason

                    Project Website: https://wiki.csd.uwo.ca/display/COMPSCI2212W2023GROUP14/COMPSCI+2212+-+Winter+2023+-+Group+14+Home)
                    GitHub: https://github.com/dan1el5/BuildingBuddy""");
        }


        button.setText("Close");
        button.addActionListener(e -> onOK());


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add code here
        dispose();
    }

    private void onCancel() {
        // add code here if necessary
        dispose();
    }
}