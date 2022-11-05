package post_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Post05ObjectMapper_Map extends JsonplaceholderBaseUrl {


    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }
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
    public void post05ObjectMapper() throws IOException {

        //1. Set The URL
        spec.pathParam("first","todos");

    //   // 2. Set The Expected Data ( put, post, patch)

        /*Burası eski ve clean olmayan kısım biz burayı JsonPlaceHolderTestData içinde expectedDataInString ile aldık
          String jsonString = "{\n" +
                  "                 \"userId\": 55,\n" +
                  "                 \"title\": \"Tidy your room\",\n" +
                  "                 \"completed\": false\n" +
                  "               }";

         */

        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        String jsonInString =  obj.expectedDataInString(55,"Tidy your room",false);

        HashMap expectedData = new ObjectMapper().readValue(jsonInString, HashMap.class);
        System.out.println("expectedData = " + expectedData);


        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // 4. Do Assertion

        HashMap actualData = new ObjectMapper().readValue(response.asString(),HashMap.class);
        System.out.println("actualData = " + actualData);


        assertEquals(201,response.statusCode());

        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));


    }
}
