/**
 * @author Jason
 * PopupView Class
 * Handles popups in application
 */

package ca.uwo.csteam14;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class PopupView extends JDialog {
    // Create a new JDialog with the desired title
    JDialog dialog = new JDialog();

    /**
     * @param title
     * @param content
     */
    public PopupView(String title, String content) {
        UIManager.put("TextArea.font", new Font("Arial", Font.PLAIN, 16));
        dialog.setTitle(title);
        dialog.setResizable(false);
        // Create a new JTextPane
        JTextPane textPane = new JTextPane();
        textPane.setPreferredSize(new Dimension(500, 350));
        textPane.setEditable(false);
        textPane.setContentType("text/html");
        textPane.setText(content);
        textPane.setFont(new Font("Arial", Font.PLAIN, 16));
        textPane.setMargin(new Insets(10, 10, 10, 10)); // top, left, bottom, right
        textPane.addHyperlinkListener(HyperlinkEvent::getEventType);
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JViewport viewport = scrollPane.getViewport();
        viewport.setViewPosition(new Point(0, 0));
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMinimum());
        });

        // Add the JScrollPane to the JDialog
        dialog.add(scrollPane);

        // Create a JButton to close the dialog
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.dispose());
        closeButton.setFocusTraversalKeysEnabled(true);
        dialog.getRootPane().setDefaultButton(closeButton);
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // Center the window on the screen
                dialog.setLocationRelativeTo(null);
            }
        });
        dialog.pack();
        // Display the JDialog
        dialog.setVisible(true);
    }
}