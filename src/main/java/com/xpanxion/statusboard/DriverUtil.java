package com.xpanxion.statusboard;

import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.xpanxion.statusboard.config.SiteFactory;

/**
 * Utility class to hold the Selenium web driver. This is to keep the majority
 * of the Selenium logic out of the tests and framework.
 * 
 * @author tfisher *
 */
public class DriverUtil {
	/** Selenium web driver to connect to the web objects. */
	private static WebDriver DRIVER = SiteFactory.getObject("driver", WebDriver.class);
	private static URI SITEBASE = SiteFactory.getObject("sitebase", URI.class);

	/**
	 * Initializes the utility with the driver to use.
	 * 
	 */
	public static void initialize() {
		getDriver().manage().deleteAllCookies();
		getDriver().get(SITEBASE.toString());
	}

	/**
	 * Initializes the utility with the driver to use.
	 * 
	 * @param driver
	 *            web driver to use
	 */
	@Deprecated
	public static void initialize(WebDriver driver) {
		DRIVER = driver;
		
		initialize();
	}

	/**
	 * Closes the driver when done. 
	 * 
	 * When the driver comes from a Spring bean, it will handle the life of the driver.
	 * 
	 * If the driver comes from a test, the test should handle the life of the driver.
	 */
	@Deprecated
	public static void shutDown() {
		DRIVER.quit();
	}
	
	public static void goToPage(String url) {
		DRIVER.get(url);
	}

	/**
	 * Returns the Selenium web driver for other objects to use.
	 * 
	 * @return the Selenium web driver
	 */
	public static WebDriver getDriver() {
		return DRIVER;
	}

	/**
	 * Returns the base URI for the website
	 * @return base URI
	 */
	public URI getSiteBase() {
		return SITEBASE;
	}

	/**
	 * Click on an element. This is assuming that there is only 1 element or
	 * that the element that matches would be correctly returned by
	 * WebDriver.findElement
	 * 
	 * @param id
	 *            By to locate the web element.
	 */
	public static void click(By id) {
		if (isPresent(id)) {
			DRIVER.findElement(id).click();
		}
	}

	/**
	 * Returns whether there are any objects on the page that match the locator
	 * given.
	 * 
	 * @param id
	 *            By to locate the web element.
	 * @return true when there are one or more elements on the page that match
	 *         the locator.
	 */
	public static boolean isPresent(By id) {
		return DRIVER.findElements(id).size() > 0;
	}

	/**
	 * Click an element based on the given criteria. This is assuming that there
	 * is only 1 element or that the element that matches would be correctly
	 * returned by WebDriver.findElement
	 * 
	 * @param locator
	 *            String identifier to locate the element
	 * @param byType
	 *            identifies which By locator to use
	 */
	public static void click(String locator, LocatorType byType) {
		click(getBy(locator, byType));
	}
	
	public static void enterText(String locator, LocatorType byType, String text) {
		enterText(getBy(locator, byType), text);
	}

	private static void enterText(By by, String text) {
		getDriver().findElement(by).sendKeys(text);
	}
	
	public static String getText(String locator, LocatorType byType) {
		return getText(getBy(locator, byType));
	}

	private static String getText(By by) {
		return getDriver().findElement(by).getText();
	}

	/**
	 * Returns whether there are any objects on the page that match the location
	 * criteria given
	 * 
	 * @param locator
	 *            String identifier to locate the element
	 * @param byType
	 *            identifies which By locator to use
	 * @return true when there are one or more elements on the page that match
	 *         the locator.
	 */
	public static boolean isPresent(String locator, LocatorType byType) {
		return isPresent(getBy(locator, byType));
	}

	/**
	 * Returns the By to locate the web element that will be needed. If
	 * LocatorType is null or a new unimplemented value, By.id will be used.
	 * 
	 * @param locator
	 *            String identifier to locate the element
	 * @param byType
	 *            identifies which By locator to use
	 * @return By to locate the web element. default: By.id
	 */
	public static By getBy(String locator, LocatorType byType) {
		By retVal = null;

		switch (byType) {
		case CLASS_NAME:
			retVal = By.className(locator);
			break;
		case CSS_SELECTOR:
			retVal = By.cssSelector(locator);
			break;
		case ID:
			retVal = By.id(locator);
			break;
		case LINK_TEXT:
			retVal = By.linkText(locator);
			break;
		case NAME:
			retVal = By.name(locator);
			break;
		case PARTIAL_LINK_TEXT:
			retVal = By.partialLinkText(locator);
			break;
		case TAG_NAME:
			retVal = By.tagName(locator);
			break;
		case XPATH:
			retVal = By.xpath(locator);
			break;
		default:
			retVal = By.id(locator);
			break;
		}

		return retVal;
	}
	
	public static boolean isVisible(String locator, LocatorType byType) {
		return isVisible(getBy(locator, byType));
	}

	private static boolean isVisible(By by) {
		if (isPresent(by)) {
			return DRIVER.findElement(by).isDisplayed();
		}
		
		return false;
	}

}
