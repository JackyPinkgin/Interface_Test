package com.jacky.wework.contact;

import com.jacky.wework.Wework;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * @author 80230531
 * @date 2022/4/25 20:24
 */
public class Department {

    public Response list(String id){
        return given().log().all()
                .param("access_token", Wework.getToken())
                .param("id",id)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract().response();
    }

}
