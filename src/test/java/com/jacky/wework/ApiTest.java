package com.jacky.wework;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 80230531
 * @date 2022/5/17 11:29
 */
public class ApiTest {

    @Test
    public void templateFromYaml() {
        Api api = new Api();
        api.templateFromYaml("/api/list.yaml", null).then().statusCode(200);
    }
}