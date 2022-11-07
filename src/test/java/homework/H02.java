package homework;

import base_urls.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class H02 extends AutomationExerciseBaseUrl {

    //1:
 /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/

    @Test
    public void h01() {

        //1. Set The URL
        spec.pathParam("first","brandsList");

        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).when().get("/{first}");
    //    response.prettyPrint();

        // Kodlar bize HTML şeklinde geliyor ilk yazdırma işlemini yaptığımızda bu sebeple onları JsonPath ile Json formatına çevirmeliyiz
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();



        // 4. Do Assertion
        assertEquals(200,response.statusCode());
        assertEquals("text/html; charset=utf-8",response.contentType());
        assertEquals("HTTP/1.1 200 OK",response.statusLine());

        Map<String,Object> HM = new HashMap<>();





    }
}

/*
         verilen
             https://automationexercise.com/api/brandsList
         Ne zaman
             Kullanıcı url'ye bir GET İsteği gönderir
         O zamanlar
             HTTP Durum Kodu 200 olmalıdır
         Ve
             İçerik Türü "text/html; charset=utf-8" olmalıdır
         Ve
             Durum Satırı HTTP/1.1 olmalıdır 200 TAMAM
         Ve
              H&M marka sayısı Polo marka sayılarına eşit olmalıdır(H&M marka sayıları olmalıdır)
*/
