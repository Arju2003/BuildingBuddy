import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Container extends JPanel {
    private BufferedImage img;
    private BufferedImage scaled;
    protected final int ONE_COLUMN = 1;
    protected final int TWO_COLUMNS = 2;
    private JPanel leftPanel;
    private JPanel rightPanel;
    protected GridBagConstraints everythingCentered;


    public Container(String backgroundImg, int style) throws IOException {
        this.setBackground(ImageIO.read(new File(backgroundImg)));

        if (style == ONE_COLUMN) {
            centerEverything();
            this.setLayout(new GridBagLayout());
        }

        if (style == TWO_COLUMNS) {
            this.setLayout(new GridLayout(1,2));
            leftPanel = new JPanel();
            leftPanel.setOpaque(false);
            rightPanel = new JPanel();
            rightPanel.setOpaque(false);
            this.add(leftPanel);
            this.add(rightPanel);
            centerEverything();
            leftPanel.setLayout(new GridBagLayout());
            rightPanel.setLayout(new GridBagLayout());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(), img.getHeight());
    }

    public void setBackground(BufferedImage value) {
        if (value != img) {
            this.img = value;
            repaint();
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (getWidth() > img.getWidth() || getHeight() > img.getHeight()) {
            scaled = getScaledInstanceToFill(img, getSize());
        } else {
            scaled = img;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (scaled != null) {
            int x = (getWidth() - scaled.getWidth()) / 2;
            int y = (getHeight() - scaled.getHeight()) / 2;
            g.drawImage(scaled, x, y, this);
        }
    }



    public static BufferedImage getScaledInstanceToFill(BufferedImage img, Dimension size) {

        double scaleFactor = getScaleFactorToFill(img, size);

        return getScaledInstance(img, scaleFactor);

    }

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

    public static double getScaleFactor(int iMasterSize, int iTargetSize) {

        return (double) iTargetSize / (double) iMasterSize;

    }

    public static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor) {

        return getScaledInstance(img, dScaleFactor, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);

    }

    protected static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor, Object hint, boolean bHighQuality) {

        BufferedImage imgScale;

        int iImageWidth = (int) Math.round(img.getWidth() * dScaleFactor);
        int iImageHeight = (int) Math.round(img.getHeight() * dScaleFactor);

//        System.out.println("Scale Size = " + iImageWidth + "x" + iImageHeight);
        if (dScaleFactor <= 1.0d) {

            imgScale = getScaledDownInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        } else {

            imgScale = getScaledUpInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        }

        return imgScale;

    }

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

    private void centerEverything() {
        everythingCentered = new GridBagConstraints();
        everythingCentered.gridwidth = GridBagConstraints.REMAINDER;
        everythingCentered.insets = new Insets(0, 0, 20, 0);
    }
    public void load(JComponent comp, char position) {
        if (position == 'C') {
            this.add(comp, everythingCentered);
        }

        else if (position == 'L') {
            leftPanel.add(comp, everythingCentered);
        }

        else if (position == 'R') {
            rightPanel.add(comp, everythingCentered);
        }
    }
}