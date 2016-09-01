/**
 * 
 */
package com.xpanxion.statusboard;

/**
 * Enum to control which By locator to use.
 * 
 * @author tfisher
 */
public enum LocatorType {
	/** By.id which locates elements by the value of the "id" attribute. */
	ID,
	/** By.cssSelector which locates elements by CSS. */
	CSS_SELECTOR,
	/**
	 * By.className which locates elements by the value of the "class" attribute
	 */
	CLASS_NAME,
	/** By.linkText which locates A elements by the exact text it displays */
	LINK_TEXT,
	/** By.name which locates A elements that contain the given link text */
	NAME,
	/**
	 * By.partialLinkText which locates A elements that contain the given link
	 * text
	 */
	PARTIAL_LINK_TEXT,
	/** By.tagName which locates elements by their tag name */
	TAG_NAME,
	/** By.xpath which locates elements via XPath */
	XPATH;

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
