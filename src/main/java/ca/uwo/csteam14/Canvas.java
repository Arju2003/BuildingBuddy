package ca.uwo.csteam14;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Generates a canvas (container) to hold GUI elements
 * This class creates a panel that serves as the main UI and contains every visual element on the screen.
 *
 * @author Jason B. Shew
 * @version 1.0
 * @since 2023-03-07
 */

public class Canvas extends JPanel {

    /** The background image of the canvas. */
    private BufferedImage img;
    /** The scaled background image of the canvas. */
    private BufferedImage scaled;
    /** The left half of the canvas. */
    protected final JPanel leftPanel;
    /** The right half of the canvas. */
    protected final JPanel rightPanel;
    /** A layout constraint that centrally aligns all elements. */
    protected GridBagConstraints everythingCentered;


    /**
     * Constructs a canvas object.
     * This class creates an empty canvas to hold visual elements.
     *
     * @param backgroundImg The background image file path.
     * @throws IOException I/O Exception is thrown when file is not readable.
     */
    public Canvas(String backgroundImg) throws IOException {
        // Sets the background image and layout for the canvas.
        this.setBackground(ImageIO.read(new File(backgroundImg)));
        this.setLayout(new GridBagLayout());
        leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        leftPanel.setLayout(new GridBagLayout());
        rightPanel.setLayout(new GridLayout());
        this.setComponentZOrder(leftPanel, 0);
        this.setComponentZOrder(rightPanel, 0);
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        gbcLeft.weightx = 0.25;
        gbcLeft.weighty = 1.0;
        gbcLeft.fill = GridBagConstraints.BOTH;
        this.add(leftPanel,gbcLeft);
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.gridx = 1;
        gbcRight.gridy = 0;
        gbcRight.weightx = 0.75;
        gbcRight.weighty = 1.0;
        gbcRight.fill = GridBagConstraints.BOTH;
        this.add(rightPanel, gbcRight);
        centerEverything();
    }


    /**
     * Returns the preferred size of this component. If an image has been set using
     * the {@code setImg} method, the preferred size will be the same as the size of
     * the image. Otherwise, the default preferred size of the component is returned.
     *
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (img == null) {
            // If no image has been set, return the default preferred size
            return super.getPreferredSize();
        } else {
            // If an image has been set, return a dimension with the same size as the image
            return new Dimension(img.getWidth(), img.getHeight());
        }
    }

    /**
     * Sets the background image of this component to the specified {@code BufferedImage}.
     * If the specified image is different from the current image, the image is updated
     * and the component is repainted.
     *
     * @param value The new background image for this component.
     */
    public void setBackground(BufferedImage value) {
        if (value != img) {
            // If the new image is different from the current image, update the image and repaint the component
            this.img = value;
            repaint();
        }
    }


    /**
     * Invalidates this component's layout and marks it as needing to be laid out again.
     * If the component's width or height is greater than the size of the current background image,
     * the image is scaled to fit the component's size using {@code getScaledInstanceToFill},
     * and the scaled image is stored in the {@code scaled} field. Otherwise, the {@code scaled}
     * field is set to the current image.
     */
    @Override
    public void invalidate() {
        super.invalidate();
        if (getWidth() > img.getWidth() || getHeight() > img.getHeight()) {
            // If the component's width or height is greater than the size of the current image,
            // scale the image to fit the component's size
            scaled = getScaledInstanceToFill(img, getSize());
        } else {
            // If the component's size is smaller than the image size, use the original image
            scaled = img;
        }
    }


    /**
     * Paints this component. If a scaled background image is available (i.e., if the component's size
     * is greater than the size of the current image), the scaled image is drawn at the center of the component.
     *
     * @param g The graphics context to use for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (scaled != null) {
            // If a scaled background image is available, center it in the component
            int x = (getWidth() - scaled.getWidth()) / 2;
            int y = (getHeight() - scaled.getHeight()) / 2;
            g.drawImage(scaled, x, y, this);
        }
    }

    /**
     * Returns a scaled instance of the specified image that fills the specified size.
     *
     * @param img  The image to scale.
     * @param size The size to fill.
     * @return A new scaled instance of the image.
     */
    public static BufferedImage getScaledInstanceToFill(BufferedImage img, Dimension size) {
        double scaleFactor = getScaleFactorToFill(img, size);
        return getScaledInstance(img, scaleFactor);
    }

    /**
     * Calculates the scale factor needed to fill the specified size with the specified image.
     *
     * @param img  The image to scale.
     * @param size The size to fill.
     * @return The scale factor needed to fill the size with the image.
     */
    public static double getScaleFactorToFill(BufferedImage img, Dimension size) {
        double dScale = 1;

        if (img != null) {
            int imageWidth = img.getWidth();
            int imageHeight = img.getHeight();

            double dScaleWidth = getScaleFactor(imageWidth, size.width);
            double dScaleHeight = getScaleFactor(imageHeight, size.height);

            dScale = Math.max(dScaleHeight, dScaleWidth);
        }

        return dScale;
    }

    /**
     * Calculates the scale factor needed to scale an image from the master size to the target size.
     *
     * @param iMasterSize The master size of the image.
     * @param iTargetSize The target size of the image.
     * @return The scale factor needed to scale the image from the master size to the target size.
     */
    public static double getScaleFactor(int iMasterSize, int iTargetSize) {
        return (double) iTargetSize / (double) iMasterSize;
    }


    /**
     * Returns a scaled instance of the given image with the specified scale factor and interpolation hints.
     *
     * @param img The image to be scaled.
     * @param dScaleFactor The scale factor by which the image should be scaled.
     * @return A scaled instance of the given image.
     */
    public static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor) {
        return getScaledInstance(img, dScaleFactor, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
    }

    /**
     * Returns a scaled instance of the given image with the specified scale factor, interpolation hint, and quality.
     *
     * @param img The image to be scaled.
     * @param dScaleFactor The scale factor by which the image should be scaled.
     * @param hint The interpolation hint used for scaling the image.
     * @param bHighQuality Whether the scaled image should be of high quality or not.
     * @return A scaled instance of the given image.
     */
    protected static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor, Object hint, boolean bHighQuality) {

        BufferedImage imgScale;
        int iImageWidth = (int) Math.round(img.getWidth() * dScaleFactor);
        int iImageHeight = (int) Math.round(img.getHeight() * dScaleFactor);

        if (dScaleFactor <= 1.0d) {
            imgScale = getScaledDownInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);
        } else {
            imgScale = getScaledUpInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);
        }

        return imgScale;
    }

    /**
     * Returns a scaled-down instance of the given BufferedImage, with the specified target width and height.
     *
     * @param img          the original BufferedImage to be scaled down
     * @param targetWidth  the desired width of the scaled image
     * @param targetHeight the desired height of the scaled image
     * @param hint         the rendering hint to be used for the image scaling operation
     * @param higherQuality true if a multi-step scaling technique should be used for higher quality, false for a one-step technique
     * @return a scaled-down instance of the given BufferedImage, with the specified target width and height
     */
    protected static BufferedImage getScaledDownInstance(BufferedImage img,
                                                         int targetWidth,
                                                         int targetHeight,
                                                         Object hint,
                                                         boolean higherQuality) {

        int type = (img.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = img;
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

                BufferedImage tmp = new BufferedImage(Math.max(w, 1), Math.max(h, 1), type);
                Graphics2D g2 = tmp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
                g2.drawImage(ret, 0, 0, w, h, null);
                g2.dispose();

                ret = tmp;
            } while (w != targetWidth || h != targetHeight);
        } else {
            ret = new BufferedImage(1, 1, type);
        }
        return ret;
    }

    /**
     * Returns a scaled-up version of the given image to match the target width and height.
     *
     * @param img the original image to be scaled
     * @param targetWidth the desired width of the scaled image
     * @param targetHeight the desired height of the scaled image
     * @param hint the rendering hint to be used for the scaling operation
     * @param higherQuality flag indicating whether a multi-step technique should be used for higher quality scaling
     * @return a scaled-up version of the original image with the target width and height
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
     * Sets the GridBagConstraints to center everything within a container.
     */
    private void centerEverything() {
        everythingCentered = new GridBagConstraints();
        everythingCentered.gridwidth = GridBagConstraints.REMAINDER;
        everythingCentered.insets = new Insets(0, 0, 30, 0);
    }

    /**
     * Replaces the specified component with a new component in the left or right panel
     * with a centered layout.
     *
     * @param comp the component to replace
     * @param position 'L' to replace in left panel, 'R' to replace in right panel
     */
    public void replaceWith(JComponent comp, char position) {
        JPanel panel = rightPanel;
        if (position == 'L') {
            panel = leftPanel;
        }
        removeComponent(comp, panel);
        panel.add(comp, everythingCentered);
    }

    /**
     * Removes a specific component from a JPanel.
     *
     * @param comp  the component to be removed
     * @param panel the JPanel from which the component is to be removed
     */
    private void removeComponent(JComponent comp, JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component.getClass() == comp.getClass()) {
                panel.remove(component);
            }
        }
        panel.revalidate(); // Revalidate the panel after removing components
        panel.repaint(); // Repaint the panel after removing components
    }


    /**
     * Adds a component to the left or right panel of this frame.
     *
     * @param comp the component to be added
     * @param position the position of the panel where the component will be added
     *                 'L' for left panel, 'R' for right panel
     */
    public void load(JComponent comp, char position) {
        if (position == 'L') {
            leftPanel.add(comp, everythingCentered);
        }
        else if (position == 'R') {
            rightPanel.add(comp, everythingCentered);
        }
    }
}