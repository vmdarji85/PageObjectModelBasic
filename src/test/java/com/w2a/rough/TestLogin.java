package com.w2a.rough;

import com.w2a.Base.Page;
import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoProductPage;
import com.w2a.pages.crm.accounts.AccountPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;

public class TestLogin  {

	public static void main(String[] args) throws InterruptedException {
		
		//This is a rough Package
				
		HomePage hp = new HomePage();
		LoginPage lp = hp.goToLogin();
	
		ZohoProductPage zpPage = lp.performLogin("vivekdarji85@gmail.com", "Jaimeldima85$");
		
		zpPage.gotoCRM();
		
		AccountPage ap = Page.menu.gotoAccounts();
		Thread.sleep(2000);
		
		CreateAccountPage cp = ap.goToCreateAccount();
		Thread.sleep(2000);
		
		cp.createAccount("Vivek");
		
		

	}

}
