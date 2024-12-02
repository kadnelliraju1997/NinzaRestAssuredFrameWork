package com.ninza.hrm.api.baseClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {

	public JavaUtility javaLib = new JavaUtility();
	public FileUtility fLib = new FileUtility();
	public DataBaseUtility dLib = new DataBaseUtility();
	public static RequestSpecification specReqObj;
	public static ResponseSpecification specRespObj;

	@BeforeSuite
	public void configBs() {
		dLib.getDbConnection();
		System.out.println("=========Connect to DB=========");

		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setContentType(ContentType.JSON);

//		reqBuilder.setAuth(oauth2(" ", null));
//		reqBuilder.addHeader("", "");
//		reqBuilder.addCookie(null)
//		reqBuilder.addMultiPart(new File(""))
//		reqBuilder.addParam(null, null)
//		reqBuilder.addPathParams(null)
//		reqBuilder.addQueryParam(null, null)
//		reqBuilder.addFormParam(null, null)
//		reqBuilder.removeFormParam(null)
//		reqBuilder.removeParam(null)
//		reqBuilder.removePathParam(null)
//		reqBuilder.removeQueryParam(null)
//		reqBuilder.noContentType()
//		reqBuilder.setAccept(null)
		reqBuilder.setBaseUri(fLib.getDataFromPropetyFile("baseURL"));
		specReqObj = reqBuilder.build();
		ResponseSpecBuilder respBuilder = new ResponseSpecBuilder();
		respBuilder.expectContentType(ContentType.JSON);
		specRespObj = respBuilder.build();
	}

	@AfterSuite
	public void configAs() {
		dLib.closeDbConnection();
	}
}
