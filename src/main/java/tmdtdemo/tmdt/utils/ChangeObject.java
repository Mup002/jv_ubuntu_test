package tmdtdemo.tmdt.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.HTTP;
import org.springframework.http.HttpStatus;
import tmdtdemo.tmdt.exception.BaseException;

public class ChangeObject {
    public static String  objectToJson(Object object)  {
        ObjectMapper objectMapper  = new ObjectMapper();
        String json = null;
        try{
            json =  objectMapper.writeValueAsString(object);
        }catch ( JsonProcessingException e){
           e.printStackTrace();
        }
        return json;
    }
}
