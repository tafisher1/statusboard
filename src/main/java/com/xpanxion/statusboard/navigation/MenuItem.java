/**
 * 
 */
package com.xpanxion.statusboard.navigation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.xpanxion.statusboard.LocatorType;
import com.xpanxion.statusboard.page.IPageObject;

/**
 * @author tfisher
 *
 */
public class MenuItem extends Link implements Navigation {
	/** Map to hold all of the links */
	private Map<String, Link> links = new HashMap<>();

	/**
	 * @param locator
	 * @param byType
	 * @param page
	 */
	public MenuItem(String locator, LocatorType byType, IPageObject page) {
		super(locator, byType, page);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param locator
	 * @param page
	 */
	public MenuItem(String locator, IPageObject page) {
		super(locator, page);

	}

	/**
	 * @param locator
	 * @param byType
	 * @param page
	 */
	public MenuItem(String locator, LocatorType byType, Map<String, Link> map) {
		this(locator, byType, (IPageObject) null);

		this.links = map;
	}

	/**
	 * @param locator
	 * @param page
	 */
	public MenuItem(String locator, Map<String, Link> map) {
		this(locator, (IPageObject) null);

		this.links = map;
	}

	public boolean hasSubMenu() {
		return links != null && links.size() > 0;
	}

	/**
	 * Click the link associated with the given name.
	 * 
	 * This does not click the menu item link before attempting to click the sub
	 * item. It is assumed that that click is occurring in the TopMenu.
	 * 
	 * @param name
	 *            unique identifier of the link (key)
	 * @return the destination page of the link
	 */
	@Override
	public IPageObject click(String name) {
		if (hasSubMenu() && isPresent(name)) {
			// this.click();

			Link link = getLink(name);
			if (link != null) {
				return link.click();
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xpanxion.statusboard.navigation.Navigation#isPresent(java.lang.
	 * String)
	 */
	@Override
	public boolean isPresent(String name) {
		Link link = getLink(name);

		return link != null && link.isPresent() && link.isVisible();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xpanxion.statusboard.navigation.Navigation#addLink(java.lang.String,
	 * com.xpanxion.statusboard.navigation.Link)
	 */
	@Override
	public void addLink(String name, Link link) {
		links.put(name, link);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xpanxion.statusboard.navigation.Navigation#getLink(java.lang.String)
	 */
	@Override
	public Link getLink(String name) {
		return links.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xpanxion.statusboard.navigation.Navigation#getAllLinks()
	 */
	@Override
	public Collection<Link> getAllLinks() {
		return links.values();
	}

}
