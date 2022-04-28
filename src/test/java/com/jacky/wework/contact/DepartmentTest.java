package com.jacky.wework.contact;

import io.restassured.builder.ResponseSpecBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

/**
 * @author 80230531
 * @date 2022/4/25 20:33
 */
class DepartmentTest {
    static Department department;
    String random = String.valueOf(System.currentTimeMillis());

    @BeforeAll
    static void setUp() {
        department = new Department();
    }

    @Test
    void list() {
        department.list("").then().statusCode(200)
                .body("department.name", hasItem("Off_white"))
                .body("department[0].id", equalTo(1));
//        department.list("8").then().statusCode(200)
//                .body("department.name", hasItem("研发部"))
//                .body("department.id[1]", equalTo(9));
    }

    @Test
    void create() {
        department.create("AppDevDept_1","8");
        department.create("AppDevDept_1","8").then().body("errcode",equalTo(60008));
    }

    @Test
    void create1() {
        department.create1("王军HuDongYuLeBu"+random,8).then().body("errmsg",equalTo("created"));
    }

    @Test
    void creste1WithChinese(){
        department.create1("吉喆Department"+ random,1).then().body("errmsg",equalTo("created"));
    }

    @Test
    void delete() {
        //先增加
        department.create1("HuDongYuLeBu",8)
                .then().body("errmsg",equalTo("created"));
        //再删除
        department.delete("34").then()
                .body("errcode",equalTo(0))
                .body("errmsg",equalTo("deleted"));
    }


    @Test
    void update() {
        department.update(33,9).then()
                .body("errmsg",equalTo("updated"));
    }

    @Test
    void update2(){
        int id = department.create1("JackyTest1", 8).then().statusCode(200)
                .extract().path("id");
        department.update(id,1).then().body("errmsg",equalTo("updated"));
        department.delete(""+id);
    }
}