package com.w2a.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.w2a.Base.Page;

public class HomePage extends Page {
	
	public LoginPage goToLogin() {
		click("loginlink_CSS");
		return new LoginPage();
	}
	
	public void goToSignUp() {
		driver.findElement(By.cssSelector("a.zh-signup")).click();
	}
	
	public void goToSuppot() {
		driver.findElement(By.cssSelector(".zh-user-account>a:nth-child(2)")).click();
	}
	
	public void goToCustomer() {
		driver.findElement(By.cssSelector("//a[text()='Customers']")).click();
		
	}
	public void validateFooterLinks() {
		
		WebElement element = driver.findElement(By.cssSelector("div.product-links five-column"));
	}

}
