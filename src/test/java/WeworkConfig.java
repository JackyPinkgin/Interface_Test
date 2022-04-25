/**
 * @author 80230531
 * @date 2022/4/25 18:35
 */
public class WeworkConfig {
    public String agentId = "1000005";
    public String corpid = "wweee5d5707ac238a9";
    public String contactSecret = "MSyVYmO_wzr2qCJwGKC2hRn8rjhdoRdBpianOihUg3w";

    private static WeworkConfig weworkConfig;

    public static WeworkConfig getInstance() {
        if (weworkConfig == null) {
            weworkConfig = new WeworkConfig();
        }
        return weworkConfig;
    }
}
