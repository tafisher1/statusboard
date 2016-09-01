/**
 * 
 */
package com.xpanxion.statusboard.navigation;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xpanxion.statusboard.LocatorType;
import com.xpanxion.statusboard.page.IPageObject;
import com.xpanxion.statusboard.page.PageObject;

/**
 * @author tfisher
 *
 */
public class FileBackedNavigation implements Navigation {
	/** Map to hold all of the links */
	private Map<String, Link> links = new HashMap<>();

	public FileBackedNavigation(List<String> list) {
		for (String line : list) {
			addLink(line);
		}
		// list.forEach(this::addLink);
	}//body > h2

	private void addLink(String line) {
		String[] pieces = line.split(",");

		if (pieces.length >= 4) {
			String title = pieces[0];
			String locator = pieces[1];
			// TODO how should roles be handled?
			// String[] roles = pieces[3].split(" ");
			IPageObject page = null;
			if (pieces.length > 4) {
				String headingText = pieces[4];
				page = new PageObject("Statusboard", headingText, "page-header", LocatorType.CLASS_NAME);
			}

			Link link = new Link(title, LocatorType.getLocatorType(locator), page);

			addLink(title, link);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xpx.framework.sdet.examination.navigation.Navigation#click(java.lang.
	 * String)
	 */
	@Override
	public IPageObject click(String name) {
		Link link = getLink(name);

		return link.click();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xpx.framework.sdet.examination.navigation.Navigation#isPresent(java.
	 * lang.String)
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
	 * com.xpx.framework.sdet.examination.navigation.Navigation#addLink(java.
	 * lang.String, com.xpx.framework.sdet.examination.navigation.Link)
	 */
	@Override
	public void addLink(String name, Link link) {
		links.put(name, link);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xpx.framework.sdet.examination.navigation.Navigation#getLink(java.
	 * lang.String)
	 */
	@Override
	public Link getLink(String name) {
		return links.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xpx.framework.sdet.examination.navigation.Navigation#getAllLinks()
	 */
	@Override
	public Collection<Link> getAllLinks() {
		return links.values();
	}

}
