package get_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Get09 extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
  {
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
 */

    @Test
    public void get09() {

        //Set the Url
        spec.pathParams("first","booking", "second",91);

        //Set The Expected Data

        //Then
        //        Response body should be like that;

        /*
          {
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
         */

        //Öncelikle küçük olan body'i Map üzerinden yaptık
        //Bu tarz durumlarda Map kullanmamız gerekiyor
        Map<String,String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin","2013-02-23");
        bookingdatesMap.put("checkout","2014-10-23");

        //Daha sonra büyük body üzerinden ilerledik
        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname","Sally");
        expectedDataMap.put("lastname","Brown");
        expectedDataMap.put("totalprice",111);
        expectedDataMap.put("depositpaid",true);
        expectedDataMap.put("bookingdates",bookingdatesMap);
        expectedDataMap.put("additionalneeds","Breakfast");
        System.out.println("expectedData = " + expectedDataMap);



        //Send The Request and Get The Response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();


        //Do Assertion
        Map<String,Object> actualDataMap = response.as(HashMap.class);
        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"), ((Map)(actualDataMap.get("bookingdates"))).get("checkin"));//Key-Value ikilileri String-Object şeklinde olduğundan, Bookingdata value kısmını casting ile Map yaptık.
        assertEquals(bookingdatesMap.get("checkout"), ((Map)(actualDataMap.get("bookingdates"))).get("checkout"));
        assertEquals(expectedDataMap.get("additionalneeds"),actualDataMap.get("additionalneeds"));


        //iv-Do assertion
        JsonPath jsonPath=response.jsonPath();

        assertEquals(null,jsonPath.getString("meta"));
        assertEquals(2986,jsonPath.getInt("data.id"));
        assertEquals("Navin Talwar",jsonPath.getString("data.name"));
        assertEquals("navin_talwar@mclaughlin.name",jsonPath.getString("data.email"));
        assertEquals("inactive",jsonPath.getString("data.status"));







    }
}
