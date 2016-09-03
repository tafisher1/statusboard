/**
 * 
 */
package com.xpanxion.statusboard;

import org.openqa.selenium.By;
import java.util.function;

/**
 * Enum to control which By locator to use.
 * 
 * @author tfisher
 */
public enum LocatorType {
	/** a By which locates elements by the value of the "id" attribute */
ID(By::id),
/** a By which locates elements by the value of the "class" attribute. */
CLASS_NAME(By::className),
/** a By which locates elements by CSS. */
CSS_SELECTOR(By::cssSelector),
/** a By which locates A elements by the exact text it displays */
LINK_TEXT(By::linkText),
/** a By which locates elements by the value of the "name" attribute. */
NAME(By::name),
/** a By which locates A elements that contain the given link text */
PARTIAL_LINK_TEXT(By::partialLinkText),
/** a By which locates elements by their tag name */
TAG_NAME(By::tagName),
/** a By which locates elements via XPath */
XPATH(By::xpath);

/** Function to transform the string locator into a Selenium By object */
private Function<By, String> byTransform;

private LocatorType(Function<By, String> by){
	this.byTransform = by;
}

public By getBy(String locator) {
	return byTransform.apply(locator);
}

	public static LocatorType getLocatorType(String value) {
		switch (value.toUpperCase()) {
		case "ID":
			return ID;
		case "CSSSELECTOR":
		case "CSS_SELECTOR":
			return LocatorType.CSS_SELECTOR;
		case "CLASSNAME":
		case "CLASS_NAME":
			return LocatorType.CLASS_NAME;
		case "LINKTEXT":
		case "LINK_TEXT":
			return LocatorType.LINK_TEXT;
		case "NAME":
			return LocatorType.NAME;
		case "PARTIALLINKTEXT":
		case "PARTIAL_LINK_TEXT":
			return LocatorType.PARTIAL_LINK_TEXT;
		case "TAGNAME":
		case "TAG_NAME":
			return LocatorType.TAG_NAME;
		case "XPATH":
			return LocatorType.XPATH;
		default:
			return LocatorType.ID;
		}
	}
}
