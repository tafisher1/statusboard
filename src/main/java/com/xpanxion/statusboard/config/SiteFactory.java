package com.xpanxion.statusboard.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.xpanxion.statusboard.page.HomePage;
import com.xpanxion.statusboard.page.IPageObject;
import com.xpanxion.statusboard.page.LogInPage;

public class SiteFactory {
//	private static final String[] SPRING_FILE_LOCATIONS = {"beans.xml"};
//	private static final ApplicationContext APP_CONTEXT = new ClassPathXmlApplicationContext(SPRING_FILE_LOCATIONS);
	private static final AbstractApplicationContext APP_CONTEXT = new AnnotationConfigApplicationContext(PageConfig.class, MenuConfig.class, SiteConfig.class);
	
	public static LogInPage getLogInPage() {
		return APP_CONTEXT.getBean(LogInPage.class);
	}
	
	public static IPageObject getPage(String pageName) {				
		return APP_CONTEXT.getBean(pageName, IPageObject.class);
	}
	
	public static <T extends IPageObject> T getPage(String pageName, Class<T> c) {				
		return APP_CONTEXT.getBean(pageName, c);
	}
	
	public static HomePage getHomePage() {
		return APP_CONTEXT.getBean(HomePage.class);
	}
	
	public static <T> T getObject(String string, Class<T> c) {
		return (T) APP_CONTEXT.getBean(string, c);
	}
	
	public static void shutdown() {
		APP_CONTEXT.registerShutdownHook();
	}
}
