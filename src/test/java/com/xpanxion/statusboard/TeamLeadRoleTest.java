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
public class TeamLeadRoleTest {

	@Before
	public void setUp() throws Exception {
		DriverUtil.initialize();
		LogInPage.getInstance().logIn("teamleaduser");
	}
	
	@AfterClass
	public static void cleanup() {
		SiteFactory.shutdown();
	}

	@Test
	@FileParameters("src/test/resources/teamleadmenu.csv")
	public void testTeamLeadTopMenu(String menuName, String menuItem, boolean hasAccess) {
		TopMenu menu = TopMenu.getInstance();
		
		Navigation subMenu = menu.getSubMenu(menuName);
		
		assertEquals(hasAccess, subMenu.isPresent(menuItem));
		if(subMenu.isPresent(menuItem)) {
			IPageObject page = subMenu.click(menuItem);
			
			assertEquals("", page.getPageAlert());
			
			assertEquals("", page.getPageNotice());
		}
	}

}
