package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class Get10 extends GoRestBaseUrl {

     /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
{
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
    }
}

*/

    @Test
    public void get10() {

        //Set the Url
        spec.pathParams("first","users","second",2986);

        //Set The Expected Data

        /*
        {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
    }
}
         */
        GoRestTestData objGoRestTestData = new GoRestTestData();
        Map<String,String> dataKeyMap = objGoRestTestData.dataKeyMapMethod("Kanaka Jain", "kanaka_jain@stark.net", "male","active");
     //   Map<String,String> dataKeyMap = objGoRestTestData.dataKeyMapMethod("Navin Talwar","navin_talwar@mclaughlin.name","male","inactive");
        Map<String,Object> expectedData = objGoRestTestData.expectedDataMethod(null,dataKeyMap);
        System.out.println("expectedData = " + expectedData);
        



        //Send The Request and Get The Response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();


        //Do Assertion
        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        assertEquals(expectedData.get("meta"),actualDataMap.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map)actualDataMap.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"), ((Map)actualDataMap.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map)actualDataMap.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map)actualDataMap.get("data")).get("status"));
        assertEquals(200,response.statusCode());



    }


    @Test
    public void get10b() {

        //Set the Url
        spec.pathParams("first", "users", "second", "2986");

        //
        Response response = given().spec(spec).when().get("/{first}/{second}");

        JsonPath json = response.jsonPath();
        json.prettyPrint();

        assertNull(json.getString("meta"));
        assertEquals("Kanaka Jain", json.getString("data.name"));
        assertEquals("kanaka_jain@stark.net", json.getString("data.email"));
        assertEquals("male", json.getString("data.gender"));
        assertEquals("active", json.getString("data.status"));
        assertEquals(200, response.statusCode());
    }


}
/*
Bu kısmı ben yaptım ama obje oluşturarak yaptığımız için yoruma aldım.
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("id",2986);
        dataMap.put("name","Navin Talwar");
        dataMap.put("email","navin_talwar@mclaughlin.name");
        dataMap.put("gender","male");
        dataMap.put("status","inactive");

        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("meta",null);
        expectedDataMap.put("data",dataMap);
        System.out.println("expectedDataMap = " + expectedDataMap);

 */
