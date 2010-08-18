package uk.ac.shef.dcs.twitter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.scribe.http.Request;
import org.scribe.http.Response;
import org.scribe.http.Request.Verb;
import org.scribe.oauth.Scribe;
import org.scribe.oauth.Token;

public class OAuthHandler {
	private static Scribe scribeSingleton;

	public static void main(String[] args) throws IOException {
		OAuthHandler handler = new OAuthHandler();
		System.out.println(handler.getTimelineString());
	}

	public String authenticate() throws IOException {
		return getBody("http://api.twitter.com/1/account/verify_credentials.xml");
	}

	private void createPropsFile(File f) throws IOException {
		Properties props = new Properties();

		// Get the cons=umer Key
		String consKey = JOptionPane
				.showInputDialog("Please enter the Consumer Key");
		String consSec = JOptionPane
				.showInputDialog("Please enter the Consumer Secret");

		// Set the properties
		props.put("consumer.key", consKey);
		props.put("consumer.secret", consSec);
		props.put("request.token.verb", "POST");
		props
				.put("request.token.url",
						"http://twitter.com/oauth/request_token");
		props.put("access.token.verb", "POST");
		props.put("access.token.url", "http://twitter.com/oauth/access_token");
		props.put("callback.url", "oob");

		FileOutputStream fos = new FileOutputStream(f);
		props.storeToXML(fos, null);
		fos.close();
	}

	private Token getAccessToken() throws IOException {
		File accessTokenFile = new File(System.getProperty("user.home")
				+ File.separator + ".com262token");

		if (!accessTokenFile.exists()) {
			try {
				Scribe scribe = getScribe();
				Token tok = scribe.getRequestToken();

				// Take the user to the login page
				String url = "https://twitter.com/oauth/authenticate?oauth_token="
						+ tok.getToken();
				Desktop.getDesktop().browse(new URI(url));

				String verifier = JOptionPane
						.showInputDialog("Enter PIN Code:");
				Token aTok = scribe.getAccessToken(tok, verifier);

				// Store the access token
				ObjectOutputStream oos = new ObjectOutputStream(
						new FileOutputStream(accessTokenFile));
				oos.writeObject(aTok);
				oos.close();
			}

			catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					accessTokenFile));
			Token tok = (Token) ois.readObject();
			ois.close();
			return tok;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String getBody(String url) throws IOException {
		Token aToken = getAccessToken();
		Scribe scribe = getScribe();
		Request request = new Request(Verb.GET, url);
		scribe.signRequest(request, aToken);
		Response resp = request.send();
		return resp.getBody();
	}

	private Properties getProperties() throws IOException {
		String propertiesLocation = System.getProperty("user.home")
				+ File.separator + ".com262-twitter.properties";
		File f = new File(propertiesLocation);
		if (!f.exists())
			createPropsFile(f);

		Properties props = new Properties();
		InputStream fis = new FileInputStream(f);
		props.loadFromXML(fis);
		fis.close();

		return props;
	}

	private Scribe getScribe() throws IOException {
		if (scribeSingleton == null)
			scribeSingleton = new Scribe(getProperties());
		return scribeSingleton;
	}

	public String getTimelineString() throws IOException {
		return getBody("http://api.twitter.com/1/statuses/home_timeline.xml");
	}
}
