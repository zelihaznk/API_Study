package post_requests;

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
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
           }
   When
    I send POST Request to the Url
   Then
       Status code is 201
   And
       response body is like {
                               "userId": 55,
                               "title": "Tidy your room",
                               "completed": false,
                               "id": 201
                               }
*/

    @Test
    public void post01() {

        //Set the Url
        spec.pathParam("first","todos");


        //Set The Expected Data
        /*
        2)  {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
           }
         */


        /*Datayı oluşturmak için daha önce oluştuduğumuz JsonPlaceHolderTestData'daki  methodunu kullanmamız gerekiyor*/
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();

        /*Daha sonra oradaki objeyi Map'a atıyoruz*/
        Map<String,Object> expectedData = obj.expectedDataMethod(55,"Tidy your room",false);


        //Send The Request and Get The Response
        /*
        When
             I send POST Request to the Url
         */
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        /*
        Then
             Status code is 201
         */
        assertEquals(201,response.statusCode());


        //And
        //       response body is like {
        //                               "userId": 55,
        //                               "title": "Tidy your room",
        //                               "completed": false,
        //                               "id": 201
        //                               }

        /*assert işlemini yaparken yukarıda Map kullandığımız için aşağıda da Map oluşturmak zorundayız*/
        Map<String,Object> actualData = response.as(HashMap.class);

        /*Burada assert işlemini yapmanın tek yolu ayrı ayrı almak yoksa hata veriyor*/
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));





    }
}
