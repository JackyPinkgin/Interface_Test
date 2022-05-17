package com.jacky.wework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;

import java.io.IOException;

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
//            weworkConfig = new WeworkConfig();
            weworkConfig = load("/conf/WeworkConfig.yaml");
            System.out.println(weworkConfig);
            System.out.println(weworkConfig.agentId);
        }
        return weworkConfig;
    }

    public static WeworkConfig load(String path) {
        // fixed: 2022/4/25 read from yaml or json

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        try {
            return mapper.readValue(WeworkConfig.class.getResourceAsStream(path), WeworkConfig.class);

//            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(WeworkConfig.getInstance()));
//            System.out.println("--------------");

//            System.out.println(mapper.writeValueAsString(WeworkConfig.getInstance()));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
