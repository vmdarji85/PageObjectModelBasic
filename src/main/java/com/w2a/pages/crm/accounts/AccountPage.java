package com.w2a.pages.crm.accounts;

import org.openqa.selenium.By;

import com.w2a.Base.Page;

public class AccountPage extends Page {
	
	public CreateAccountPage goToCreateAccount() {
		
		click("addaccountIcon_CSS");
		return new CreateAccountPage();
	}
	
	public void goToImportAccount() {
		
	}

}
