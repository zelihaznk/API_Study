package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get02 {



    @Test
    public void get01() {

        /*
    * Given
        https://restful-booker.herokuapp.com/booking/1
    When
        User send a GET Request to the url
    Then
        HTTP Status code should be 404
    And
        Status Line should be HTTP/1.1 404 Not Found
    And
        Response body contains "Not Found"
    And
        Response body does not contain "TechProEd"
    And
        Server is "Cowboy"
 */

        //i)  Set the URL,
        /*
         * Given
        https://restful-booker.herokuapp.com/booking/1
         */
        String url = "https://restful-booker.herokuapp.com/booking/1";

        //ii) Set the expected Data (beklenen datanin olusturulmasi, post, put, patch)


        //iii) Type code to send request (Talep gondermek icin kod yazimi)
        /*
        When
        User send a GET Request to the url
         */
        Response response = given().when().get(url);
        response.prettyPrint();

        //iv) Do Assertion ()dogrulama yapmak
        /*
         Then
        HTTP Status code should be 404

         And
        Status Line should be HTTP/1.1 404 Not Found
         */
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

       /*
        And
        Response body contains "Not Found"
        */
        assertTrue(response.asString().contains("Not Found"));

        /*
         And
        Response body does not contain "TechProEd"
         */
        assertFalse(response.asString().contains("TechProEd"));

        /*
        And
        Server is "Cowboy"
         */
        assertEquals(response.getHeader("Server"),"Cowboy");







    }
}
