package com.xpanxion.statusboard;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.xpanxion.statusboard.config.SiteFactory;
import com.xpanxion.statusboard.navigation.Navigation;
import com.xpanxion.statusboard.navigation.TopMenu;
import com.xpanxion.statusboard.page.IPageObject;
import com.xpanxion.statusboard.page.LogInPage;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class AdminRoleTest {

	@Before
	public void setUp() throws Exception {
		DriverUtil.initialize();
		LogInPage.getInstance().logInAdmin();
	}
	
	@AfterClass
	public static void cleanup() {
		SiteFactory.shutdown();
	}

	@Test
	@FileParameters("src/test/resources/adminmenu.csv")
	public void testAdminTopMenu(String menuName, String menuItem, boolean hasAccess) {
		TopMenu menu = TopMenu.getInstance();
		
		Navigation subMenu = menu.getSubMenu(menuName);
		
		assertEquals(hasAccess, subMenu.isPresent(menuItem));
		if(hasAccess) {
			IPageObject page = subMenu.click(menuItem);
			
			assertEquals("", page.getPageAlert());
			
			assertEquals("", page.getPageNotice());
		}
	}

}
