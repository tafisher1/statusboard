package com.xpanxion.statusboard.navigation;

import com.xpanxion.statusboard.DriverUtil;
import com.xpanxion.statusboard.LocatorType;
import com.xpanxion.statusboard.page.IPageObject;

/**
 * Class to hold properties related to links on a page.
 * 
 * A link consists of some way to identify the web element and a page that will
 * be opened by clicking the link.
 * 
 * @author tfisher
 *
 */
public class Link {
	/** String identifier to locate the element */
	private String locator;
	/** identifies which By locator to use */
	private LocatorType byType;
	/** the destination page of the link */
	private IPageObject page;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Link{");
		str.append("locator: ").append(locator);
		str.append(" locatorType: ").append(byType);
		str.append(" page: ").append(page);
		str.append("}");
		return str.toString();
	}

	/**
	 * Constructs the Link Object
	 * 
	 * @param locator
	 *            String identifier to locate the element
	 * @param byType
	 *            identifies which By locator to use
	 * @param page
	 *            the destination page of the link.
	 */
	public Link(String locator, LocatorType byType, IPageObject page) {
		this.locator = locator;
		this.byType = byType;
		this.page = page;
	}

	/**
	 * Constructs the Link Object with the default locator type of ID.
	 * 
	 * @param locator
	 *            String identifier to locate the element
	 * @param page
	 *            Page object that the link goes to.
	 */
	public Link(String locator, IPageObject page) {
		this(locator, LocatorType.ID, page);
	}

	/**
	 * Click an web element on the page that match the link's locator.
	 * 
	 * @return corresponding page object
	 */
	public IPageObject click() {
		DriverUtil.click(locator, byType);

		if(page != null) {
			page.waitToLoad();
		}

		return page;
	}

	/**
	 * Returns whether there are any web elements on the page that match the
	 * link's locator.
	 * 
	 * @return true when there are one or more elements on the page that match
	 *         the locator.
	 */
	public boolean isPresent() {
		return DriverUtil.isPresent(locator, byType);
	}

	/**
	 * Returns the href attribute from the web element found by the link's
	 * locator.
	 * 
	 * Always call this before clicking the link.
	 * 
	 * @return the href attribute from the anchor web element
	 */
	public String getHref() {
		if (isPresent()) {
			return DriverUtil.getDriver().findElement(DriverUtil.getBy(locator, byType)).getAttribute("href");
		}

		return null;
	}

	public boolean isVisible() {
		return DriverUtil.isVisible(locator, byType);
	}
}
