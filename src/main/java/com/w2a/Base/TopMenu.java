package com.w2a.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.w2a.pages.crm.accounts.AccountPage;

public class TopMenu {
	
	public WebDriver driver;
	
	public TopMenu(WebDriver driver) {
		this.driver = driver;
	}

	public void gotoHome() {

	}

	public void gotoFeeds() {

	}

	public void gotoContacts() {

	}

	public void gotoLeads() {

	}

	public AccountPage gotoAccounts() {
		
		Page.click("accountTab_CSS");
		return new AccountPage();

	}
	
	public void signOut() {
		
	}
	 

}
