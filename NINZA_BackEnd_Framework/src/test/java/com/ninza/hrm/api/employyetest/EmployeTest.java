package com.ninza.hrm.api.employyetest;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.EmployeePojo;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.constants.endpoints.IEndPoint;

import io.restassured.response.Response;
import junit.framework.Assert;

public class EmployeTest extends BaseAPIClass {
	

	@Test
	public void addEmployeeTest() throws SQLException {
		int randomNum = javaLib.getRandomNumber();

		// API_1 ==> add a project
		ProjectPojo pojo = new ProjectPojo("raju", "Airtel__" + randomNum, "Created", 0);
		Response resp = given()
				.spec(specReqObj)
				.body(pojo)
				.when()
				.post(IEndPoint.ADDProj);
		        resp.then().statusCode(201)
		        .and().assertThat().body("msg", Matchers.equalTo("Successfully Added"))
		        .spec(specRespObj).log().all();
		String projectName = resp.jsonPath().get("projectName");
		
		// API_2 Add employee to same Project
		EmployeePojo empObj = new EmployeePojo("Architect", "15/07/1998", "raju@gmail.com",
				"Uesr_" + randomNum, 18, "9886900694", projectName, "ROLE_EMPLOYEE", "Uesr_" + randomNum);
		
		Response resp1 = given()
				.spec(specReqObj)
				.body(empObj)
				.when()
				.post(IEndPoint.ADDEmp);
		        resp1.then().assertThat().time(Matchers.lessThan(3000L))
		        .assertThat().statusCode(201)
		        .spec(specRespObj).log().all();

		// verify the projectName in DB layer
		dLib.getDbConnection();
		boolean flag = dLib.executeSelectQuaryVerifyAndGetData("select * from employee", 5, "Uesr_" + randomNum);
		Assert.assertTrue("project is not verified in DB", flag);
		dLib.closeDbConnection();

	}

	@Test
	public void addEmployeWithOutEmailTest() {
		int randomNum = javaLib.getRandomNumber();
		// API_1 ==> add a project

		ProjectPojo pojo = new ProjectPojo("raju", "Airtel__" + randomNum, "Created", 0);
		
		Response resp = given()
				.spec(specReqObj)
				.body(pojo)
				.when()
				.post(IEndPoint.ADDProj);
		         resp.then()
		         .statusCode(201)
		         .and().assertThat()
		         .body("msg", Matchers.equalTo("Successfully Added"))
		         .spec(specRespObj).log().all();

		// capture project name from the response
		String projectName = resp.jsonPath().get("projectName");
		
		// API_2 Add employee to same Project
		
		EmployeePojo empObj = new EmployeePojo("Architect", "15/07/1998", "", "Uesr_" + randomNum, 18, "9886900694",
				projectName, "ROLE_EMPLOYEE", "Uesr_" + randomNum);
		
		Response resp1 = given()
				.spec(specReqObj)
				.body(empObj)
				.when()
				.post(IEndPoint.ADDEmp);
		         resp1.then().assertThat().time(Matchers.lessThan(3000L))
		         .assertThat()
				.statusCode(500)
				.spec(specRespObj).log().all();

	}

}
