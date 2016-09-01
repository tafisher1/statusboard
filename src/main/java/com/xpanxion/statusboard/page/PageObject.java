package com.xpanxion.statusboard.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.xpanxion.statusboard.DriverUtil;
import com.xpanxion.statusboard.LocatorType;
import com.xpanxion.statusboard.navigation.Navigation;

public class PageObject implements IPageObject {
	private String title;
	private String headingText;
	private String headingLocator;
	
	private Navigation header;
	private Navigation footer;

	private LocatorType headingLocatorType;
	
	public PageObject(String title,String headingText,String headingLocator,LocatorType headingLocatorType) {
		this.title = title;
		this.headingText = headingText;
		this.headingLocator = headingLocator;
		this.headingLocatorType = headingLocatorType;
	}

	@Override
	public Navigation getHeader() {
		return header;
	}

	@Override
	public Navigation getFooter() {
		return footer;
	}
	
	@Override
	public String getCurrentHeadingText() {
		WebElement element = DriverUtil.getDriver().findElement(DriverUtil.getBy(getHeadingLocator(), getHeadingLocatorType()));
		
		return element.getText();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Page{");
		str.append("title: ").append(title);
		str.append(" heading: ").append(headingText);
		str.append(" locator{ ");
		str.append("text:").append(headingLocator);
		str.append(" type:").append(headingLocatorType);
		str.append("}");
		str.append("}");
		return str.toString();
	}

	@Override
	public boolean verifyHeadingText() {
		return getExpectedHeadingText().equals(getCurrentHeadingText());
	}

	@Override
	public String getCurrentPageTitle() {
		return DriverUtil.getDriver().getTitle();
	}

	@Override
	public boolean verifyPageTitle() {		
		return getExpectedPageTitle().equals(DriverUtil.getDriver().getTitle());
	}

	@Override
	public String getExpectedHeadingText() {
		return headingText;
	}

	@Override
	public String getExpectedPageTitle() {
		return title;
	}

	@Override
	public void waitToLoad() {
		(new WebDriverWait(DriverUtil.getDriver(), 10))
				.until(ExpectedConditions.visibilityOfElementLocated(DriverUtil.getBy(getHeadingLocator(), getHeadingLocatorType())));

	}

	/**
	 * @return the headingLocator
	 */
	public String getHeadingLocator() {
		return headingLocator;
	}

	/**
	 * @return the headingLocatorType
	 */
	public LocatorType getHeadingLocatorType() {
		return headingLocatorType;
	}
	
	public String getPageNotice() {
		return DriverUtil.getText("notice", LocatorType.CLASS_NAME);
	}
	
	public String getPageAlert() {
		return DriverUtil.getText("alert", LocatorType.CLASS_NAME);
	}

}
