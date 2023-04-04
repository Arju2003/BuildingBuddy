/**
 * This class represents the opening screen of this application.
 * It consists of a background image, a building selector, a button, amd an app logo.
 * @author Jason B. Shew
 * @version 1.0
 * @since 2023-03-07
 */

package ca.uwo.csteam14;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Constructs a splash screen.
 */
public class Splash extends JPanel {

    /** The original background image */
    private BufferedImage img;
    /** The scaled background image */
    private BufferedImage scaled;
    /** A GridBagConstraints setting to align components */
    protected GridBagConstraints everythingCentered;

    /**
     * @param backgroundImg The background image of the splash screen
     * @throws IOException  Throws an IO Exception when image file fails to load
     */
    public Splash(String backgroundImg) throws IOException{
        GUI.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AppMenu appMenu = new AppMenu(); // Loads the app menu bar
        GUI.frame.setJMenuBar(appMenu.load());
        this.setBackground(ImageIO.read(new File(backgroundImg))); // Sets background images
        centerEverything(); // Sets layout and aligns components
        this.setLayout(new GridBagLayout());
    }

    /**
     * @return  A Dimension object that represents a preferred size.
     */
    @Override
    public Dimension getPreferredSize() {
        return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(), img.getHeight());
    }

    /**
     * @param value A buffer image to be used as the background image
     */
    public void setBackground(BufferedImage value) {
        if (value != img) {
            this.img = value;
            repaint();
        }
    }

    /**
     *  This method invalidates the container.
     */
    @Override
    public void invalidate() {
        super.invalidate();
        if (getWidth() > img.getWidth() || getHeight() > img.getHeight()) {
            scaled = getScaledInstanceToFill(img, getSize());
        } else { // If container is larger than the image, then scales it down
            scaled = img; // Otherwise just uses it
        }
    }

    /**
     * This method paints the scaled image on the <code>Graphics</code> object.
     * @param g the <code>Graphics</code> object to paint on
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (scaled != null) {
            int x = (getWidth() - scaled.getWidth()) / 2;
            int y = (getHeight() - scaled.getHeight()) / 2;
            g.drawImage(scaled, x, y, this);
        }
    }


    /**
     * Returns a scaled instance of a BufferedImage to fill a given Dimension size,
     * maintaining the original aspect ratio.
     *
     * @param img The BufferedImage to be scaled.
     * @param size The Dimension size to which the image will be scaled to fill.
     * @return A scaled instance of the BufferedImage that fills the given Dimension size.
     */
    public static BufferedImage getScaledInstanceToFill(BufferedImage img, Dimension size) {
        // Calculate the scaleFactor required to fill the given Dimension size while maintaining aspect ratio
        double scaleFactor = getScaleFactorToFill(img, size);

        // Return the scaled instance of the BufferedImage using the calculated scaleFactor
        return getScaledInstance(img, scaleFactor);
    }

    /**
     * Returns the scale factor required to fill a given Dimension size with an image,
     * maintaining the original aspect ratio.
     *
     * @param img The BufferedImage to be scaled.
     * @param size The Dimension size that the image should fill.
     * @return The scale factor required to fill the given Dimension size with the image.
     */
    public static double getScaleFactorToFill(BufferedImage img, Dimension size) {
        double dScale = 1;

        if (img != null) {
            // Get the dimensions of the input image
            int imageWidth = img.getWidth();
            int imageHeight = img.getHeight();

            // Calculate the scale factor required to fill the given Dimension size while maintaining aspect ratio
            double dScaleWidth = getScaleFactor(imageWidth, size.width);
            double dScaleHeight = getScaleFactor(imageHeight, size.height);
            dScale = Math.max(dScaleHeight, dScaleWidth);
        }

        return dScale;
    }


    /**
     * Returns the scale factor required to scale a master size to a target size.
     *
     * @param iMasterSize The original size of the object to be scaled.
     * @param iTargetSize The target size to which the object should be scaled.
     * @return The scale factor required to scale the master size to the target size.
     */
    public static double getScaleFactor(int iMasterSize, int iTargetSize) {
        return (double) iTargetSize / (double) iMasterSize;
    }


    /**
     * Returns a scaled instance of a BufferedImage using a given scale factor and rendering hints.
     *
     * @param img The BufferedImage to be scaled.
     * @param dScaleFactor The scale factor to be used for scaling the image.
     * @return A scaled instance of the BufferedImage using the given scale factor and default rendering hints.
     */
    public static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor) {
        // Use bilinear interpolation for image scaling
        Object hint = RenderingHints.VALUE_INTERPOLATION_BILINEAR;

        // Preserve the original image's transparency
        boolean bTransparency = true;

        return getScaledInstance(img, dScaleFactor, hint, bTransparency);
    }


    /**
     * Returns a scaled instance of a BufferedImage with the specified scale factor, rendering hints, and quality settings.
     *
     * @param img The BufferedImage to be scaled.
     * @param dScaleFactor The scale factor to be used for scaling the image.
     * @param hint The rendering hint to be used for image scaling.
     * @param bHighQuality A flag indicating whether to use high-quality scaling or not.
     * @return A scaled instance of the BufferedImage with the specified scale factor, rendering hints, and quality settings.
     */
    protected static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor, Object hint, boolean bHighQuality) {

        BufferedImage imgScale;

        // Calculate the scaled image dimensions
        int iImageWidth = (int) Math.round(img.getWidth() * dScaleFactor);
        int iImageHeight = (int) Math.round(img.getHeight() * dScaleFactor);

        if (dScaleFactor <= 1.0d) {
            // Scale down the image using the getScaledDownInstance method
            imgScale = getScaledDownInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);
        } else {
            // Scale up the image using the getScaledUpInstance method
            imgScale = getScaledUpInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);
        }

        return imgScale;
    }


    /**
     * This method scales down a given BufferedImage to the specified targetWidth and targetHeight,
     * using the specified hint for rendering and the specified boolean value for high quality rendering.
     * If targetWidth or targetHeight is 0, the method will return an image with dimensions of 1x1 pixel.
     *
     * @param img the BufferedImage to be scaled down
     * @param targetWidth the desired width of the scaled image
     * @param targetHeight the desired height of the scaled image
     * @param hint the rendering hint to be used during scaling
     * @param higherQuality a boolean value indicating whether high quality rendering should be used
     * @return the scaled down BufferedImage
     */
    protected static BufferedImage getScaledDownInstance(BufferedImage img,
                                                         int targetWidth,
                                                         int targetHeight,
                                                         Object hint,
                                                         boolean higherQuality) {

        // Determine the type of the BufferedImage
        int type = (img.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        // Initialize the return image with the original image
        BufferedImage ret = img;

        // If targetWidth or targetHeight is greater than 0, scale the image down to the desired size
        if (targetHeight > 0 || targetWidth > 0) {
            int w, h;
            if (higherQuality) {
                // Use multi-step technique: start with original size, then
                // scale down in multiple passes with drawImage()
                // until the target size is reached
                w = img.getWidth();
                h = img.getHeight();
            } else {
                // Use one-step technique: scale directly from original
                // size to target size with a single drawImage() call
                w = targetWidth;
                h = targetHeight;
            }

            do {
                if (higherQuality && w > targetWidth) {
                    w /= 2;
                    if (w < targetWidth) {
                        w = targetWidth;
                    }
                }

                if (higherQuality && h > targetHeight) {
                    h /= 2;
                    if (h < targetHeight) {
                        h = targetHeight;
                    }
                }

                // Create a temporary BufferedImage object for the scaled image
                BufferedImage tmp = new BufferedImage(Math.max(w, 1), Math.max(h, 1), type);

                // Get the Graphics2D object for the temporary BufferedImage object
                Graphics2D g2 = tmp.createGraphics();

                // Set the rendering hint for the Graphics2D object
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);

                // Draw the scaled image onto the temporary BufferedImage object
                g2.drawImage(ret, 0, 0, w, h, null);

                // Dispose the Graphics2D object
                g2.dispose();

                // Set the temporary BufferedImage object as the return image
                ret = tmp;
            } while (w != targetWidth || h != targetHeight);
        } else {
            // If targetWidth and targetHeight are both 0, return an image with dimensions of 1x1 pixel
            ret = new BufferedImage(1, 1, type);
        }

        // Return the scaled down BufferedImage
        return ret;
    }


    /**
     * Returns a scaled up instance of the provided BufferedImage.
     *
     * @param img the BufferedImage to be scaled up
     * @param targetWidth the desired width of the scaled up image
     * @param targetHeight the desired height of the scaled up image
     * @param hint the hint to be used for scaling interpolation
     * @param higherQuality whether to use a multi-step technique for higher quality scaling
     * @return a BufferedImage scaled up to the specified dimensions
     */
    protected static BufferedImage getScaledUpInstance(BufferedImage img,
                                                       int targetWidth,
                                                       int targetHeight,
                                                       Object hint,
                                                       boolean higherQuality) {

        int type = BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w < targetWidth) {
                w *= 2;
                if (w > targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h < targetHeight) {
                h *= 2;
                if (h > targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;

        } while (w != targetWidth || h != targetHeight);
        return ret;
    }


    /**
     * Centers everything in the current container by creating a new {@link GridBagConstraints}
     * object and setting its gridwidth to {@link GridBagConstraints#REMAINDER} and its insets
     * to 0 on the left, 0 on the top, 60 on the right, and 0 on the bottom.
     */
    private void centerEverything() {
        // Creates a new instance of GridBagConstraints for centering everything
        everythingCentered = new GridBagConstraints();

        // Makes the gridwidth span the entire frame
        everythingCentered.gridwidth = GridBagConstraints.REMAINDER;

        // Sets bottom margin to 60 pixels and leaves the rest to 0
        everythingCentered.insets = new Insets(0, 0, 60, 0);
    }


    /**
     * This class adds a component to a parent panel.
     * @param comp A JComponent to be added to the main panel
     */
    public void load(JComponent comp) {
            this.add(comp, everythingCentered);
    }

    /**
     * This method builds a splash screen.
     * @throws IOException when the background image file cannot be read
     */
    public void build() throws IOException {
        // Scales app logo and adds it to a JLabel object for use
        ImageIcon icon = new ImageIcon("./images/BB_icon.png");
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(300, 300,  Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        JLabel logo = new JLabel();
        logo.setIcon(icon);
        this.load(logo);

        // Generates a building selector for navigation across buildings
        String[] buildings = {"Middlesex College", "Kresge Building", "Physics & Astronomy Building"};
        JComboBox<? extends String> buildingSelector = new JComboBox<>(buildings);

        buildingSelector.setRenderer(Main.centerRenderer);
        switch (Main.currentBuildingCode) {
            case ("MC") -> buildingSelector.setSelectedItem("Middlesex College");
            case ("KB") -> buildingSelector.setSelectedItem("Kresge Building");
            case ("PAB") -> buildingSelector.setSelectedItem("Physics & Astronomy Building");
        }
        // Stylizes the building selector
        buildingSelector.setBounds(450, 300, 200, 100);
        Font font = new Font("Arial", Font.PLAIN, 18);
        buildingSelector.setFont(font);
        load(buildingSelector);

        // Defines the behaviour of the building selector
        buildingSelector.addItemListener(e -> {
            String selectedItem = (String) buildingSelector.getSelectedItem();
            setVisible(false);
            // Sets the background image according to the choice of building
            switch (Objects.requireNonNull(selectedItem)) {
                case "Middlesex College" -> {
                    try {
                        setBackground(ImageIO.read(new File("./images/MC_hero.png")));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case "Kresge Building" -> {
                    try {
                        setBackground(ImageIO.read(new File("./images/KB_hero.png")));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case "Physics & Astronomy Building" -> {
                    try {

                        setBackground(ImageIO.read(new File("./images/PAB_hero.png")));

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                default -> {
                }
            }
            setVisible(true);
        });

        // Adds an Explore button for jumping to the map viewer of that building
        JButton exploreButton = new JButton("Explore");
        exploreButton.setFont(font);
        exploreButton.addActionListener(e -> {
            GUI.frame.setMinimumSize(GUI.frame.getSize());
            GUI.frame.setMaximumSize(GUI.frame.getSize());
            GUI.frame.setPreferredSize(GUI.frame.getSize());
            GUI.frame.setResizable(false);
            // Get the selected item in the dropdown list
            String selectedItem = (String) buildingSelector.getSelectedItem();

            // Navigate to the corresponding building
            switch (Objects.requireNonNull(selectedItem)) {
                case "Middlesex College" -> {
                    new GUI("MC");
                    Main.currentBuildingCode = "MC";
                    Main.currentFloor = Main.currentFloor_MC;
                }
                case "Kresge Building" -> {
                    new GUI("KB");
                    Main.currentBuildingCode = "KB";
                    Main.currentFloor = Main.currentFloor_KB;
                }
                case "Physics & Astronomy Building" -> {
                    new GUI("PAB");
                    Main.currentBuildingCode = "PAB";
                    Main.currentFloor = Main.currentFloor_PAB;
                }
                default -> {
                }
            }
            GUI.frame.setTitle("BuildingBuddy by " + Main.developerName + " – Version " + Main.currentAppVersion + " – Exploration Mode");
        });
        load(exploreButton);
        GUI.frame.setContentPane(this);
        GUI.frame.pack();
        GUI.frame.setLocationRelativeTo(null); // always loads the interface at the center of the monitor regardless resolution
        GUI.frame.setVisible(true);
        GUI.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}