package com.jacky.wework.contact;

import com.jacky.wework.Wework;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * @author 80230531
 * @date 2022/4/25 20:24
 */
public class Department {

    public Response list(String id) {
        return given().log().all()
                .param("access_token", Wework.getToken())
                .param("id", id)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract().response();
    }

    public Response create(String name, String parentid) {
        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/create.json"))
                .set("$.name", name)
                .set("$.parentid", parentid).jsonString();

        return given().log().all().queryParam("access_token", Wework.getToken())
                .when().body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().extract().response();
    }

}
