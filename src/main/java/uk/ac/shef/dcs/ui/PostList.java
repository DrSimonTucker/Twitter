package uk.ac.shef.dcs.ui;

import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import uk.ac.shef.dcs.ComCollection;
import uk.ac.shef.dcs.Iterator;
import uk.ac.shef.dcs.SocialPost;

public class PostList extends JPanel implements ListCellRenderer
{

   DefaultListModel model = new DefaultListModel();

   List<SocialPost> posts = new LinkedList<SocialPost>();

   public PostList()
   {
      initList();
   }

   public void addPost(ComCollection<SocialPost> post)
   {
      Iterator<SocialPost> iterator = post.getIterator();
      while(iterator.hasNext())
    	  model.addElement(iterator.getNext());
   }
   
   public void addSinglePost(SocialPost post)
   {
      model.addElement(post);
   }

   @Override
   public Component getListCellRendererComponent(JList list, Object value, int index,
         boolean isSelected, boolean cellHasFocus)
   {
      return new PostPanel((SocialPost) value);
   }

   private void initList()
   {
      JList list = new JList(model);
      list.setCellRenderer(this);
      JScrollPane scroller = new JScrollPane(list);
      this.add(scroller);
   }
}
