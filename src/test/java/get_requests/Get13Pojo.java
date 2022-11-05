package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get13Pojo extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */

    @Test
    public void getPojo13() {

        //1. Set The URL
        spec.pathParams("first","users","second",2508);

        // 2. Set The Expected Data ( put, post, patch)
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(2508,"Sharmila Deshpande VM","deshpande_sharmila_vm@becker.name","female","active");

        GoRestPojo expectedData = new GoRestPojo(null,goRestDataPojo);
        System.out.println("expectedData = " + expectedData);


        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        // 4. Do Assertion

        assertEquals(200,response.statusCode());

        //Burada pojo data tipine çevirmeliyim
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getMeta(),actualData.getMeta());

        assertEquals(goRestDataPojo.getId(),actualData.getData().getId());
        assertEquals(goRestDataPojo.getName(),actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actualData.getData().getStatus());



    }
}
