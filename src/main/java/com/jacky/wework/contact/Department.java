package com.jacky.wework.contact;

import com.jacky.wework.Wework;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.io.*;

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

    //用于个人使用的 创建部门方法
    public Response create1(String name, int parentid) {
        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/create1.json"))
                .set("$.name", name)
                .set("$.parentid", parentid)
                .jsonString();

        return given().log().all().queryParam("access_token", Wework.getToken())
                .when().body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().extract().response();
    }

    public Response delete(String id) {
        return given().log().all()
                .param("access_token", Wework.getToken()).param("id", id)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public Response update(int id, int parentid){

        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/update.json"))
                .set("$.id", id)
                .set("$.parentid", parentid).jsonString();

        return given().log().all()
                .queryParam("access_token",Wework.getToken())
                .when().body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all().statusCode(200).extract().response();
    }

}
