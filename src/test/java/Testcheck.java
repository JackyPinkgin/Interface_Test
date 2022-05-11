import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

/**
 * @author 80230531
 * @date 2022/5/10 21:28
 */
public class Testcheck {

    @Test
    public void check() {
        System.out.println("hhhhhh");

        given().when().get("http://10.177.95.73:8080/get_pv").then().statusCode(200);
    }

    @Test
    public void testSchema() {
        given().log().all().when().get("http://10.177.95.73:8080/get_pv")
                .then().log().all()
                .statusCode(200)
                .body(matchesJsonSchema(new File("src/main/resources/schema/get_pv.schema")));
    }

    @Test
    public void testSchemaInClasspath() {
        given().log().all().when().get("http://10.177.95.73:8080/get_pv")
                .then().log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema/get_pv.schema"));
    }

    @Test
    public void testFilter() {
        given()
                .filter((req, res, ctx) -> {
                    System.out.println(req.getURI());
                    System.out.println(res);
                    Response resNew = ctx.next(req, res);
                    System.out.println("============");
                    System.out.println(resNew.getSessionId());
                    System.out.println("============");
                    return resNew;
                })
                .log().all().when().get("http://10.177.95.73:8080/get_pv")
                .then().log().all()
                .statusCode(200)
        ;
    }


}
