package uk.ac.shef.dcs.ui;

import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import uk.ac.shef.dcs.ComCollection;
import uk.ac.shef.dcs.Iterator;
import uk.ac.shef.dcs.SocialPost;

/**
 * A JPanel containing a list of posts
 * 
 * @author sat
 * 
 */
public class PostList extends JPanel implements ListCellRenderer
{

   /** List model to be used */
   private final DefaultListModel model = new DefaultListModel();

   /**
    * Constructor
    */
   public PostList()
   {
      initList();
   }

   /**
    * Adds a collection of post to the display
    * 
    * @param posts
    *           the collection of posts to be added
    */
   public final void addPost(final ComCollection<SocialPost> posts)
   {
      Iterator<SocialPost> iterator = posts.getIterator();
      while (iterator.hasNext())
         model.addElement(iterator.getNext());
   }

   /**
    * Adds a a single post to the panel list
    * 
    * @param post
    *           The post to be added to the list
    */
   public final void addSinglePost(final SocialPost post)
   {
      model.addElement(post);
   }

   @Override
   public final Component getListCellRendererComponent(final JList list, final Object value,
         final int index, final boolean isSelected, final boolean cellHasFocus)
   {
      return new PostPanel((SocialPost) value);
   }

   /**
    * Initialise the list
    */
   private void initList()
   {
      JList list = new JList(model);
      list.setCellRenderer(this);
      JScrollPane scroller = new JScrollPane(list);
      this.add(scroller);
   }
}
