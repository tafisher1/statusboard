package com.xpanxion.statusboard.page;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import com.xpanxion.statusboard.DriverUtil;
import com.xpanxion.statusboard.LocatorType;
import com.xpanxion.statusboard.config.SiteFactory;

public class LogInPage extends PageObject {
	private static final String SUCCESS = "Signed in successfully.";
	
	private static final String EMAIL_TXT = "user_email";
	private static final String PWD_TXT = "user_password";
	private static final String COMMIT_BTN = "commit";
	
	private static class SingletonHolder {
		private static final LogInPage INSTANCE = SiteFactory.getLogInPage();
	}
	
	private Properties userProps;
	
	public LogInPage() {
		this("Statusboard", "Log In", "h2", LocatorType.TAG_NAME);
	}

	public LogInPage(String title, String headingText, String headingLocator, LocatorType headingLocatorType) {
		super(title, headingText, headingLocator, headingLocatorType);

		userProps = new Properties();
		
		try(Reader reader = Files.newBufferedReader(Paths.get("src/main/resources", "user.properties"))) {
			userProps.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logInAdmin() {
		logIn("adminuser");
	}

	public void logIn(String user) {
		String email = userProps.getProperty(user + ".email");
		String password = userProps.getProperty(user + ".password");
		
		logIn(email, password);
	}
	
	public void logIn(String email, String password) {
		//enter user's email into the field #user_email
		//<input autofocus="autofocus" type="email" value="" name="user[email]" id="user_email">
		DriverUtil.enterText(EMAIL_TXT, LocatorType.ID, email);
		
		//enter the user's password into the field #user_password
		//<input autocomplete="off" type="password" name="user[password]" id="user_password">
		DriverUtil.enterText(PWD_TXT, LocatorType.ID, password);
		
		//click the Log in button
		//<input type="submit" name="commit" value="Log in">
		DriverUtil.click(COMMIT_BTN, LocatorType.NAME);
	}
	
	public boolean loggedIn() {
		//<p class="notice">Signed in successfully.</p>		
		return SUCCESS.equalsIgnoreCase(getPageNotice());
	}

	public static LogInPage getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
