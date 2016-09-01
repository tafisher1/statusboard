package com.xpanxion.statusboard.navigation;

import java.util.Collection;

import com.xpanxion.statusboard.page.IPageObject;

/**
 * Interface to define navigation components of a page.
 * 
 * @author tfisher
 *
 */
public interface Navigation {
	/**
	 * Add a link with the given name.
	 * 
	 * @param name
	 *            unique identifier of the link (key)
	 * @param link
	 *            link to add
	 */
	public void addLink(String name, Link link);

	/**
	 * Click the link associated with the given name.
	 * 
	 * @param name
	 *            unique identifier of the link (key)
	 * @return the destination page of the link
	 */
	public IPageObject click(String name);

	/**
	 * Returns all of the links stored in the object
	 * 
	 * @return the links without their keys.
	 */
	public Collection<Link> getAllLinks();

	/**
	 * Returns the link mapped to the given name
	 * 
	 * @param name
	 *            unique identifier of the link (key)
	 * @return the link
	 */
	public Link getLink(String name);

	/**
	 * Returns whether the link is on the page
	 * 
	 * @param name
	 *            unique identifier of the link (key)
	 * @return true when the link is on the page.
	 */
	public boolean isPresent(String name);
}
