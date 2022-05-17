package com.jacky.wework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

/**
 * @author 80230531
 * @date 2022/4/26 20:07
 */
public class Api {
    HashMap<String, Object> query = new HashMap();

    public RequestSpecification getDefaultRequestSpecification() {
        return given().log().all();
    }

    public static String template(String path, HashMap<String, Object> map) {
        DocumentContext documentContext = JsonPath.parse(Api.class.getResourceAsStream(path));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });
        return documentContext.jsonString();
    }

    public Response templateFromHar(String path, String pattern, HashMap<String, Object> map) {
        //从har中读取请求，根据map进行更新
        DocumentContext documentContext = JsonPath.parse(Api.class.getResourceAsStream(path));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });

        //下面两行是伪代码 没有实现的
        String method = documentContext.read("method");
        String url = documentContext.read("url");

        return getDefaultRequestSpecification().when().request(method, url);
    }

    public Response templateFromYaml(String path, HashMap<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Restful restful = mapper.readValue(WeworkConfig.class.getResourceAsStream(path), Restful.class);
            if (restful.method.toLowerCase().contains("get")) {
                map.entrySet().forEach(entry -> {
                    restful.query.replace(entry.getKey(), entry.getValue().toString());
                });
            }

            if (restful.method.toLowerCase().contains("post")) {
                if (map.containsKey("_body")) {
                    restful.body = map.get("_body").toString();
                }
                if (map.containsKey("_file")) {
                    String filePath = map.get("_file").toString();
                    map.remove("_file");
                    restful.body = template(filePath, map);
                }
            }

            RequestSpecification requestSpecification = getDefaultRequestSpecification();

            if (restful.query != null) {
                restful.query.entrySet().forEach(entry -> {
                    requestSpecification.queryParam(entry.getKey(), entry.getValue());
                });
            }

            if (restful.body != null) {
                requestSpecification.body(restful.body);
            }

            return requestSpecification.when()
                    .request(restful.method, restful.url)
                    .then().log().all()
                    .extract().response();


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
