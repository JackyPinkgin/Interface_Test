package com.jacky.wework;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * @author 80230531
 * @date 2022/4/26 20:07
 */
public class Restful {
    HashMap<String, Object> query = new HashMap();
    public RequestSpecification requestSpecification = given();

    public Response send() {
        requestSpecification = given().log().all();
        query.entrySet().forEach(entry -> {
            requestSpecification.queryParam(entry.getKey(), entry.getValue());
        });
        return requestSpecification.when().request("get", "baidu.com");
    }

    public static String template(String path, HashMap<String, Object> map) {
        DocumentContext documentContext = JsonPath.parse(Restful.class.getResourceAsStream(path));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });
        return documentContext.jsonString();
    }

    public Response templateFromHar( String path, String pattern,HashMap<String, Object> map) {
        //从har中读取请求，根据map进行更新
        DocumentContext documentContext = JsonPath.parse(Restful.class.getResourceAsStream(path));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });

        //下面两行是伪代码 没有实现的
        String method = documentContext.read("method");
        String url = documentContext.read("url");

        return requestSpecification.when().request(method, url);
    }


}
