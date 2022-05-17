package com.jacky.wework.contact;

import com.jacky.wework.Api;
import com.jacky.wework.Wework;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


/**
 * @author 80230531
 * @date 2022/4/26 20:16
 */
public class Contact extends Api {
    String random = String.valueOf(System.currentTimeMillis());

    public Contact() {
    }

    @Override
    public RequestSpecification getDefaultRequestSpecification() {
        RequestSpecification requestSpecification = super.getDefaultRequestSpecification();
        requestSpecification.log().all().queryParam("access_token", Wework.getToken()).contentType(ContentType.JSON)
                .expect().statusCode(200);
        requestSpecification.filter((req, res, ctx) -> {
            return ctx.next(req, res);
        });
        return requestSpecification;
    }
}
