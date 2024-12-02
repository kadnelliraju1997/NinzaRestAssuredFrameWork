package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.constants.endpoints.IEndPoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class ProjectTest extends BaseAPIClass {
	
	ProjectPojo pojo;

	@Test
	public void createSingleProject() throws Throwable {
		String expSucMsg = "Successfully Added";
		String projectName = "Airtel._." + javaLib.getRandomNumber();
		pojo = new ProjectPojo("raju", projectName, "Created", 0);

		// verify the projectName in API layer
		
		Response resp = given()
				.spec(specReqObj)
				.body(pojo)
				.when()
				. post(IEndPoint.ADDProj);
		         resp.then().statusCode(201)
		         .and().assertThat()
		         .time(Matchers.lessThan(3000L))
		         .assertThat()
				.assertThat().body("msg", Matchers.equalTo(expSucMsg))
				.spec(specRespObj).log().all();
		Assert.assertEquals(resp.jsonPath().get("projectName"), projectName);
		String projectId = resp.andReturn().jsonPath().get("projectId");
		System.out.println(projectId);

		// verify the projectName in DB layer
		boolean flag = dLib.executeSelectQuaryVerifyAndGetData("select * from project", 4, projectName);
		Assert.assertTrue("project is not verified in DB", flag);
	}

	@Test(dependsOnMethods = "createSingleProject")
	public void createDuplicateProject() throws Throwable {

		Response resp = given()
				.spec(specReqObj)
				.when()
				.body(pojo)
				.post(IEndPoint.ADDProj);
		        resp.then().statusCode(409)
		        .spec(specRespObj);
	}

}
