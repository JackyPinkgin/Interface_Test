package com.jacky.wework.contact;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author 80230531
 * @date 2022/5/10 19:40
 */
public class RestassuredTest {
    public static RequestSpecification reqSpec = null;
    public static RequestSpecification build;

    @BeforeAll
    static void setUp() {
        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder
                .addCookie("hello", "jacky")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("hello", "it is me")
                .addHeader("User-Agent", "Jacky iphone12 pro max");
        reqSpecBuilder.setProxy("127.0.0.1", 8888);
        reqSpecBuilder.setBaseUri("http://10.177.95.73:8080");
        reqSpec = reqSpecBuilder.build();

//        build = new RequestSpecBuilder().setProxy("127.0.0.1", 8888).setBaseUri("http://10.177.95.73:8080").build();
//        build.header("Accept", "application/json, text/plain, */*").header("Accept-Encoding", "gzip, deflate")
//                .header("hello", "it is me")
//                .header("User-Agent", "Jacky iphone")
//                .cookie("hello", "jacky");
    }

    @Test
    void checkcheck() {
        given()
                .spec(reqSpec)
                .log().all()
                .get("/get_pv").then().log().all()
                .statusCode(200)
                .body("data.daily7PvList.find{it.id==132}.pv", CoreMatchers.equalTo(3));
    }
}
