package ca.uwo.csteam14;
import javax.swing.*;
import java.awt.*;

public class LayerFilter  extends JPanel {

    private final JPanel viewer = new JPanel();

    private final JCheckBox[] checkboxes;

    //main class
    public LayerFilter(String title, String[] dataString) {

        //create a new label
        JLabel layerTitle = new JLabel(title);

        viewer.add(layerTitle);

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        Font font = new Font("Arial", Font.PLAIN, 18);

        checkboxes = new JCheckBox[dataString.length];


        for (int i = 0; i < checkboxes.length; ++i) {
            JCheckBox checkbox = new JCheckBox(dataString[i]);
            if (dataString[i].contains("Washrooms") || dataString[i].contains("Accessibility")) {
                checkbox.setEnabled(false);
                checkbox.setSelected(true);
                checkbox.setFocusable(false);
            }
            checkboxes[i] = checkbox;
        }

        for (JCheckBox checkbox : checkboxes) {
            checkbox.setFont(font);
            checkboxPanel.add(checkbox);
        }

        viewer.add(checkboxPanel, BorderLayout.CENTER);

    }

    public JPanel load() {
        return viewer;
    }

    public void hideCheckbox(String label) {
        for (JCheckBox c : checkboxes) {
            if (c.getText().contains(label)) c.setVisible(false);
        }
    }
}
