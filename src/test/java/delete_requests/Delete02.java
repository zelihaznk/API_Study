package delete_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDeletePojo;
import utils.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Delete02 extends DummyRestApiBaseUrl {

    /*
     URL: https://dummy.restapiexample.com/api/v1/delete/2
     HTTP Request Method: DELETE Request
     Test Case: Type by using Gherkin Language
     Assert:     i) Status code is 200
                 ii) "status" is "success"
                 iii) "data" is "2"
                 iv) "message" is "Successfully! Record has been deleted"
       */

    /*
    Given
          URL: https://dummy.restapiexample.com/api/v1/delete/2
    When
          HTTP Request Method: DELETE Request
          Test Case: Type by using Gherkin Language
    Then
          Assert:     i) Status code is 200
    And
          ii) "status" is "success"
    And
          iii) "data" is "2"
    And
          iv) "message" is "Successfully! Record has been deleted"Given

     */

    @Test
    public void delete02() {

        //1. Set The URL
        spec.pathParams("first","delete","second",2);

        // 2. Set The Expected Data ( put, post, patch)
          /*
    {
    "status": "success",
    "data": "2",
    "message": "Successfully! Record has been deleted"
}
     */
        DummyRestApiDeletePojo expectedData = new DummyRestApiDeletePojo("success","2","Successfully! Record has been deleted");
        System.out.println("expectedData = " + expectedData);

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion
        assertEquals(200,response.statusCode());
        DummyRestApiDeletePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiDeletePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getData(),actualData.getData());
        assertEquals(expectedData.getMessage(),actualData.getMessage());
        assertEquals(expectedData.getStatus(),actualData.getStatus());


    }
}
