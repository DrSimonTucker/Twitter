package uk.ac.shef.dcs.twitter;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uk.ac.shef.dcs.twitter.handlers.TwitterUserName;

public class TwitterProxy {

	static OAuthHandler handler = new OAuthHandler();

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
		System.out.println("Username = " + TwitterProxy.getTwitterUserName());
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
