import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * @author 80230531
 * @date 2022/5/10 21:28
 */
public class Testcheck {

    @Test
    public void check(){
        System.out.println("hhhhhh");

        given().when().get("http://10.177.95.73:8080/get_pv").then().statusCode(200);
    }
}
