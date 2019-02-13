package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.Base.Page;
import com.w2a.pages.ZohoProductPage;
import com.w2a.pages.crm.accounts.AccountPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;
import com.w2a.utilities.Utilities;

public class CreateAccountTest {
	
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void createAccountTest(Hashtable<String, String>data) throws InterruptedException {
		ZohoProductPage zpPage = new ZohoProductPage();
		zpPage.gotoCRM();
		
		AccountPage ap = Page.menu.gotoAccounts(); 
		Thread.sleep(2000);
		
		CreateAccountPage cp = ap.goToCreateAccount();
		Thread.sleep(2000);
		
		cp.createAccount(data.get("accountname"));
		
		
	}

}
