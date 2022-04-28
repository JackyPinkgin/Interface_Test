package com.jacky.wework.contact;

import com.jacky.wework.Wework;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

/**
 * @author 80230531
 * @date 2022/4/25 20:24
 */
public class Department extends Contact {

    public Response list(String id) {

        Response response =  requestSpecification
                .queryParam("id", id)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all().extract().response();
        reset();
        return response;
    }

    public Response create(String name, String parentid) {
        reset();
        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/create.json"))
                .set("$.name", name)
                .set("$.parentid", parentid).jsonString();


        return requestSpecification
                .body(body).when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().extract().response();
    }

    //用于个人使用的 创建部门方法
    public Response create1(String name, int parentid) {
        reset();
        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/create1.json"))
                .set("$.name", name)
                .set("$.parentid", parentid)
                .jsonString();

        return requestSpecification
                .body(body).when()
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
        reset();
        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/update.json"))
                .set("$.id", id)
                .set("$.parentid", parentid).jsonString();

        return requestSpecification
                .body(body).when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all().extract().response();
    }

}
