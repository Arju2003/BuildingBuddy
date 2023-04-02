/**
 * @author Jason
 * PopupView Class
 * Handles popups in application
 */

package ca.uwo.csteam14;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class PopupView extends JDialog {
    // Create a new JDialog with the desired title
    JDialog dialog = new JDialog();

    /**
     * @param title
     * @param content
     */
    public PopupView(String title, String content, String iconFileName) {
        UIManager.put("TextArea.font", new Font("Arial", Font.PLAIN, 16));
        dialog.setTitle(title);
        dialog.setResizable(false);

        // Load the image file
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./images/"+iconFileName));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Scale the image to 150x150 pixels
        Image scaledImage = Objects.requireNonNull(image).getScaledInstance(150, 150, Image.SCALE_SMOOTH);

        // Create a new JLabel to hold the image
        JLabel imageLabel = new JLabel(new ImageIcon(Objects.requireNonNull(scaledImage)));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a new JPanel to hold the JLabel and set its layout
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Create a new JTextPane
        JTextPane textPane = new JTextPane();
        textPane.setPreferredSize(new Dimension(500, 350));
        textPane.setEditable(false);
        textPane.setContentType("text/html");
        textPane.setText(content);
        textPane.setFont(new Font("Arial", Font.PLAIN, 16));
        textPane.setMargin(new Insets(10, 10, 10, 10)); // top, left, bottom, right
        textPane.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    // Open the link in the user's default browser
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JViewport viewport = scrollPane.getViewport();
        viewport.setViewPosition(new Point(0, 0));
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMinimum());
        });

        // Add the JScrollPane and JPanel to the JDialog
        dialog.add(scrollPane);
        dialog.add(imagePanel, BorderLayout.WEST);

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
