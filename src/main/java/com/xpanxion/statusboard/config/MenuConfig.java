package com.xpanxion.statusboard.config;

import static com.xpanxion.statusboard.LocatorType.LINK_TEXT;
import static com.xpanxion.statusboard.LocatorType.PARTIAL_LINK_TEXT;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.xpanxion.statusboard.navigation.Link;
import com.xpanxion.statusboard.navigation.MenuItem;
import com.xpanxion.statusboard.navigation.TopMenu;

/**
 * Configuration of the beans for the Status board menu.
 * 
 * @author tfisher
 *
 */
@Configuration
@PropertySource("menu.properties")
public class MenuConfig {
	private static final String PROPERTY_FORMAT = "%s.%s.linkText";
	private static final String DELIVERY_SUPPORT_MENU = "deliverySupportMenu";
	private static final String ADMIN_MENU = "adminMenu";
	
	/** Reference to the page beans */
	@Autowired
	private PageConfig pages;

	/** Properties from the given property source. */
	@Autowired
	private Environment env;
	
	@Bean
	public TopMenu topMenu() {
		TopMenu menu = new TopMenu();
		
		menu.setupNavigation(topMenuItems());
		
		return menu;
	}

	/**
	 * Bean for the map of the sub menu for delivery support.
	 * 
	 * @return map of item names to links.
	 */
	@Bean
	public Map<String, Link> accountManagerMenuSubItems() {
		Map<String, Link> map = new HashMap<>();

		// add links to the map
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "currentStatusReport")), currentStatusLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "notGreenStatusReport")), notGreenStatusLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "redStatusReport")), redStatusLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "yellowStatusReport")), yellowStatusLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "greenStatusReport")), greenStatusLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "overdueStatusReport")), overdueStatusLink());

		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "allIssuesReport")), allIssuesLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "openIssuesReport")), openIssuesLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "unassignedIssuesReport")), unassignedIssuesLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "inProgressIssuesReport")), inProgressIssuesLink());

		return map;
	}

	/**
	 * Bean for the link in the top menu for delivery support.
	 * 
	 * @return Menu Item with the locator and sub menu that will be opened when
	 *         clicked.
	 */
	@Bean
	public MenuItem accountManagerSubMenu() {
		return new MenuItem(env.getProperty("accountManagerMenu.linkText"), PARTIAL_LINK_TEXT,
				accountManagerMenuSubItems());
	}

	/**
	 * Bean for the link in the admin menu for Account & Project Management.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link accountsLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, ADMIN_MENU, "accounts")), LINK_TEXT,
				pages.accountsPage());
	}

	/**
	 * Bean for the map of the sub menu for Admin.
	 * 
	 * @return map of item names to links.
	 */
	@Bean
	public Map<String, Link> adminMenuSubItems() {
		Map<String, Link> map = new HashMap<>();

		// add links to the map
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, ADMIN_MENU, "accounts")), accountsLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, ADMIN_MENU, "userManagement")), userManagementLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, ADMIN_MENU, "engagementTypes")), engagementTypesLink());

		return map;
	}

	/**
	 * Bean for the link in the top menu for Admin.
	 * 
	 * @return Menu Item with the locator and sub menu that will be opened when
	 *         clicked.
	 */
	@Bean
	public MenuItem adminSubMenu() {
		return new MenuItem(env.getProperty(ADMIN_MENU + ".linkText"), PARTIAL_LINK_TEXT,
				adminMenuSubItems());
	}

	/**
	 * Bean for the link in the delivery support menu for All Issues.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link allIssuesLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "allIssuesReport")), LINK_TEXT,
				pages.allIssuesReportPage());
	}

	/**
	 * Bean for the link in the delivery support menu for Current Statuses.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link currentStatusLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "currentStatusReport")), LINK_TEXT,
				pages.currentStatusesReportPage());
	}

	/**
	 * Bean for the map of the sub menu for delivery support.
	 * 
	 * @return map of item names to links.
	 */
	@Bean
	public Map<String, Link> deliverySupportMenuSubItems() {
		Map<String, Link> map = new HashMap<>();

		// add links to the map
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "currentStatusReport")), currentStatusLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "notGreenStatusReport")), notGreenStatusLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "redStatusReport")), redStatusLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "yellowStatusReport")), yellowStatusLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "greenStatusReport")), greenStatusLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "overdueStatusReport")), overdueStatusLink());

		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "allIssuesReport")), allIssuesLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "openIssuesReport")), openIssuesLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "unassignedIssuesReport")), unassignedIssuesLink());
		map.put(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "inProgressIssuesReport")), inProgressIssuesLink());

		return map;
	}

	/**
	 * Bean for the link in the top menu for delivery support.
	 * 
	 * @return Menu Item with the locator and sub menu that will be opened when
	 *         clicked.
	 */
	@Bean
	public MenuItem deliverySupportSubMenu() {
		return new MenuItem(env.getProperty(DELIVERY_SUPPORT_MENU + ".linkText"), PARTIAL_LINK_TEXT,
				deliverySupportMenuSubItems());
	}

	/**
	 * Bean for the link in the admin menu for Engagement Type Management.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link engagementTypesLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, ADMIN_MENU, "engagementTypes")), LINK_TEXT,
				pages.engagementTypesPage());
	}

	/**
	 * Bean for the link in the delivery support menu for Green Statuses.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link greenStatusLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "greenStatusReport")), LINK_TEXT,
				pages.greenStatusesReportPage());
	}

	/**
	 * Bean for the link in the top menu for Home.
	 * 
	 * @return Menu Item with the locator and page that will be opened when
	 *         clicked.
	 */
	@Bean
	public MenuItem homeLink() {
		return new MenuItem(env.getProperty("home.linkText"), LINK_TEXT, pages.homePage());
	}

	/**
	 * Bean for the link in the delivery support menu for All In Progress
	 * Issues.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link inProgressIssuesLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "inProgressIssuesReport")), LINK_TEXT,
				pages.inProgressIssuesReportPage());
	}

	/**
	 * Bean for the link in the top menu for Log Out.
	 * 
	 * @return Menu Item with the locator and page that will be opened when
	 *         clicked.
	 */
	@Bean
	public MenuItem logOutLink() {
		return new MenuItem(env.getProperty("logOut.linkText"), LINK_TEXT, pages.logInPage());
	}

	/**
	 * Bean for the link in the delivery support menu for Not Green Statuses.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link notGreenStatusLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "notGreenStatusReport")), LINK_TEXT,
				pages.notGreenStatusesReportPage());
	}

	/**
	 * Bean for the link in the delivery support menu for All Open Issues.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link openIssuesLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "openIssuesReport")), LINK_TEXT,
				pages.openIssuesReportPage());
	}

	/**
	 * Bean for the link in the delivery support menu for Overdue Statuses.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link overdueStatusLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "overdueStatusReport")), LINK_TEXT,
				pages.overdueStatusesReportPage());
	}

	/**
	 * Bean for the link in the delivery support menu for Red Statuses.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link redStatusLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "redStatusReport")), LINK_TEXT,
				pages.RedStatusesReportPage());
	}

	/**
	 * Bean for the map of the sub menu for delivery support.
	 * 
	 * @return map of item names to links.
	 */
	@Bean
	public Map<String, MenuItem> topMenuItems() {
		Map<String, MenuItem> map = new HashMap<>();

		// add links to the map
		map.put(env.getProperty("home.linkText"), homeLink());
		map.put(env.getProperty("logOut.linkText"), logOutLink());
		map.put(env.getProperty(DELIVERY_SUPPORT_MENU + ".linkText"), deliverySupportSubMenu());
		map.put(env.getProperty("accountManagerMenu.linkText"), accountManagerSubMenu());
		map.put(env.getProperty(ADMIN_MENU + ".linkText"), adminSubMenu());

		return map;
	}

	/**
	 * Bean for the link in the delivery support menu for All Open Unassigned
	 * Issues.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link unassignedIssuesLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "unassignedIssuesReport")), LINK_TEXT,
				pages.unassignedIssuesReportPage());
	}

	/**
	 * Bean for the link in the admin menu for User Management.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link userManagementLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, ADMIN_MENU, "userManagement")), LINK_TEXT,
				pages.userManagementPage());
	}

	/**
	 * Bean for the link in the delivery support menu for Yellow Statuses.
	 * 
	 * @return link with the locator and page that will be opened when clicked.
	 */
	@Bean
	public Link yellowStatusLink() {
		return new Link(env.getProperty(String.format(PROPERTY_FORMAT, DELIVERY_SUPPORT_MENU, "yellowStatusReport")), LINK_TEXT,
				pages.yellowStatusesReportPage());
	}
}
