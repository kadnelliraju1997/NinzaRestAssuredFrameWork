package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.given;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;

public class CreateProjectThroughFrontEnd extends BaseAPIClass {
	JavaUtility jLib=new JavaUtility();
	FileUtility fLib=new FileUtility();
	DataBaseUtility dLib=new DataBaseUtility();
	
	@Test
	public void createProjectFE() {
		
		String BaseUrl = fLib.getDataFromPropetyFile("baseURL");
		String userName = fLib.getDataFromPropetyFile("username");
		String passWord = fLib.getDataFromPropetyFile("password");
		
		String projectName = "Ninzazza"+jLib.getRandomNumber();
		WebDriver driver=new ChromeDriver();
		driver.get(BaseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys(passWord);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Projects']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Create Project']")).click();
		WebElement projectNameTf = driver.findElement(By.xpath("//input[@name='projectName']"));
		 projectNameTf.sendKeys(projectName);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("Ninzazza");
		WebElement status = driver.findElement(By.xpath("//label[text()='Project Status* ']/ancestor::div/select[@name='status']"));
		Select select=new Select(status);
		select.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		Select select1 =new Select(driver.findElement(By.xpath("//div[@class='col-sm-6']//select[@class='form-control']")));
		select1.selectByIndex(1);
		driver.findElement(By.xpath("//input[@placeholder='Search by Project Name']")).sendKeys(projectName);
		String projectId = driver.findElement(By.xpath("//td[normalize-space()='"+projectName+"']/preceding-sibling::td")).getText().trim();
		driver.quit();
		//read data using Api
		given().spec(specReqObj)
		.get("/project/"+projectId)
		.then()
			.statusCode(200)
			.spec(specRespObj)
			.log().all();
		
		//Db validation
		dLib.getDbConnection();
		boolean flag = dLib.executeSelectQuaryVerifyAndGetData("select * from project", 4, projectName);
		Assert.assertTrue(flag,"project is not  verified in DB");
		
	}

}
