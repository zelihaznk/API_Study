package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData {

    public Map<String,String> bookingdatesMethod(String checkin, String checkout){  //Inner Map

        Map<String,String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin",checkin);
        bookingdatesMap.put("checkout",checkout);

        return bookingdatesMap;
    }

    //outer  Map
    public  Map<String,Object> expectedDataMethod(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String,String> bookingdates){

        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname",firstname);
        expectedDataMap.put("lastname",lastname);
        expectedDataMap.put("totalprice",totalprice);
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates",bookingdates);


        return expectedDataMap;
    }

    //önce inner map olusturuyoruz sonra outer ı olusturuyoruz , outer methodu olustururken icerisine olusturdugumuz inner map ı, önüne  map olarak yazıyoruz







}
