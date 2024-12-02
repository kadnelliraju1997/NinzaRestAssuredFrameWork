package com.ninza.hrm.api.genericutility;

import java.util.ArrayList;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class JsonUtils {
	
	FileUtility fLib=new FileUtility();
	
	/**
	 * @author RAJU
	 * @param resp
	 * @param jsonXpath
	 * @return
	 */
	public String getDataOnJsonXpath(Response resp, String jsonXpath) {
		ArrayList<Object> list = JsonPath.read(resp.asString(), jsonXpath);
		return list.get(0).toString();

	}

	/**
	 * @author RAJU
	 * @param resp
	 * @param jsonXpath
	 * @param expectedData
	 * @return
	 */
	public boolean verifyDataOnJsonXpath(Response resp, String jsonXpath, String expectedData) {
		ArrayList<Object> list = JsonPath.read(resp.asString(), jsonXpath);
		boolean flag = false;
		for (Object str : list) {
			if (str.equals(expectedData)) {
				System.out.println(expectedData + " is avilable==PASS");
				flag = true;
			}
			if (flag == false) {
				System.out.println(expectedData + " is not avilable==PASS");
			}
		}
		return flag;
	}

	/**
	 * @author RAJU
	 * @param resp
	 * @param jsonPath
	 * @return
	 */
	public String getDataOnJsonPath(Response resp, String jsonPath) {
		return resp.jsonPath().getString(jsonPath);
	}
/**
 *  @author RAJU
 * @return
 */
	public String getAcessToken() {
		Response resp = given().formParam("client_id", fLib.getDataFromPropetyFile("client_id"))
				.formParam("client_secret", fLib.getDataFromPropetyFile("client_secret"))
				.formParam("grant_type", fLib.getDataFromPropetyFile("grant_type")).when()
				.post(fLib.getDataFromPropetyFile("redirectURL"));
		resp.then().log().all();
		//capture token from response
		String token=resp.jsonPath().get("access_token");
		return token;
	}
}
