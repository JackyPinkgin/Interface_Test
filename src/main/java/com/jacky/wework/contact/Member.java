package com.jacky.wework.contact;

import io.restassured.response.Response;

import java.util.HashMap;

/**
 * @author 80230531
 * @date 2022/4/25 20:24
 */
public class Member extends Contact {

    //https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN

    public Response create(HashMap<String, Object> map) {

        String body = template("/data/member.json", map);
        return getDefaultRequestSpecification().body(body).when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().extract().response();

    }

}
