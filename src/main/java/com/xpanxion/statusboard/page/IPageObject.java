package com.xpanxion.statusboard.page;

import com.xpanxion.statusboard.navigation.Navigation;

public interface IPageObject {
	public Navigation getHeader();

	public Navigation getFooter();

	public String getCurrentHeadingText();

	public String getExpectedHeadingText();

	public boolean verifyHeadingText();

	public String getCurrentPageTitle();

	public String getExpectedPageTitle();

	public boolean verifyPageTitle();

	public void waitToLoad();
	
	public String getPageNotice();
	
	public String getPageAlert();
}
