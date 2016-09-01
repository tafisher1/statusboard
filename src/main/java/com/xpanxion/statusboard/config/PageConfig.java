package com.xpanxion.statusboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.xpanxion.statusboard.LocatorType;
import com.xpanxion.statusboard.page.HomePage;
import com.xpanxion.statusboard.page.LogInPage;
import com.xpanxion.statusboard.page.PageObject;

@Configuration
@PropertySource("classpath:page.properties")
public class PageConfig {

	@Autowired
	private Environment env;

	@Bean
	public HomePage homePage() {
		return new HomePage(env.getProperty("homePage.title"), env.getProperty("homePage.heading"), env.getProperty("homePage.headingLocator"),
				LocatorType.getLocatorType(env.getProperty("homePage.locatorType")));
	}

	@Bean
	public LogInPage logInPage() {
		return new LogInPage(env.getProperty("logInPage.title"), env.getProperty("logInPage.heading"), env.getProperty("logInPage.headingLocator"),
				LocatorType.getLocatorType(env.getProperty("logInPage.locatorType")));
	}

	@Bean(name="currentStatuses")
	public PageObject currentStatusesReportPage() {
		return new PageObject(env.getProperty("statusReport.title"), env.getProperty("statusReport.current.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="notGreenStatuses")
	public PageObject notGreenStatusesReportPage() {
		return new PageObject(env.getProperty("statusReport.title"), env.getProperty("statusReport.notGreen.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="RedStatuses")
	public PageObject RedStatusesReportPage() {
		return new PageObject(env.getProperty("statusReport.title"), env.getProperty("statusReport.Red.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="yellowStatuses")
	public PageObject yellowStatusesReportPage() {
		return new PageObject(env.getProperty("statusReport.title"), env.getProperty("statusReport.yellow.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="greenStatuses")
	public PageObject greenStatusesReportPage() {
		return new PageObject(env.getProperty("statusReport.title"), env.getProperty("statusReport.green.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="overdueStatuses")
	public PageObject overdueStatusesReportPage() {
		return new PageObject(env.getProperty("statusReport.title"), env.getProperty("statusReport.overdue.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="allIssues")
	public PageObject allIssuesReportPage() {
		return new PageObject(env.getProperty("issueReport.title"), env.getProperty("issueReport.all.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="openIssues")
	public PageObject openIssuesReportPage() {
		return new PageObject(env.getProperty("issueReport.title"), env.getProperty("issueReport.allOpen.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="unassignedIssues")
	public PageObject unassignedIssuesReportPage() {
		return new PageObject(env.getProperty("issueReport.title"), env.getProperty("issueReport.allOpenUnassigned.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="inProgressIssues")
	public PageObject inProgressIssuesReportPage() {
		return new PageObject(env.getProperty("issueReport.title"), env.getProperty("issueReport.inProgress.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="accounts")
	public PageObject accountsPage() {
		return new PageObject(env.getProperty("admin.title"), env.getProperty("admin.accounts.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="userManagement")
	public PageObject userManagementPage() {
		return new PageObject(env.getProperty("admin.title"), env.getProperty("admin.userManagement.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}

	@Bean(name="engagementTypes")
	public PageObject engagementTypesPage() {
		return new PageObject(env.getProperty("admin.title"), env.getProperty("admin.engagementTypes.heading"), env.getProperty("headingLocator"),
				LocatorType.getLocatorType(env.getProperty("headingLocatorType")));
	}
}
