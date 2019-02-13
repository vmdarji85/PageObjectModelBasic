package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoProductPage;
import com.w2a.utilities.Utilities;

public class LoginTest extends BaseTest{
	
	@Test(dataProviderClass = Utilities.class, dataProvider="dp")
	public void loginTest(Hashtable<String , String> data) {
		
		HomePage hp = new HomePage();
		LoginPage lp = hp.goToLogin();
	
		lp.performLogin(data.get("username"), data.get("password"));
		
	}

}
