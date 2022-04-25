package com.jacky.wework.contact;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 80230531
 * @date 2022/4/25 20:33
 */
class DepartmentTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void list() {
        Department department = new Department();
        department.list("").then().statusCode(200)
                .body("department.name", hasItem("Off_white"))
                .body("department[0].id", equalTo(1));
//
        department.list("8").then().statusCode(200)
                .body("department.name",hasItem("研发部"))
                .body("department.id[1]",equalTo(9));
    }

}