package delete_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Delete01 extends JsonplaceholderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {

        //1. Set The URL
        spec.pathParams("first","todos","second",198);

        // 2. Set The Expected Data ( put, post, patch)
        Map<String,String> expectedData = new HashMap<>();
        System.out.println("expectedData = " + expectedData);

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();



        // 4. Do Assertion
        Map actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        //1. Yol:
        assertEquals(expectedData,actualData);

        //2. Yol:
        assertTrue(actualData.isEmpty());

        //3. Yol:
        assertEquals(0,actualData.size());

    }
}
