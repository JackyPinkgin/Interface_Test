package NotImportant;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * @author 80230531
 * @date 16:21
 */
public class Xueqiu {
    @Test
    public void testSearch2() {
        given().
                queryParam("auth", "Jacky")
                .when().
                get("http://10.177.95.73:8080/tool_list_of_auth")
                .then().log().all().statusCode(200)
                .body("msg",equalTo("所有小工具列表"))
                .body("data.id",hasItem(217))
                .body("data.name",hasItems("图片转txt展示","控制台DEMO"));
    }

    @Test
    public void testSearch() {
        useRelaxedHTTPSValidation();
        given().
                queryParam("q", "alibaba")
                .when().
                get("http://xueqiu.com/k")
                .then().log().all().statusCode(200);
    }
    @Test
    public void testSearch3() {
        useRelaxedHTTPSValidation();
        given().
                queryParam("code", "alibaba").header("Cookie","acw_tc=2760827016508750054724063ed49009f3e4b424fd6516314685a917f74029; xq_a_token=7a84ec3929cd1e60abe21a2c26b9292767c1bd62; xqat=7a84ec3929cd1e60abe21a2c26b9292767c1bd62; xq_r_token=1b89b595a3ae0881d42538acf4948a94fd506777; xq_id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOi0xLCJpc3MiOiJ1YyIsImV4cCI6MTY1Mjc0MzcwMywiY3RtIjoxNjUwODc0OTk3NDI2LCJjaWQiOiJkOWQwbjRBWnVwIn0.Mo0d7mZbE8ruvcP9Q_b5W57TzUvrSr-ZNGpSS2rR-DyjBJiZ8b6EIDBoUaI9sEZ1hgaGoZ3QZo9SdI3g9n-L-dOLG8Oj1R4KFKzJo_LP7-ogkKUVQZh03Odhp8Ffbd63t6ZjlpngNviWQQcjUH0uMQbtkNN5OH0kuDxSWvfSmuVt6e7g6CG1grwerCnOgr3_B8ppLz897nq7uE4OweL4lGzsU-hdjhLlJ5IWzh4GS_aF_Wbaw6nvborIgZsp_wKLNDZXRxn_iPRW6XYw8Uh6ZvuCJZ59aTkjxhtfzk_kLFT3MHYel98ys7uHqTtj5MxPCRsdJ2uYs3FC0FY8Jdre1g; u=381650875005481; device_id=6aa70eca7351d27833e57b92945c9173; Hm_lvt_1db88642e346389874251b5a1eded6e3=1650875010; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1650875181")
                .when().
                get("https://xueqiu.com/stock/search.json")
                .then()
                .log().all().statusCode(200)
//                .body("stocks[0].name",equalTo("阿里巴巴"));
                .body("stocks.name",hasItem("阿里巴巴"));
//                .body("stocks[0].code",equalTo("BABA"));
    }
}
