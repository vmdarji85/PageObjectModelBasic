package com.w2a.pages;

import org.openqa.selenium.By;

import com.w2a.Base.Page;

public class LoginPage extends Page{
	
	public ZohoProductPage performLogin(String userName, String password) {
		type("email_CSS", userName);
		type("pass_CSS", password);
		click("signinBtn_CSS");
		return new ZohoProductPage();
	}

}
