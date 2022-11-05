package homework;

import base_urls.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class H01 extends AutomationExerciseBaseUrl {

    /*
Given
    https://automationexercise.com/api/productsList
When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be "text/html; charset=utf-8"
And
    Status Line should be HTTP/1.1 200 OK
And
     There must be 12 Women, 9 Men, 13 Kids usertype in products
  */

    /*
verilen
    https://automationexercise.com/api/productsList
Ne zaman
    Kullanıcı url'ye bir GET İsteği gönderir
O zamanlar
    HTTP Durum Kodu 200 olmalıdır
Ve
    İçerik Türü "text/html; charset=utf-8" olmalıdır
Ve
    Durum Satırı HTTP/1.1 olmalıdır 200 TAMAM
Ve
     Ürünlerde 12 Kadın, 9 Erkek, 13 Çocuk kullanıcı tipi bulunmalıdır.
  */


    @Test
    public void homework() {

        //Set the Url
        //Set The Expected Data
        //Send The Request and Get The Response
        //Do Assertion

        //Set the Url
        spec.pathParam("first", "productsList");

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        // Kodlar bize HTML şeklinde geliyor ilk yazdırma işlemini yaptığımızda bu sebeple onları JsonPath ile Json formatına çevirmeliyiz
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();


        //Do Assertion


        //Then
        //    HTTP Status Code should be 200
        assertEquals(200, response.statusCode());
        //And
        //    Content Type should be "text/html; charset=utf-8"
        assertEquals("text/html; charset=utf-8", response.getContentType());
        //And
        //    Status Line should be HTTP/1.1 200 OK
        assertEquals("HTTP/1.1 200 OK", response.statusLine());



        //And
        //     There must be 12 Women, 9 Men, 13 Kids usertype in products


        // Önce List'e  json formatında yazdırdıklarımızdan bizden istenenleri body içinde olanları ifade ediyoruz
        // body içerisinde bizden istenen usertype'e kadar ilerledik
        // JSON bodysinin usertype içeriği Liste eklendi
        List<String> categories = jsonPath.getList("products.category.usertype.usertype");

        /*
        findall()
         Bu fonksiyon ile bir kaynak içerisinde istediğimiz metnin kaç kere geçtiğini inceleyebiliriz.
         */

        //Önce List'e  json formatında yazdırdıklarımızdan bizden istenenleri body içinde olanları ifade ediyoruz
        //burada body içerisinde findall() kaynak içerisinde istediğimiz metnin kaç kere geçtiğini inceledik usertype'e kadar ilerledik
        List<String> catagory = jsonPath.getList("products.category.usertype.findAll{it.usertype}.usertype");
        System.out.println("catagories = " + categories);
        System.out.println("catagory = " + catagory);


        //There must be 12 Women, 9 Men, 13 Kids usertype in products
        //Ürünlerde 12 Kadın, 9 Erkek, 13 Çocuk kullanıcı tipi bulunmalıdır.


        // 1. Yol: Bir int değere bizden istenen Women, Men, Kids'i yerleştirip 0'dan başlamasını ifade ettik
        // Daha sonra forech içinde içinde switch ile each'ı case'e aldık women, man, kids olanların tamamı olana kadar aldık
        int womenCount = 0;
        int menCount = 0;
        int kidsCount = 0;
        for (String each : categories){
            switch (each){
                case "Women":
                    womenCount++;
                    break;
                case "Men":
                    menCount++;
                    break;
                case "Kids":
                    kidsCount++;
                    break;
            }
        }

        //Ve burada yazdırdık
        System.out.println("womenCount = " + womenCount);
        System.out.println("menCount = " + menCount);
        System.out.println("kidsCount = " + kidsCount);

        //Burada ise doğruladık
        assertEquals(12,womenCount);
        assertEquals(9,menCount);
        assertEquals(13,kidsCount);

        //There must be 12 Women, 9 Men, 13 Kids usertype in products
        //Ürünlerde 12 Kadın, 9 Erkek, 13 Çocuk kullanıcı tipi bulunmalıdır.

        // 2. Yol:

        //Önce List'e  json formatında yazdırdıklarımızdan bizden istenenleri body içinde olanları ifade ediyoruz
        //burada body içerisinde findall() kaynak içerisinde istediğimiz metnin kaç kere geçtiğini inceledik
        //Burada usertype=='Women',, usertype=='Men',, usertype=='Kids' ''a eşit olanları List'e atadık ve yazdırdık
        List<String> women = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        System.out.println("women = " + women);

        List<String> men = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        System.out.println("men = " + men);

        List<String> kids = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
        System.out.println("kids = " + kids);

        //Yukarıda List'e atadıklarımızın size()=o listenin eleman sayısının eşitliğini sorguladık ve doğruladık
        assertEquals(12,women.size());
        assertEquals(9,men.size());
        assertEquals(13,kids.size());


        // 3. Yol: Burada ise Lambda ile catagories' stream() ile akışa aldık  ve sorgulamayı öyle yaptık
        assertEquals(12,categories.stream().filter(t->t.equals("Women")).count());
        assertEquals(9,categories.stream().filter(t->t.equals("Men")).count());
        assertEquals(13,categories.stream().filter(t->t.equals("Kids")).count());


        // 4. Yol: Burada yine int bir değere Women, Men ve Kids atamasını 0 üzerinden verdik
        // Daha sonra bunu bir for döngüsünde tanımlamış olduğumuz List cinsinden size() ını for içinde tanımladık
        // Bunu ise get(i) ile getirdik

        int countWomen = 0;
        int countMen = 0;
        int countKids = 0;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).equals("Women")) countWomen++;
            if (categories.get(i).equals("Men")) countMen++;
            if (categories.get(i).equals("Kids")) countKids++;
        }

        System.out.println("countWomen = " + countWomen);
        System.out.println("countMen = " + countMen);
        System.out.println("countKids = " + countKids);

        //Bu şekilde de doğrulamasını gerçekleştirdik
        assertEquals(12,countWomen);
        assertEquals(9,countMen);
        assertEquals(13,countKids);

    }
}
