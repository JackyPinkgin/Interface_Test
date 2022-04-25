package com.jacky.wework;

import io.restassured.RestAssured;

/**
 * @author 80230531
 * @date 2022/4/25 18:50
 */
public class Wework {
    private static String token;

    public static String getWeworkToken(String secret) {
        return RestAssured.given().log().all()
                .queryParam("corpid", WeworkConfig.getInstance().corpid)
                .queryParam("corpsecret", secret)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all().statusCode(200)
                .extract().path("access_token");
    }

    public static String getWeworkTokenForContact(){
        return null;
    }

    public static String getToken() {
        // TODO: 2022/4/25 支持两种类型的token
        if (token==null){
            token = getWeworkToken(WeworkConfig.getInstance().contactSecret);
        }
        return token;
    }
}
