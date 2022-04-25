package NotImportant;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


/**
 * @author 80230531
 * @date 16:10
 */
public class Baidu {
    @Test
    public void testGetHtml() {
        given().log().all().get("http://www.baidu.com").then().log().all().statusCode(200);
    }

    @Test
    public void testMp3(){
        given().
                queryParam("wd","mp3")
        .when().
                log().all().get("http://www.baidu.com/s")
        .then().
                log().all().statusCode(200);
    }
}
