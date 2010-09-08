package uk.ac.shef.dcs.twitter;

public class Tweet {
	private String text;
	private Long time;
	private String username;

	public Tweet() {

	}

	public String getText() {
		return text;
	}

	public Long getTime() {
		return time;
	}

	public String getUsername() {
		return username;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return username + ": " + text + " (" + time + ")";
	}
}
