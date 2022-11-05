package practice;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Put01 extends JsonplaceholderBaseUrl {

        /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/142
     2) {
            "userId": 29,
            "title": "If you belive everything happens",
            "completed": false
          }
    When
I send PUT Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 29,
                   "title": "If you belive everything happens",
                   "completed": false
                   "id": 142
                  }
 */

    @Test
    public void put01() {

        //1. Set The URL
        spec.pathParams("first","todos","second",142);

        // 2. Set The Expected Data ( put, post, patch)
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();

        Map<String,Object> expectedData = obj.expectedDataMethod(29,"If you belive everything happens",false);
        System.out.println("expectedData = " + expectedData);


        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion

        assertEquals(200,response.statusCode());

        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));


    }








}
