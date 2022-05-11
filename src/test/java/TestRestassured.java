import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.Base64;

import static io.restassured.RestAssured.*;

/**
 * @author 80230531
 * @date 2022/5/10 19:40
 */
public class TestRestassured {
    public static RequestSpecification reqSpec;
    public static RequestSpecification build;

    @BeforeClass
    public static void setUpClass() {
        RestAssured.filters((req, res, ctx) -> {
            req.header("testtest", "Jackyhere");
            return ctx.next(req, res);
        });
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
    public void Testcheckcheck() {
        given()
                .spec(reqSpec)
                .log().all()
                .get("/get_pv").then().log().all()
                .statusCode(200)
                .body("data.daily7PvList.find{it.id==132}.pv", CoreMatchers.equalTo(3));
    }

    @Test
    public void testFilterAddCookie() {
        given().log().all()
                .spec(reqSpec)
                .when()
                .get("/get_pv").then().log().all()
                .statusCode(200);
    }

    @Test
    public void testtest(){
        String a = "eyJjb2RlIjoiMjAwMCIsIm1zZyI6IiIsImRhdGEiOnsiZGFpbHk3UHZMaXN0IjpbeyJpZCI6MTMzLCJkYXRlIjoiMjAyMi0wNS0xMSIsInB2IjozfSx7ImlkIjoxMzIsImRhdGUiOiIyMDIyLTA1LTEwIiwicHYiOjN9LHsiaWQiOjEzMSwiZGF0ZSI6IjIwMjItMDUtMDkiLCJwdiI6MX0seyJpZCI6MTMwLCJkYXRlIjoiMjAyMi0wNS0wNyIsInB2Ijo1fSx7ImlkIjoxMjksImRhdGUiOiIyMDIyLTA1LTA1IiwicHYiOjF9LHsiaWQiOjEyOCwiZGF0ZSI6IjIwMjItMDQtMjgiLCJwdiI6NH0seyJpZCI6MTI3LCJkYXRlIjoiMjAyMi0wNC0yNyIsInB2IjoyfV0sIndlZWtQdiI6MTksImFsbFB2Ijo0NDA4fX0K";
        String s = new String(Base64.getDecoder().decode(a));
        System.out.println(s);
    }

}
