package com.jacky.wework.contact;

import com.jacky.wework.Restful;
import com.jacky.wework.Wework;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

/**
 * @author 80230531
 * @date 2022/4/26 20:16
 */
public class Contact extends Restful {
    public Contact() {
        reset();
    }

    public void reset() {
        requestSpecification = given();
        requestSpecification.log().all()
                .queryParam("access_token", Wework.getToken())
                .contentType(ContentType.JSON)
                .expect().statusCode(200);
    }
}
