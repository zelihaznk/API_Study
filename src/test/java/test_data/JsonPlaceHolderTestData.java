package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String,Object> expectedDataMethod(Integer userId, String title, Boolean completed ){


        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("userId",userId);
        expectedDataMap.put("title",title);
        expectedDataMap.put("completed",completed);

        return expectedDataMap;

        //hmmm aslinda simdi belli oldu neden wrapper class kullandigimiz
        //ver null i gitsin
    }

    //Bu methodu oluşturma sebebimiz patch işlemi yapabilmekti yukarıdakinden tek farkı burada null yazılan yer varsa ona göre hareket et demektir
    public Map<String,Object> expectedDataMethodCondition(Integer userId, String title, Boolean completed ){


        Map<String,Object> expectedDataMap = new HashMap<>();

        if (userId!=null){
            expectedDataMap.put("userId",userId);
        }

        if (title!=null){
            expectedDataMap.put("title",title);
        }

        if (completed!=null){
            expectedDataMap.put("completed",completed);
        }

        return expectedDataMap;
    }

    public String expectedDataInString(Integer userId,String title,Boolean completed){

        String expectedData = " {\n" +
                "                 \"userId\": "+userId+",\n" +
                "                 \"title\": \""+title+"\",\n" +
                "                 \"completed\": "+completed+"\n" +
                "                 }";


        return expectedData;
    }



}