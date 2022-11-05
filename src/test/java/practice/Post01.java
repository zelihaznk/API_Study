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

public class Post01 extends JsonplaceholderBaseUrl {

/*
    Given
      1)  https://jsonplaceholder.typicode.com/todos
      2)  {
           "userId": 30,
           "title": "There is always another way",
           "completed": false
           }
   When
    I send POST Request to the Url
   Then
       Status code is 201
   And
       response body is like {
                               "userId": 30,
                               "title": "There is always another way",
                               "completed": false,
                               "id": 201
}
*/

    @Test
    public void post01() {

        //Random bir id'ye yeni data oluşturma işlemi gerçekleştiriliyor
        //Tek parametre olduğu için param yapıldı

        //1. Set The URL
        spec.pathParam("first","todos");

        // 2. Set The Expected Data ( put, post, patch)
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();

        Map<String,Object> expectedData = obj.expectedDataMethod(30,"There is always another way",false);
        System.out.println("expectedData = " + expectedData);



        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // 4. Do Assertion

        assertEquals(201,response.statusCode());

        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);


        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));

    }

}
