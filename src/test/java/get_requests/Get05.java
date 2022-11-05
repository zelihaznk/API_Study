package get_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;


public class Get05 extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
    And
     Among the data there should be someone whose firstname is "Johhny" and lastname is "Dear"
    */

    @Test
    public void get01() {

        // 1)  Set the URL,
        spec.pathParam("first","booking").queryParams("firstname","Ali","lastname","Cengiz");
        //önce parametrenin yolunu ekledik daha sonra sorgulama parametresi oluşturup sorgulamamızı yaptık

        //https://restful-booker.herokuapp.com/booking?firstname=Ali&lastname=Cengiz
        // key=value seklinde calisiyor

        // 2) Set the expected Data (beklenen datanin olusturulmasi, post, put, patch)

        // 3) Send the request and Get Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4) Do Assertion ()dogrulama yapmak
        assertEquals(200,response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));

    /*
    URI : URI, internette bir kaynak üzerine işaret edilmiş resim veya belge gibi klasik formata uygun bir karakter dizisidir.
    URL : URL, bir kaynağın örnek konumlayıcı veya tek halde kaynak bulucu olarak tanımlanabilir.
     */

    }
}
