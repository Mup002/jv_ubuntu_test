package tmdtdemo.tmdt.config;


public class RequestURL {
    protected static  String[] UN_SECURED_URLs = {
//            "/api/v1/admin/**",
            "/api/v1/account/**",
            "/test/redis/**",
            "/test/token/**",
            "/api/v1/room/**",
            "/api/v1/service/**",
            "/api/v1/comment/allCmt/**"
    };

     protected static String[] SECURED_URLS_ROLE_USER = {
            "/api/v1/cart/**",
            "/api/v1/comment/cmt",
            "/api/v1/user/index",
             "api/v1/order/**"
    };

    protected static String[] SECURED_URLS_ROLE_ADMIN = {
            "api/v1/user/all",
            "/api/v1/admin/**"
    };
}
