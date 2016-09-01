package com.xpanxion.statusboard.navigation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.xpanxion.statusboard.LocatorType;
import com.xpanxion.statusboard.page.HomePage;
import com.xpanxion.statusboard.page.LogInPage;

@Deprecated
public class FileBackedTopMenu {
	private static final String MENU_TOP = "Top";
	public static final String MENU_DELIVERY_SUPPORT = "Delivery Support";
	public static final String MENU_ACCOUNT_MANAGER = "Account Manager";
	public static final String MENU_ADMIN = "Admin";
	private static final FileBackedTopMenu INSTANCE = new FileBackedTopMenu();

	private Link home = new Link("Home", LocatorType.LINK_TEXT, HomePage.getInstance());
	// <a href="/users/sign_out">Log Out</a>
	private Link logOut = new Link("Log Out", LocatorType.LINK_TEXT, LogInPage.getInstance());
	private Navigation top;
	private Map<String, Navigation> subMenus;

	public FileBackedTopMenu() {
		subMenus = new HashMap<>();

		Path filepath = Paths.get("src/main/resources", "TopMenu.csv");

		try (Stream<String> stream = Files.lines(filepath)) {
			Map<String, List<String>> map = stream.skip(1).collect(Collectors.groupingBy(line -> line.split(",")[2]));
			top = new FileBackedNavigation(map.get(MENU_TOP));

			for (Entry<String, List<String>> entry : map.entrySet()) {
				if (entry.getKey() != MENU_TOP) {
					subMenus.put(entry.getKey(), new FileBackedNavigation(entry.getValue()));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FileBackedTopMenu getInstance() {
		return INSTANCE;
	}

	public void logOut() {
		logOut.click();
	}

	public void home() {
		home.click();
	}

	public Navigation getSubMenu(String menu) {
		// this click doesn't change pages, but brings up the sub menu
		Link delSup = top.getLink(menu);

		if (delSup != null && delSup.isPresent()) {
			delSup.click();
		}

		return subMenus.get(menu);
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
}
