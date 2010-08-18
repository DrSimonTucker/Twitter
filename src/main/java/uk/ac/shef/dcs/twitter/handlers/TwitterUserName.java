package uk.ac.shef.dcs.twitter.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TwitterUserName extends DefaultHandler {

	String text = "";

	String username = "";

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		text += new String(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ((localName + qName).equals("name"))
			username = text;
	}

	public String getUserName() {
		return username;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		text = "";
	}

}
