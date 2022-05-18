import com.jacky.wework.Wework;
import com.jacky.wework.WeworkConfig;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

/**
 * @author 80230531
 * @date 2022/4/25 17:55
 */

public class TestGetToken {

    @Test
    public void testToken(){
        String token = Wework.getWeworkToken(WeworkConfig.getInstance().contactSecret);
        assertThat(token,not(equalTo(null)));
    }


}
