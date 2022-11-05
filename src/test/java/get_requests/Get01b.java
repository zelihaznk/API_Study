package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01b {

    /*
   Given
       https://reqres.in/api/users/3
   When
       User sends a GET Request to the url

       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/

    @Test
    public void get01() {

        //Adımlar
        // 1. Set The URL ( URL Olustur)
        // 2. Set The Expected Data ( put, post, patch)
        // 3. Send The Request And Get The Response ( Talep gonder ve cevap al)
        // 4. Do Assertion ( Dogrulama Yap)


        //First Step:Set the Url
        //Second Step:Set The Expected Data
        //Third Step:Send The Request and Get The Response
        //Fourt Step:Do Assertion    Assert yapmak zorunlu değildir



        String url = "https://reqres.in/api/users/3"; //Url'i burada stringe atadık

        Response response = given().when().get(url); //given'i RestAssured'den alarak gherrkin dilinde yazmaya başladık
        response.prettyPrint(); //responseyi yazdırır

        // Then    HTTP Status Code should be 200
        // And     Content Type should be JSON
        // And     Status Line should be HTTP/1.1 200 OK
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK");







    }
}
//"https://reqres.in/" bu kısım baseUrl'dir   "api/users/3" bunlar ise parametredir

//headers bilginin bilgisidir başlık gibidir

//Bir methodun body'si yoksa abstract olur


//patch --> var olana düzenlemede hareket eder bu sebeple örneğin id'si bulunmalıdır
/*
GET : getir
	-->id ve benzer verileri inceleyabiliriz
	-->Pretty'den kalıbı kopyalayabiliriz
POST : Yeni kayıt oluştur
	--> Kopyaladıgımız kalıpla body'de JSON dili ile yeni kayıt olusturabiliriz
	--> Mevcut id degisiyor dogal olarak
PATCH : Yama yapma (Yani düzeltme)
	--> Mevcut datanın bir kısmını guncellemek için kullanılır
PUT : Bütün datayı guncelleme
	--> Mevcut id degismeden data'da komple degisiklik yapilabilir
DELETE : Kayıt silme
	--> Mevcut kaydı silmek icin kullanılır
 */

/*
HTTP STATUS CODE
İnformational Responses(bildirimsel cevaplar) ==> 100 lü rakamlar
Succesfull responses(başarılı cevaplar)       ==> 200 lü rakamlar
Redirections(Yönlendirme cevapları)           ==> 300 lü rakamlar
Client Errors(istemci hataları)               ==> 400 lü rakamlar
Server errors(sunucu hataları)                ==> 500 lü rakamlar
 */
