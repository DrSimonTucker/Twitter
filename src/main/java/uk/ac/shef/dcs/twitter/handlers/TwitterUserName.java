package uk.ac.shef.dcs.twitter.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler for processing the twitter username
 * 
 * @author sat
 * 
 */
public class TwitterUserName extends DefaultHandler
{
   /** The text read by the XML parser */
   private String text = "";

   /** The username of the person reading the twitter thing */
   private String username = "";

   @Override
   public final void characters(final char[] ch, final int start, final int length)
         throws SAXException
   {
      text += new String(ch, start, length);
   }

   @Override
   public final void endElement(final String uri, final String localName, final String qName)
         throws SAXException
   {
      if ((localName + qName).equals("name"))
         username = text;
   }

   /**
    * Retrieve the username from the parsed stream
    * 
    * @return {@link String} the User Name of the authenticator
    */
   public final String getUserName()
   {
      return username;
   }

   @Override
   public final void startElement(final String uri, final String localName, final String qName,
         final Attributes attributes) throws SAXException
   {
      text = "";
   }

}
