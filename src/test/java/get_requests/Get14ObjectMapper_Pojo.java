package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get14ObjectMapper_Pojo extends JsonplaceholderBaseUrl {

           /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    //ObjectMapper + Pojo = en iyi yöntem
    @Test
    public void post05ObjectMapperPojo() {


        //1. Set The URL
        spec.pathParams("first","todos","second",198);

        // 2. Set The Expected Data ( put, post, patch)
        JsonPlaceHolderPojo expecteeData = new JsonPlaceHolderPojo(10,"quis eius est sint explicabo",true);
        System.out.println("expecteeData = " + expecteeData);

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion
        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());

        assertEquals(expecteeData.getUserId(),actualData.getUserId());
        assertEquals(expecteeData.getTitle(),actualData.getTitle());
        assertEquals(expecteeData.getCompleted(),actualData.getCompleted());





    }
}
