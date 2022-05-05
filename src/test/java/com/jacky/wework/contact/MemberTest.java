package com.jacky.wework.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 80230531
 * @date 2022/5/5 19:54
 */
class MemberTest {

    static Member member;

    @BeforeAll
    static void setUp() {
        member = new Member();
    }

    @Test
    void create() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid", "Jacky_" + member.random);
        map.put("name", "Jacky_" + member.random);
        map.put("mobile","189"+member.random.substring(0,8));
        map.put("department", 1);
        map.put("biz_mail",null);
        map.put("avatar_mediaid",null);
        member.create(map).then().log().all().statusCode(200).body("errcode",equalTo(0));
    }
}