import io.restassured.RestAssured;
import jdk.nashorn.internal.objects.annotations.SpecializedFunction;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author 80230531
 * @date 2022/4/25 17:55
 */

/**
 * corpId
 * wweee5d5707ac238a9
 *
 * 通讯录同步secret
 * MSyVYmO_wzr2qCJwGKC2hRn8rjhdoRdBpianOihUg3w
 * */
public class TestGetToken {
    @Test
    void testToken(){
        RestAssured.given().log().all()
                .queryParam("corpid",WeworkConfig.getInstance().corpid)
                .queryParam("corpsecret",WeworkConfig.getInstance().contactSecret)
        .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all().statusCode(200)
                .body("errcode",equalTo(0));
    }


}
