package uk.ac.shef.dcs.twitter;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uk.ac.shef.dcs.twitter.handlers.Tweets;
import uk.ac.shef.dcs.twitter.handlers.TwitterUserName;

public class TwitterProxy {

	static OAuthHandler handler = new OAuthHandler();

	public static Tweet[] getFriendsTweets(int n) {
		Tweet[] tweetArr = new Tweet[n];

		try {
			String xmlString = handler.getFriends();
			parse(xmlString, new Tweets(tweetArr));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tweetArr;
	}

	public static Tweet[] getLatestPublicTweets(int n) {
		Tweet[] tweetArr = new Tweet[n];

		try {
			String xmlString = handler.getAll();
			parse(xmlString, new Tweets(tweetArr));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tweetArr;
	}

	public static Tweet[] getLatestTweets(int n) {
		Tweet[] tweetArr = new Tweet[n];

		try {
			String xmlString = handler.getHome();
			parse(xmlString, new Tweets(tweetArr));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tweetArr;
	}
	public static String getTwitterUserName() {
		try {
			String xmlString = handler.authenticate();
			TwitterUserName parsed = (TwitterUserName) parse(xmlString,
					new TwitterUserName());
			return parsed.getUserName();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static void main(String[] args) {
		Tweet[] tweets = TwitterProxy.getFriendsTweets(5);
		for (Tweet tweet : tweets)
			System.err.println(tweet);
	}
	private static DefaultHandler parse(String str, DefaultHandler handler)
			throws IOException {
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(new InputSource(new StringReader(str)), handler);
		} catch (SAXException e) {
			throw new IOException(e);
		} catch (ParserConfigurationException e) {
			throw new IOException(e);
		}

		return handler;
	}
}
