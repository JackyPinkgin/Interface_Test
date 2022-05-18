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

    //    public Response list(String id) {
//
//        Response response = requestSpecification
//                .queryParam("id", id)
//                .when()
//                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
//                .then().log().all().extract().response();
//        reset();
//        return response;
//    }
    public Response list(String id) {
        reset();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        return templateFromYaml("/api/list.yaml", map);
    }


    public Response create(String name, String parentid) {
        //让用例更清晰
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("_file", "/data/create.json");
        hashMap.put("name", name);
        hashMap.put("parentid", parentid);
        return templateFromYaml("/api/create.yaml", hashMap);

        //读数据
//        String body = template("/data/create.json", hashMap);
//        hashMap.clear();
//        hashMap.put("_body",body);
//        return templateFromYaml("/api/create.yaml",hashMap);

//        return getDefaultRequestSpecification()
//                .body(body).when()
//                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
//                .then().log().all().extract().response();
    }

    public Response create(HashMap<String, Object> map) {
        map.put("_file", "/data/create.json");
        return templateFromYaml("/api/create.yaml", map);
    }

    //用于个人使用的 创建部门方法
    public Response create1(String name, int parentid) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("_file", "/data/create.json");
        map.put("name", name);
        map.put("parentid", parentid);

        return templateFromYaml("/api/create.yaml", map);
    }

    public Response delete(int id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        return templateFromYaml("/api/delete.yaml",map);
    }

    public Response update(int id, int parentid) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("_file", "/data/update.json");
        map.put("id", id);
        map.put("parentid", parentid);

        return templateFromYaml("/api/update.yaml",map);
    }

    public Response update(HashMap<String, Object> map) {
        return templateFromHar("demo.har.json.json",
                "https://work.weixin.qq.com/wework_admin/party?action=addparty",
                map);
    }

    public Response deleteAll() {
        List<Integer> idList = list("").then().log().all().extract().path("department.id");
        System.out.println(idList);
        idList.forEach(id -> delete(id));
        return null;
    }

}
