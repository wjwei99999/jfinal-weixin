package com.jfinal.weixin.sdk.msg;

import org.junit.Assert;
import org.junit.Test;

import com.jfinal.weixin.sdk.msg.in.InMsg;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;

public class OutMsgTest {

	@Test
	public void test1() {
		OutTextMsg msg = new OutTextMsg();
		msg.setToUserName("to james");
		msg.setFromUserName("from james");
		msg.setCreateTime(msg.now());
		msg.setContent("jfinal weixin 极速开发平台碉堡了");
		
		InMsg inMsg = InMsgParser.parse(msg.toXml());
		Assert.assertTrue(inMsg instanceof InTextMsg);
	}
	
}
