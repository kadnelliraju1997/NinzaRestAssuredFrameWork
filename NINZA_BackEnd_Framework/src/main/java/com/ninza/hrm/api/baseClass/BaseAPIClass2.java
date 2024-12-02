package com.ninza.hrm.api.baseClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.objectrepository.HomePage;
import com.ninza.hrm.objectrepository.LoginPage;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass2 {

	public JavaUtility javaLib = new JavaUtility();
	public FileUtility fLib = new FileUtility();
	public DataBaseUtility dLib = new DataBaseUtility();
	public static RequestSpecification specReqObj;
	public static ResponseSpecification specRespObj;
	public WebDriver driver=null;
	public LoginPage lp;
	public HomePage hp=new HomePage(driver);
	

	@BeforeSuite
	public void configBs() {
		dLib.getDbConnection();
		System.out.println("=========Connect to DB=========");

		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setContentType(ContentType.JSON);
//		reqBuilder.setAuth(oauth2(" ", null));
//		reqBuilder.addHeader("", "");
		reqBuilder.setBaseUri(fLib.getDataFromPropetyFile("baseURL"));
		specReqObj = reqBuilder.build();
		
		ResponseSpecBuilder respBuilder=new ResponseSpecBuilder();
		respBuilder.expectContentType(ContentType.JSON);
		specRespObj = respBuilder.build();
	}
	@BeforeClass
	public void launchBrowaer() {
		String BaseUrl = fLib.getDataFromPropetyFile("baseURL");
		driver = new ChromeDriver();
		driver.get(BaseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		
	}
	@BeforeMethod
	public void loginToApp() {
		String username = fLib.getDataFromPropetyFile("username");
		String password = fLib.getDataFromPropetyFile("password");
		lp=new LoginPage(driver);
		lp.loginToApp(username, password);
		
	}
	@AfterMethod
	public void logoutFromApp() {
		hp=new HomePage(driver);
		hp.logoutFromApp(driver);
		}
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	@AfterSuite
	public void configAs() {
		dLib.closeDbConnection();
	}
}
