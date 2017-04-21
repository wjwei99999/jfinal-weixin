package com.jfinal.weixin.iot;

import com.jfinal.weixin.iot.protocol.BlueLight;
import com.jfinal.weixin.iot.protocol.BlueLight.CmdId;
import com.jfinal.weixin.sdk.utils.Base64Utils;

public class BlueLightTest {

	public static void main(String[] args) {
		BlueLight light = BlueLight.build(CmdId.OPEN_LIGHT_PUSH, "Hello,WeChat!", (short) 0);
		System.out.println(light);
		System.out.println(Base64Utils.encode(light.toBytes()));
	}

}
