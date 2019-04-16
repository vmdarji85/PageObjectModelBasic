package com.w2a.pages;

import org.openqa.selenium.By;

import com.w2a.Base.Page;
import com.w2a.pages.crm.CRMHomePage;

public class ZohoProductPage extends Page {
	
		
	public CRMHomePage gotoCRM() throws InterruptedException {
		//click("closeBtn_CSS");
		Thread.sleep(2000);
		click("crmLink_CSS");
		return new CRMHomePage();
	}
	
	public void gotoCreator() {
		driver.findElement(By.xpath("//div[@id='zl-category-1']//p[text()='Creator']")).click();
	}
	
	public void gotoMail() {
		
	}
	
	

}
