package uk.ac.shef.dcs.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * A panel showing a single image
 * 
 * @author sat
 * 
 */
public class ImagePanel extends JPanel
{
   /** This image shown in this panel */
   private final Image img;

   /**
    * Constructor
    * 
    * @param imgIn
    *           The image to display
    */
   public ImagePanel(final Image imgIn)
   {
      this.img = imgIn;
   }

   @Override
   public final Dimension getPreferredSize()
   {
      return new Dimension(img.getHeight(null), img.getWidth(null));
   }

   @Override
   public final void paint(final Graphics g)
   {
      super.paint(g);
      g.drawImage(img, 0, 0, null);
   }
}
