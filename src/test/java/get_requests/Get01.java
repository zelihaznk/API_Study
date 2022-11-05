package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

    @Test
    public void get01() {

        /*
Given
        https://restful-booker.herokuapp.com/booking/101
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
 */

        //  i)  Set the URL,
        String url = "https://restful-booker.herokuapp.com/booking/101";

        // ii) Set the expected Data (beklenen datanin olusturulmasi, Post, Put, Patch)
        // Bizden post, put ya da patch istenmedigi icin bu case de kullanmayacagiz.

        // iii) Type code to send request ( Talep gondermek icin kod yazimi)
        Response response=given().when().get(url);
        response.prettyPrint(); //Bu kısım dogrulama için kullanılır yani istenenler yazdırılır sout gibi

        // iv) Do Assertion (dogrulama yapmak)

        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");


        //status code konsola yazdirin
        System.out.println("Status Code :"+response.getStatusCode());

        //Content Type konsola yazdiralim
        System.out.println("Content Type :"+response.getContentType());

        //Status Line konsola yazdiralim
        System.out.println("Status Line :"+response.getStatusLine());

        //Header konsola yazdiralim
        System.out.println("Header :"+response.getHeader("Server"));

        //Headers konsola yazdiralim
        System.out.println("Headers :"+response.getHeaders());

        //Time konsola yazdiralim
        System.out.println("Time :"+response.getTime());

    }
}

/*
1- Postman'i , manuel API testleri icin kullandik
2- Otomasyon testleri icin de Rest Assuret Librariy kullanacagiz
3- Otomasyon testlerimizi yaparken asagidaki adimlari izliyoruz
    a) Gereksinimleri anlamak,
    b) Test Case yaziyoruz
        i) Test Case yaziminda Gherkin dilini kullanacagiz. Bizler yazilim diline hakim olsak da
            karsimizdaki kisiler hakim olmayabilir ama Gherkin ile yazilan testleri anlamakta zorluk cekmeyeceklerdir.
            Gherkin dilinde kullanacagimiz keywordler;

            - Given: On kosullar
            - When: Yapilacak aksiyonlar icin (get(), put(), post(), patch() ve delete() )
            - Then: Istek yaptiktan (request gonderdikten sonra) dogrulama
            - And: Coklu islemlerde kullanacagiz
  c) Test kodlarimizi yazmaya baslayacagiz

        i)  Set the URL,
        ii) Set the expected Data (beklenen datanin olusturulmasi, post, put, patch)
        iii) Type code to send request (Talep gondermek icin kod yazimi)
        iv) Do Assertion ()dogrulama yapmak

 */

/*
-Bütün Web Service'ler API'dir ama her API bir Web Service degildir

1) GET: Sunucudan sadece veri çekmek(okuma) istiyorsak yani veri üzerinde herhangi bir değişiklik (ekleme, slime, modifiye) yapılmayacaksa GET metodunu kullanmamız tavsiye ediliyor.
CRUD operasyonlarından Read’e karşılık geldiğini söyleyebiliriz.
Ör: GET /students kullandığımızda bize öğrenciler listesini dönmesi.
2) POST: Server Api’e body kısmını doldurarak ve veri üzerinde değişiklik yapmak istediğimizde kullanabiliriz
Değişilik yapmak ile kastedilen CRUD operasyonlarından Create ve Update kısımlarını kapsar.
Ör: Post /createUser ile body kısmına kullanıcı bilgileri girip veritabanında bir kullanıcı oluşturulması istenmesi
3) PUT: Post isteğinin özelliklerine sahiptir. Yani CRUD operasyonlarından Create ve Update operasyonlarını yapmak istediğimizde kullanıyoruz.
Post’dan ayrılan tarafı Put isteğinin idempotent ve not cacheable olarak tanımlanması
4) DELETE: CRUD operasyonlarından Delete’e karşılık gelir. Bir veriyi silmek istediğimizde kullanılması tavsiye ediliyor.



https://petstore.swagger.io/   --> Swagger Petstore incaleme sitesi

https://petstore.swagger.io/			--> API
https://restful-booker.herokuapp.com/booking	--> Tets
https://jsonplaceholder.typicode.com/todos	--> Site
https://reqres.in/api/users			--> Örnekleri


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


