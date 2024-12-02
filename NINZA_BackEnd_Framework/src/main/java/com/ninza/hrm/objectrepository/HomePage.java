package com.ninza.hrm.objectrepository;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	@FindBy(xpath = "//div[@title='Logout']//*[name()='svg']")
	private WebElement logoutBtn;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}
	
	public void logoutFromApp(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",getLogoutBtn());
//		getLogoutBtn().click();
	}
	
	
}
