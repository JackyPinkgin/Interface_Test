package com.jacky.wework.contact;

import com.jacky.wework.Wework;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.*;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;

/**
 * @author 80230531
 * @date 2022/4/25 20:24
 */
public class Department extends Contact {

    public Response list(String id) {

        Response response = requestSpecification
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

    public Response create(HashMap<String, Object> map) {
        reset();
        DocumentContext documentContext = JsonPath.parse(this.getClass().getResourceAsStream("/data/create.json"));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });
        return requestSpecification.body(documentContext.jsonString())
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().extract().response();
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

    public Response delete(int id) {
        reset();
        return requestSpecification
                .param("id", id)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public Response update(int id, int parentid) {
        reset();
        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/update.json"))
                .set("$.id", id)
                .set("$.parentid", parentid).jsonString();

        return requestSpecification
                .body(body).when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all().extract().response();
    }

    public Response deleteAll() {
        reset();
        List<Integer> idList = list("").then().log().all().extract().path("department.id");
        System.out.println(idList);
        idList.forEach(id -> delete(id));
        return null;
    }

}
