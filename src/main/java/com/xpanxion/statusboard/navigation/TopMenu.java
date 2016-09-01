package com.xpanxion.statusboard.navigation;

import java.util.Map;

import com.xpanxion.statusboard.config.SiteFactory;

public class TopMenu {
	public static final String MENU_DELIVERY_SUPPORT = "Delivery Support";
	public static final String MENU_ACCOUNT_MANAGER = "Account Manager";
	public static final String MENU_ADMIN = "Admin";
	
	private static class SingletonHolder {
		private static final TopMenu INSTANCE = SiteFactory.getObject("topMenu", TopMenu.class);
	}

	private Map<String, MenuItem> menuItems;

	public TopMenu() {
		
	}

	public static TopMenu getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public void logOut() {
		click("Log Out");
	}

	public void home() {
		click("Home");
	}
	
	public MenuItem click(String item) {
		MenuItem menuItem = menuItems.get(item);

		if (menuItem != null) {
			menuItem.click();
		}
				
		return menuItem;
	}

	public Navigation getSubMenu(String menu) {
		// this click doesn't change pages, but brings up the sub menu
		MenuItem menuItem = click(menu);

		if (menuItem != null && menuItem.hasSubMenu()) {
			return menuItem;
		}

		return null;
	}

	public Navigation getDeliverySupport() {
		// this click doesn't change pages, but brings up the sub menu
		return getSubMenu(MENU_DELIVERY_SUPPORT);
	}

	public Navigation getAccountManager() {
		// this click doesn't change pages, but brings up the sub menu
		return getSubMenu(MENU_ACCOUNT_MANAGER);
	}

	public Navigation getAdmin() {
		// this click doesn't change pages, but brings up the sub menu
		return getSubMenu(MENU_ADMIN);
	}

	public void setupNavigation(Map<String, MenuItem> topMenuItems) {
		menuItems = topMenuItems;
	}
}
