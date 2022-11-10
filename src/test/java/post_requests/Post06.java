package post_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiReponceBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Post06 extends DummyRestApiBaseUrl {

    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body:
                     {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image",
                        "id": 4891
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */
    /*
    Given
        https://dummy.restapiexample.com/api/v1/create

    And    {
             "employee_name": "Tom Hanks",
             "employee_salary": 111111,
             "employee_age": 23,
             "profile_image": "Perfect image"
          }
     When
        User sends POST request

     Then
        Status code is 200
     And
        Response body should be like the following
{
    "status": "success",
    "data": {
        "employee_name": "Tom Hanks",
        "employee_salary": 111111,
        "employee_age": 23,
        "profile_image": "Perfect image",
        "id": 4891
    },
    "message": "Successfully! Record has been added."
}


     */

    @Test
    public void post06() {

        //Set the Url
        spec.pathParam("first","create");

        //Set the Expected Data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Tom Hanks",111111,23,"Perfect image");

        System.out.println("expectedData = " + expectedData);

        //Send the POST Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();


        // 4. Do Assertion
        DummyRestApiReponceBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiReponceBodyPojo.class);
        System.out.println("actualData = " + actualData);



        //Hard Assert
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(), actualData.getData().getProfile_image());

        /*
        //Soft assert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(),200);
        softAssert.assertEquals(actualData.getData().getEmployee_name(),expectedData.getEmployee_name(),"Employee_name uyusmadi");
        softAssert.assertEquals(actualData.getData().getEmployee_salary(),expectedData.getEmployee_salary(),"Employee_salary uyusmadi");
        softAssert.assertEquals(actualData.getData().getEmployee_age(),expectedData.getEmployee_age(),"Employee_age uyusmadi");
        softAssert.assertEquals(actualData.getData().getProfile_image(),expectedData.getProfile_image(),"Profile_image uyusmadi");
        softAssert.assertAll();
         */







    }
}
