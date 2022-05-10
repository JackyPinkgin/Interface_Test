//package com.jacky.wework.contact;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//import java.util.Arrays;
//import java.util.HashMap;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @author 80230531
// * @date 2022/5/5 19:54
// */
//class MemberTest {
//
//    static Member member;
//
//    @BeforeAll
//    static void setUp() {
//        member = new Member();
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"Jacky_", "wangjun_", "testerhome_"})
//    void testCreate(String name) {
//        String nameNew = name + member.random;
//        String random = String.valueOf(System.currentTimeMillis()).substring(5, 13);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("userid", nameNew);
//        map.put("name", nameNew);
//        map.put("mobile", "189" + random);
//        map.put("email", random + "@qq.com");
//        map.put("department", 1);
//        map.put("biz_mail", null);
//        map.put("avatar_mediaid", null);
//        member.create(map).then().log().all().statusCode(200).body("errcode", equalTo(0));
//    }
//}