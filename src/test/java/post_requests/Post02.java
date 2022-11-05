package post_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Post02 extends RestfulBaseUrl {

     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/

    @Test
    public void post02() {

        //1. Set The URL
        spec.pathParam("first","booking");


        // 2. Set The Expected Data ( put, post, patch)
        RestfulTestData obj = new RestfulTestData();

        Map<String,String> bookingdatesMap = obj.bookingdatesMethod("2021-09-09","2021-09-21");
        System.out.println("bookingdates = " + bookingdatesMap);

        Map<String,Object> expectedDataMap = obj.expectedDataMethod("John","Doe",11111,true,bookingdatesMap);
        System.out.println("expectedData = " + expectedDataMap);


        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();


        // 4. Do Assertion

        assertEquals(200,response.statusCode());

        /*
        {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
         */

        Map<String,Object> actualDataMap = response.as(HashMap.class);

        //Önce booking geldiği için burada Map üzerinden bir obje oluşturmamız gerekiyor ve daha sonra get diyoruz
        assertEquals(expectedDataMap.get("firstname"), ((Map)actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map)actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map)actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map)actualDataMap.get("booking")).get("depositpaid"));
        assertEquals(expectedDataMap.get("bookingdates"), ((Map)actualDataMap.get("booking")).get("bookingdates"));

        //Daha sonra ise yapılması gereken ikinci body için tekrar obje için Map oluşturma işlemi gerçekleştirmeliyiz
        //Burada unutulmaması gereken şey önce yukarıdaki body için Map oluşturulmalı daha sonra ise ikinci body oluşturulmalıdır
        assertEquals(bookingdatesMap.get("checkin"), ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

    }
}
