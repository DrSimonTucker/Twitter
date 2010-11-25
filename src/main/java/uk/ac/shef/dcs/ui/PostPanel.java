package uk.ac.shef.dcs.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import uk.ac.shef.dcs.SocialPost;

/**
 * A JPanel containing a single post
 * 
 * @author sat
 * 
 */
public class PostPanel extends JPanel
{

   /** The post to be display */
   private final SocialPost post;

   /**
    * Constructor
    * 
    * @param displayPost
    *           The post this panel should display
    */
   public PostPanel(final SocialPost displayPost)
   {
      post = displayPost;
      initPanel();
   }

   /**
    * Prepare the panel for use
    */
   private void initPanel()
   {
      this.setLayout(new BorderLayout());
      this.add(new ImagePanel(post.getAvatar()), BorderLayout.WEST);
      this.add(new JTextArea(post.getText()), BorderLayout.CENTER);
      this.add(new JLabel(post.getTime()), BorderLayout.SOUTH);

      this.setBorder(BorderFactory.createLineBorder(Color.black));
   }
}
