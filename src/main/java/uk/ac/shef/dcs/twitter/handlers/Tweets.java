package uk.ac.shef.dcs.twitter.handlers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uk.ac.shef.dcs.twitter.Tweet;

public class Tweets extends DefaultHandler {
	private Tweet currTweet = new Tweet();

	DateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss ZZZZ yyyy");
	boolean inUser = false;

	private int pointer = 0;

	private String text = "";

	private final Tweet[] tweetArr;

	public Tweets(Tweet[] toFill) {
		tweetArr = toFill;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		text += new String(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ((localName + qName).equals("status")) {
			if (pointer < tweetArr.length)
				tweetArr[pointer++] = currTweet;
			currTweet = new Tweet();
		} else if ((localName + qName).equals("text"))
			currTweet.setText(text);
		else if ((localName + qName).equals("screen_name"))
			currTweet.setUsername(text);
		else if (!inUser && (localName + qName).equals("created_at"))
			try {
				currTweet.setTime(df.parse(text).getTime());
			} catch (ParseException e) {
				throw new SAXException(e);
			}
		else if ((localName + qName).equals("user"))
			inUser = false;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		text = "";
		if ((localName + qName).equals("user"))
			inUser = true;
	}
}
