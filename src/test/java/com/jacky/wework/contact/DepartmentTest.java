package com.jacky.wework.contact;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

/**
 * @author 80230531
 * @date 2022/4/25 20:33
 */
public class DepartmentTest {
    static Department department;
    String random = String.valueOf(System.currentTimeMillis());

    @Before
    static void setUp() {
        if (department == null) {
            department = new Department();
        }
//        department.deleteAll();
    }

    @Test
    void list() {
        department.list("").then().statusCode(200)
                .body("department.name", hasItem("Off_white"))
                .body("department[0].id", equalTo(1));

        department.list("").then().statusCode(200)
                .body("department.id", hasItems(2));
//        department.list("8").then().statusCode(200)
//                .body("department.name", hasItem("研发部"))
//                .body("department.id[1]", equalTo(9));
    }

//    @Test
//    void list2() {
//        ArrayList<Integer> res = new ArrayList<>();
//        res = department.list("1").then().extract().path("department.id");
//        System.out.println(res);
//        res.forEach(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                if (integer >= 36) {
//                    department.delete(integer);
//                }
//            }
//        });
//    }

    @Test
    void create() {
        department.create("AppDevDept_1", "8");
        department.create("AppDevDept_1", "8").then().body("errcode", equalTo(60008));
    }

    @Test
    void createByMap() {
        HashMap<String, Object> map = new HashMap<String, Object>() {{
            put("name", "JackyJacky" + random);
            put("parentid", "1");
            put("id", null);
        }};
        department.create(map).then()
                .body("errcode", equalTo(0))
                .body("errmsg", equalTo("created"));

    }

    @Test
    void create1() {
        department.create1("王军HuDongYuLeBu" + random, 2).then().body("errmsg", equalTo("created"));
    }

    @Test
    void creste1WithChinese() {
        department.create1("吉喆Department" + random, 1).then().body("errmsg", equalTo("created"));
    }

    @Test
    void delete() {
        //先增加
        ValidatableResponse response = department.create1("HuDongYuLeBu", 1)
                .then().body("errmsg", equalTo("created"));
        Integer id = response.extract().path("id");

        //再删除
        department.delete(id).then()
                .body("errcode", equalTo(0))
                .body("errmsg", equalTo("deleted"));
    }


    @Test
    void update() {
        ValidatableResponse response = department.create1("ruanjianceshibu", 1)
                .then().body("errmsg", equalTo("created"));
        Integer id = response.extract().path("id");
        department.update(id, 1).then()
                .body("errmsg", equalTo("updated"));
    }

    @Test
    void update2() {
        int id = department.create1("JackyTest1", 2).then().statusCode(200)
                .extract().path("id");
        department.update(id, 1).then().body("errmsg", equalTo("updated"));
        department.delete(id);
    }

    @Test
    void deleteAll() {
        department.deleteAll();
    }

    @Test
    void check() {
        given().log().all()
                .get("http://10.177.95.73:8080/get_pv").then().log().all()
                .statusCode(200)
                .body("data.daily7PvList.find{it.id==132}.pv", equalTo(2));
    }
}