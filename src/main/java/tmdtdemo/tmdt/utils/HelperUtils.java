package tmdtdemo.tmdt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class HelperUtils {
    public static String accessKeyBuilderRedisKey(String str){
        StringBuilder builder = new StringBuilder();
        builder.append("accessKey_");
        builder.append(str);
        return builder.toString();
    }

    public static String cartBuilderRedisKey(String username){
        StringBuilder builder = new StringBuilder();
        builder.append("cart_userId=");
        builder.append(String.valueOf(username));
        return builder.toString();
    }

    public static final ObjectWriter JSON_WRITTER = new ObjectMapper().writer().withDefaultPrettyPrinter();

}
