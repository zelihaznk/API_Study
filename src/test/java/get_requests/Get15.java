package get_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Get15 extends RestfulBaseUrl {

    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
               "firstname": "Guoqiang",
               "lastname": "Morante Briones",
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
    public void get15() {

        //1. Set The URL
        spec.pathParams("first","booking","second",22);

        // 2. Set The Expected Data ( put, post, patch)
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");

        BookingPojo expectedData = new BookingPojo("Guoqiang","Morante Briones",111, true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion
        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);
        System.out.println("actualData = " + actualData);

        //Soft Assertion
        // 1. Adım:
        SoftAssert softAssert = new SoftAssert();

        // 2. Adım: Assertion yap.
        softAssert.assertEquals(response.statusCode(),200,"Status code uyusmadi");
        softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname(),"Firstname uyusmadi");
        softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname(),"Lastname uyusmadi");
        softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice(),"Totalprice uyusmadi");
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid(),"Depositpaid uyusmadi");
        softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds(),"Additionalneeds uyusmadi");

        softAssert.assertEquals(actualData.getBookingdates().getCheckin(),bookingDatesPojo.getCheckin(),"Checkin uyusmadi");
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(),bookingDatesPojo.getCheckout(),"Checkout uyusmadi");

        // 3. Adım: assertAl() kullan.
        softAssert.assertAll();

        //Hard Assertion
        assertEquals(200,response.statusCode());

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());








    }
}

/*Eski yöntem
   BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
 */
