package com.xpanxion.statusboard.page;

import com.xpanxion.statusboard.LocatorType;
import com.xpanxion.statusboard.config.SiteFactory;

public class HomePage extends PageObject {
	
	private static class SingletonHolder {
		private static final HomePage INSTANCE = SiteFactory.getPage("homePage",HomePage.class);
	}

	public HomePage() {
		this("Statusboard", "Statusboard News", "h1", LocatorType.TAG_NAME);
	}

	public HomePage(String title, String headingText, String headingLocator, LocatorType headingLocatorType) {
		super(title, headingText, headingLocator, headingLocatorType);
	}

	public static HomePage getInstance() {
		return SingletonHolder.INSTANCE;
	}

}
