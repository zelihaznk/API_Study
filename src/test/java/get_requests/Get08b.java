package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get08b extends JsonplaceholderBaseUrl {

    //Dinamik yöntem
    @Test
    public void get08b(){

//Set the Url
        spec.pathParams("first","todos","second",2);

//Set The Expected Data ==> Payload
        JsonPlaceHolderTestData objJsonPlcHldr = new JsonPlaceHolderTestData();

        Map<String,Object> expectedData = objJsonPlcHldr.expectedDataMethod(1,"quis ut nam facilis et officia qui",false);
        System.out.println(expectedData);


//Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

//Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals("1.1 vegur", response.header("Via"));
        assertEquals("cloudflare", response.header("Server"));
        assertEquals(200, response.statusCode());

    }
}
