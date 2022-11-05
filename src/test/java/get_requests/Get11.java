package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;
import static org.hamcrest.Matchers.*;

public class Get11 extends GoRestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        Niranjan Gupta, Samir Namboothiri and Gandharva Kaul are among the users
    And
        The female users are less than or equals to male users
 */

    @Test
    public void test11() {

        //Set the Url
        spec.pathParam("first","users");

        //Set The Expected Data

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}");
    //    response.prettyPrint();


        //Do Assertion
        System.out.println(response.statusCode());
        System.out.println(response.statusLine());
        System.out.println(response.contentType());

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("meta.pagination.limit",equalTo(10)
                        ,"meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1")
                        ,"data",hasSize(10)
                        ,"data.status",hasItem("active")
                        ,"data.name",hasItems("Pres. Amarnath Dhawan", "Sujata Chaturvedi" , "Navin Panicker"));



        JsonPath jsonPath = response.jsonPath();
        //Kadın ve erkek sayılarını karşılaştıralım
        //1. Yol : Java ile
        List<String> genders = response.jsonPath().getList("data.gender");
        System.out.println("genders = " + genders);

        int kadinSayisi = 0;
        for (String each:genders) {
            if (each.equalsIgnoreCase("female")){
                kadinSayisi++;
            }
        }
        assertTrue(kadinSayisi<=genders.size()-kadinSayisi);

        //2. Yol: Kadın ve erkek sayılarını
        List<String> femaleNames = response.jsonPath().getList("data.findAll{it.gender=='female'}.name");
        System.out.println("femaleNames = " + femaleNames);

        List<String> maleNames = response.jsonPath().getList("data.findAll{it.gender=='male'}.name");
        System.out.println("maleNames = " + maleNames);

        assertTrue(femaleNames.size()<=maleNames.size());


        //3. Yol:
        assertTrue(jsonPath.getList("data.findAll{it.gender='female'}").size()<=jsonPath.getList("data.findAll{it.gender='male'}").size());


    }

    /*
    verilen
         https://gorest.co.in/public/v1/users
     Ne zaman
         Kullanıcı GET İsteği gönderir
     O zamanlar
         "Sayfalandırma sınırı" değeri 10'dur
     Ve
         "Geçerli bağlantı", "https://gorest.co.in/public/v1/users?page=1" olmalıdır
     Ve
         Kullanıcı sayısı 10 olmalıdır
     Ve
         En az bir "etkin" durumumuz var
     Ve
         "Fr. Paramartha Bandopadhyay", "Pres. Amarnath Dhawan" ve "Sujata Chaturvedi" kullanıcılar arasında
     Ve
         Kadın kullanıcılar erkek kullanıcılara eşit veya daha az
     */






















}
