package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiReponceBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Put02 extends DummyRestApiBaseUrl {

    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */

/*
Given
    URL: https://dummy.restapiexample.com/api/v1/update/21
     Request body: {
                    "employee_name": "Ali Can",
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image"
                 }

When
     User sends PUT request

Then
            i) Status code is 200

And
            ii) Response body should be like the following
                {
                    "status": "success",
                    "data": {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                    },
                    "message": "Successfully! Record has been updated."
                }
 */

    @Test
    public void put02() {

        //1. Set The URL
        spec.pathParams("first","update","second",21);

        // 2. Set The Expected Data ( put, post, patch)
        DummyRestApiDataPojo innerData = new DummyRestApiDataPojo("Ali Can",111111,23,"Perfect image");

        DummyRestApiReponceBodyPojo expectedData = new DummyRestApiReponceBodyPojo("success",innerData,"Successfully! Record has been updated.");
        System.out.println("expectedData = " + expectedData);

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(innerData).when().put("/{first}/{second}"); //içerde oln gönderilmelidir
        response.prettyPrint();

        // 4. Do Assertion
        assertEquals(200,response.statusCode());

        DummyRestApiReponceBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiReponceBodyPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(),actualData.getMessage());

        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());






    }
}
