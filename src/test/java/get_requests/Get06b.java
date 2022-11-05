package get_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Get06b extends ReqresBaseUrl {

    /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200
        2)Print all pantone_values
        3)Print all ids greater than 3 on the console
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2

          1)Durum kodu 200
          2)Tüm pantone_değerlerini yazdır
          3)Konsolda 3'ten büyük tüm kimlikleri yazdır
               3'ten büyük 3 kimlik olduğunu iddia et
          4)Kimlikleri 3'ten küçük olan tüm adları konsolda yazdır
               kimlikleri 3'ten küçük olan isimler 2'dir
*/

    @Test
    public void test06() {

        //Set the Url
        spec.pathParam("first","unknown");


        //Set The Expected Data

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion

        // 1)Status code is 200
        assertEquals(200,response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        //2)Print all pantone_values
        System.out.println(jsonPath.getList("data.pantone_value"));//[15-4020, 17-2031, 19-1664, 14-4811, 17-1456, 15-5217]
        System.out.println(jsonPath.getList("data.pantone_value").size());//6 kaç tane olduğunu verdi
        System.out.println(jsonPath.getList("data.pantone_value").get(0));//15-4020  birincisini aldık

        //3)Print all ids greater than 3 on the console


        //Lambda ile
        List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("ids = " + ids);

        /*
        ESKİ YÖNTEM:
        List<Integer> idler = jsonPath.getList("data.id");

        for (int i = 0; i < idler.size(); i++) {
            if (idler.get(i)>3){
                System.out.println(i+ ".id:" +idler.get(i));
            }
        }
         */
        //          Assert that there are 3 ids greater than 3
        assertEquals(3,ids.size());


        // 4)Kimlikleri 3'ten küçük olan tüm adları konsolda yazdır
        List<String> names = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("names = " + names);


        // kimlikleri 3'ten küçük olan isimler 2'dir
        assertEquals(2,names.size());




    }
}
//Set the Url
//Set The Expected Data
//Send The Request and Get The Response
//Do Assertion