package ca.uwo.csteam14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class PopupView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel title;

    private JScrollPane sp;
    private JTextArea content;

    public PopupView(String type) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(600,800); // Set the size of the dialog

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

// Calculate the center position
        int dialogX = (screenSize.width - getWidth()) / 2;
        int dialogY = (screenSize.height - getHeight()) / 2;

// Set the position of the dialog to the center of the screen
        setLocation(dialogX, dialogY);

        if (Objects.equals(type, "Help")) {
            title.setText("HELP");
            content.setText("What is a POI?\n" +
                    "\n" +
                    "A POI is a point of interest, .... \n" +
                    "\n" +
                    "What is a bookmark?\n" +
                    "\n" +
                    "A bookmark is a .....\n" +
                    "\n" +
                    "What is My Location?\n" +
                    "\n" +
                    "My Location is a user-defined POI ...\n" +
                    "\n" +
                    "How to view or edit bookmarks?\n" +
                    "\n" +
                    "Enter View – Bookmarks, or press CTRL + B.\n" +
                    "\n" +
                    "How to view or edit My Locations?\t\n" +
                    "\n" +
                    "Enter View – My Locations, or press CTRL + L. \n" +
                    "\n" +
                    "How to quit the application safely?\n" +
                    "\n" +
                    "Choose Exit from the main menu, or just click [X] on top of the window.\n" +
                    "\n" +
                    "I still need help!\n" +
                    "Feel free to write us: abc@123.com");
        }
        else if (Objects.equals(type, "About")) {
            title.setText("ABOUT");
            content.setText("BuildingBuddy\n" +
                    "\n" +
                    "Version: 1.0\n" +
                    "\n" +
                    "Developed by Team 14 at UWO\n" +
                    "\n" +
                    "Developers: Daniel, Robert, Joshua, Arjuna, Jason\n" +
                    "\n" +
                    "Project Website: https://wiki.csd.uwo.ca/display/COMPSCI2212W2023GROUP14/COMPSCI+2212+-+Winter+2023+-+Group+14+Home)\nGitHub: https://github.com/dan1el5/BuildingBuddy");
        }


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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
