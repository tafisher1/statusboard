package com.xpanxion.statusboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.xpanxion.statusboard.config.SiteFactory;
import com.xpanxion.statusboard.navigation.TopMenu;
import com.xpanxion.statusboard.page.LogInPage;

public class LogInTest {//extends BaseTest {	
	@Before
	public void setUp() {
		DriverUtil.initialize();
	}
	
	@AfterClass
	public static void cleanup() {
		SiteFactory.shutdown();
	}
	
	@Test
	public void testLogInAdmin() {
		LogInPage.getInstance().logInAdmin();
		assertTrue("Log in failed",LogInPage.getInstance().loggedIn());
	}

	@Test
	public void testLogInDeliverySupport() {
		LogInPage.getInstance().logIn("deliverysupportuser");
		assertTrue("Log in failed",LogInPage.getInstance().loggedIn());
	}

	@Test
	public void testLogInAccountManager() {
		LogInPage.getInstance().logIn("accountmanageruser");
		assertTrue("Log in failed",LogInPage.getInstance().loggedIn());
	}

	@Test
	public void testLogInTeamLead() {
		LogInPage.getInstance().logIn("teamleaduser");
		assertTrue("Log in failed",LogInPage.getInstance().loggedIn());
	}

	@Test
	public void testLogInTeamMate() {
		LogInPage.getInstance().logIn("teammateuser");
		assertTrue("Log in failed",LogInPage.getInstance().loggedIn());
	}

	@Test
	public void testLogInFailInvalidUser() {
		LogInPage.getInstance().logIn("baduser@xpanxion.com", "nouser");
		assertFalse("Invalid user was allowed to log in", LogInPage.getInstance().loggedIn());
		//<p class="alert">Invalid email or password.</p>
		assertEquals("Invalid email or password.", LogInPage.getInstance().getPageAlert());
	}

	@Test
	public void testLogInFailInvalidPassword() {
		LogInPage.getInstance().logIn("adminguy@xpanxion.com", "badpassword");
		assertFalse("Invalid password was allowed to log in", LogInPage.getInstance().loggedIn());
		assertEquals("Invalid email or password.", LogInPage.getInstance().getPageAlert());
	}

	@Test
	public void testLogOut() {
		LogInPage.getInstance().logInAdmin();
		TopMenu.getInstance().logOut();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//<p class="alert">You need to sign in or sign up before continuing.</p>
		assertEquals("You need to sign in or sign up before continuing.", LogInPage.getInstance().getPageAlert());
	}

}
