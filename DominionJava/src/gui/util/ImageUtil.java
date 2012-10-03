package gui.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.EnumMap;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import dominion.card.Card;

public class ImageUtil
{
    public static final int                 FULL_SIZE_IMAGE      = 150;
    public static final int                 THUMBNAIL_SIZE_IMAGE = 100;

    private static EnumMap<Card, ImageIcon> fullImageMap         = new EnumMap<Card, ImageIcon>(Card.class);

    private ImageUtil()
    {
    }

    public static ImageIcon getCardImage(Card cardToGet)
    {
        return getCardImage(Locale.getDefault(),
                            cardToGet);
    }

    public static ImageIcon getCardImage(Locale local,
                                         Card cardToGet)
    {
        if (fullImageMap.get(cardToGet) == null)
        {
            // fetch card from storage if it is not already known from the EnumMap cache.
            fullImageMap.put(cardToGet,
                             createImageIconFromResource("resources" + GlobalDefs.FILE_SEPARATOR
                                                                 + "dominioncardresource" + GlobalDefs.FILE_SEPARATOR
                                                                 + cardToGet.getCardSet()
                                                                            .toString()
                                                                            .toLowerCase() + GlobalDefs.FILE_SEPARATOR
                                                                 + getLocalAsString(local) + GlobalDefs.FILE_SEPARATOR
                                                                 + cardToGet.toString()
                                                                            .toLowerCase() + ".gif",
                                                         ""));

        }
        return fullImageMap.get(cardToGet);
    }

    public static void scaleImageToLabel(Card cardToGet,
                                         JLabel iconLabel)
    {
        if (!iconLabel.isShowing() || iconLabel.getWidth() == 0 || iconLabel.getHeight() == 0)
        {
            return;
        }
        // int iSquareSize = Math.min(iconLabel.getWidth(),
        // iconLabel.getHeight());
        iconLabel.setIcon(getScaledCard(cardToGet,
                                        iconLabel.getWidth(),
                                        iconLabel.getHeight()));
    }

    /**
     * Resizes an image using a Graphics2D object backed by a BufferedImage.
     * 
     * @param srcImage
     *            - source image to scale
     * @param width
     *            - desired width
     * @param height
     *            - desired height
     * @return - the new resized image
     */
    public static ImageIcon getScaledCard(Card cardToGet,
                                          int width,
                                          int height)
    {
        return new ImageIcon(getScaledImage(getCardImage(cardToGet).getImage(),
                                            width,
                                            height));
    }

    /**
     * Resizes an image using a Graphics2D object backed by a BufferedImage.
     * 
     * @param srcImage
     *            - source image to scale
     * @param width
     *            - desired width
     * @param height
     *            - desired height
     * @return - the new resized image
     */
    public static Image getScaledImage(Image srcImage,
                                       int width,
                                       int height)
    {
        BufferedImage resizedImg = new BufferedImage(width,
                                                     height,
                                                     BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImage,
                     0,
                     0,
                     width,
                     height,
                     null);
        g2.dispose();
        return resizedImg;
    }

    /**
     * Creates an ImageIcon if the path is valid.
     * 
     * @param String
     *            - resource path
     * @param String
     *            - description of the file
     */
    private static ImageIcon createImageIconFromResource(String path,
                                                         String description)
    {
        URL imgURL = ClassLoader.getSystemResource(path);
        if (imgURL != null)
        {
            return new ImageIcon(imgURL,
                                 description);
        }
        else
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private static String getLocalAsString(Locale local)
    {
        if (local.equals(Locale.ENGLISH) || local.equals(Locale.CANADA) || local.equals(Locale.US))
        {
            return "english";

        }
        else if (local.equals(Locale.CANADA_FRENCH) || local.equals(Locale.FRANCE))
        {
            return "french";
        }
        else
        {
            throw new IllegalArgumentException("Unsupported locale: " + local.toString());
        }
    }
}
