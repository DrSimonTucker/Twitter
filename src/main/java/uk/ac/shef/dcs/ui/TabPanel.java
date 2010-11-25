package uk.ac.shef.dcs.ui;

import javax.swing.JTabbedPane;

import uk.ac.shef.dcs.Dictionary;
import uk.ac.shef.dcs.Iterator;
import uk.ac.shef.dcs.SocialPost;

public class TabPanel extends JTabbedPane
{
	public TabPanel(Dictionary<String, SocialPost> dict)
	{
		//Add a tab for each key in the dictionary
		Iterator<String> keyIt = dict.keys();
		
		while(keyIt.hasNext())
		{
			String key = keyIt.getNext();
			
			//Get the values for this key
			Iterator<SocialPost> valIt = dict.values(key);
			PostList panel = new PostList();
			while(valIt.hasNext())
			{
				SocialPost post = valIt.getNext();
				panel.addSinglePost(post);
			}
			
			addTab(key.toString(), panel);
		}
	}
}
