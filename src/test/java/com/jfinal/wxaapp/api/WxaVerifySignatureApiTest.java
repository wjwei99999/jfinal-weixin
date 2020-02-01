
package com.jfinal.wxaapp.api;

import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;
/**
 * @author Javen
 */
public class WxaVerifySignatureApiTest {
    public static void main(String[] args) {
        WxaConfig wc = new WxaConfig();
        wc.setAppId("wxf30d9b9b316d5de4");
        wc.setAppSecret("bf0f1a06ba7cc16be643a250ca40213b");

        WxaConfigKit.setWxaConfig(wc);

        ApiResult apiResult = WxaVerifySignatureApi.verifySignature(
                "oUikW0Tmx9FYrSDc7SGMYqWJMClo",
                "{\\\"raw\\\":\\\"Javen Test\\\",\\\"counter\\\":3,\\\"uid\\\":\\\"dc11462476e67688389f66628f425e5d\\\",\\\"cpu_id\\\":\\\"46BAD991-CBCA-4EC5-8416-F7F1E466D9B2\\\"}",
                "eFc37i97P15t64aLeTpxEShrP1meRIcj4A3YnEAb/5IIiD+m6wQSmUWVp9N6sb1lG/Sv2ai2nEewn576GhWGSbzIhJp7ZYGlhn2R6NvOxUwSEvpGcVtD0bsoXbOghWq7E/oDmUcY354N0FqYhbEYgGw9PVxrnwEFtzOLiA9IVRTYAzsAQiKkUIZEpF5JQUUdRRZS5mXz01MSlKoVHVf2MjIMEtjUG6oEGbXB0VfPSS92UQevkTudOSY3K1EtWupf0bM77oY/5JvSwkrjxTcE6DxqbNy3QbmFkb1yi1puTYvIso0q/veequhu7FEv8XKiIuxwMqY19Cera+OGxSAIXw=="
        );
        System.out.println(apiResult);
    }
}
