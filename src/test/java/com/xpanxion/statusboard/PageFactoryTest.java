package com.xpanxion.statusboard;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.xpanxion.statusboard.config.SiteFactory;
import com.xpanxion.statusboard.navigation.Link;
import com.xpanxion.statusboard.navigation.MenuItem;
import com.xpanxion.statusboard.page.IPageObject;

public class PageFactoryTest {
	
	@AfterClass
	public static void cleanup() {
		SiteFactory.shutdown();
	}

	@Test
	public void testPage() {
		IPageObject page = SiteFactory.getPage("currentStatuses");
		assertEquals("Statuses that are Current", page.getExpectedHeadingText());
	}

	@Test
	public void testMenu() {
		MenuItem menu = SiteFactory.getObject("deliverySupportSubMenu", MenuItem.class);
		Link link = menu.getLink("Current Statuses");
		assertEquals("Link{locator: Current Statuses locatorType: LINK_TEXT page: "
				+ "Page{title: Statusboard heading: Statuses that are Current "
				+ "locator{ text:page-header type:CLASS_NAME}}}", link.toString());
	}

}
