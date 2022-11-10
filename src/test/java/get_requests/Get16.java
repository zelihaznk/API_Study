package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;
import static org.hamcrest.Matchers.*;

public class Get16 extends DummyRestApiBaseUrl {

    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200 i) Durum kodu 200
                   ii) There are 24 employees ii) 24 çalışan var
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees iii) "Tiger Nixon" ve "Garrett Winters" çalışanlar arasında
                   iv) The greatest age is 66 iv) En büyük yaş 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick" v) En küçük yaşın adı "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6644770 vi) Tüm çalışanların toplam maaşı 6.644.770






    */

    /*
    Given (Ön koşul)
                   URL: https://dummy.restapiexample.com/api/v1/employees
    When
                   User sends Get Request
    Then (Assertion)
                   i) Status code is 200
    And
                   ii) There are 24 employees   ( 24 tane olduğunu anlamak için List e atabiliriz veya size'ını alabiliriz)
    And
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
    And
                   iv) The greatest age is 66
    And
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
    And
                   vi) Total salary of all employees is 6644770

     */

    @Test
    public void get16() {

        //1. Set The URL
        spec.pathParam("first","employees");

        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. Do Assertion
        assertEquals(200,response.statusCode());

        // ii) There are 24 employees
        // iii) "Tiger Nixon" and "Garrett Winters" are among the employees
        response.then().assertThat().body("data.id",hasSize(24)  //Burada yaptığımız işlem hasSize ile doğrulama yapma kaç eleman varsa bununla buluruz
                ,"data.employee_name",hasItems("Tiger Nixon","Garrett Winters")); //Burada içinde hasItems ile bu öğeler var mı diye soruyoruz


        // iv) The greatest age is 66
        List<Integer> ages = response.jsonPath().getList("data.employee_age");  //Burada yaşların hepsini getList ile Liste attık
        System.out.println("Ages : " + ages); //Burada tüm yaşları göreceğiz
        Collections.sort(ages); //sort methodu ile sıralama yapılır
        System.out.println("Sorted Ages : " + ages);
        System.out.println(ages.get(ages.size()-1)); //Burada son indexin elemanını istiyoruz yani 66 geliyor
        assertEquals(66,(int)(ages.get(ages.size() - 1))); //En büyük yaşın 66 olduğunu doğrulamaya çalıştık


        // v) The name of the lowest age is "Tatyana Fitzpatrick"
       String lowestAge = response.jsonPath().getString("data.findAll{it.employee_age == "+ages.get(0)+"}.employee_name"); //Burada en küçük yaşın Tatyana Fitzpatrick olduğunu doğruladık
        System.out.println("lowestAge = " + lowestAge);
        assertEquals("[Tatyana Fitzpatrick]",lowestAge);
        // findAll() Bu fonksiyon ile bir kaynak içerisinde istediğimiz metnin kaç kere geçtiğini inceleyebiliriz.


        // vi) Total salary of all employees is 6,644,770
        List<Integer> salary = response.jsonPath().getList("data.employee_salary");
        System.out.println("salary = " + salary);

        // 1. Yol:
        int totalSalary = 0;
        for (int each:salary) {
            totalSalary+=each;
        }
        System.out.println("totalSalary = " + totalSalary);
        assertEquals(6644770,totalSalary);

        // 2. Yol:
        int totalSalary2 = salary.stream().reduce(0, Integer::sum); //lambda ile toplamını buluyoruz
        System.out.println("totalSalary2 = " + totalSalary2);
        assertEquals(6644770,totalSalary2);

        // 3. Yol:
        int totalSalary3 = salary.stream().reduce(0,Math::addExact);
        System.out.println("totalSalary3 = " + totalSalary3);
        assertEquals(6644770,totalSalary3);



    }
}
