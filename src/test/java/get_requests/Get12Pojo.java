package get_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get12Pojo extends RestfulBaseUrl {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/19
       When
         I send GET Request to the URL
      Then
         Status code is 200
     And
         Response body is like:
                      {
                           "firstname": "Guoqiang",
                            "lastname": "Liu",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Breakfast"
                        }
    */
    @Test
    public void get12Pojo(){
        //Set the Url
        spec.pathParams("first","booking","second",19);

        //Set the Expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01"); //obje oluştururken parametreli cons oluşturduk


        BookingPojo expectedData = new BookingPojo("Guoqiang","Liu",111,true,bookingDatesPojo,"Breakfast");//Daha sonra diger classtan obje oluturduk parametreli olarak
        System.out.println("expectedData = " + expectedData);


        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        // 4. Do Assertion

        //Burada pojo data tipine çevirdik
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        //1. Yol:
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());

        //2. Yol:  ==> Tavsiye edilen
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());




    }





}