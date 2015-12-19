package com.jfinal.weixin.sdk.msg;

import org.junit.Assert;
import org.junit.Test;

import com.jfinal.weixin.sdk.msg.in.*;
import com.jfinal.weixin.sdk.msg.in.event.*;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;

public class XMLMsgTest {
	
	/**
	 * 菜单事件
	 */
	@Test
	public void test001() {
		String xml = "<xml>"
				+ "<ToUserName><![CDATA[ToUserName]]></ToUserName>"
				+ "<FromUserName><![CDATA[FromUserName]]></FromUserName>"
				+ "<CreateTime>1446526359</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[scancode_waitmsg]]></Event>"
				+ "<EventKey><![CDATA[2_1]]></EventKey>"
				+ "<ScanCodeInfo>"
					+ "<ScanType><![CDATA[qrcode]]></ScanType>"
					+ "<ScanResult><![CDATA[http://www.jfinal.com]]></ScanResult>"
				+ "</ScanCodeInfo>"
				+ "</xml>";

		InMsg inMsg = InMsgParser.parse(xml);
		
		Assert.assertTrue(inMsg instanceof InMenuEvent);
		InMenuEvent inMenuEvent = (InMenuEvent) inMsg;

		ScanCodeInfo scanCodeInfo = inMenuEvent.getScanCodeInfo();
		String scanType = scanCodeInfo.getScanType();
		String scanResult = scanCodeInfo.getScanResult();

		Assert.assertEquals(scanType, "qrcode");
		Assert.assertEquals(scanResult, "http://www.jfinal.com");
	}
	
	/**
	 * 普通文本
	 */
	@Test
	public void test002() {
		String xml = "<xml>"
				+ "<ToUserName><![CDATA[gh_5f58ae0646df]]></ToUserName>"
				+ "<FromUserName><![CDATA[oy_CjjrWbbbBQxMGSZxpA48XgIbo]]></FromUserName>"
				+ "<CreateTime>1450496618</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>"
				+ "<Content><![CDATA[hello]]></Content>"
				+ "<MsgId>6229835537670016495</MsgId>"
				+ "</xml>";
		
		InMsg inMsg = InMsgParser.parse(xml);
		
		Assert.assertTrue(inMsg instanceof InTextMsg);
	}
	
	/**
	 * 地理位置
	 */
	@Test
	public void test003() {
		String xml = "<xml>"
				+ "<ToUserName><![CDATA[gh_5f58ae0646df]]></ToUserName>"
				+ "<FromUserName><![CDATA[oy_CjjrWbbbBQxMGSZxpA48XgIbo]]></FromUserName>"
				+ "<CreateTime>1450496950</CreateTime>"
				+ "<MsgType><![CDATA[location]]></MsgType>"
				+ "<Location_X>39.845194</Location_X>"
				+ "<Location_Y>116.314440</Location_Y>"
				+ "<Scale>15</Scale>"
				+ "<Label><![CDATA[北京市丰台区花乡万芳园(二区)内(樊羊路东)]]></Label>"
				+ "<MsgId>6229836963599158839</MsgId>"
				+ "</xml>";
		
		InMsg inMsg = InMsgParser.parse(xml);
		
		Assert.assertTrue(inMsg instanceof InLocationMsg);
	}
	
	/**
	 * 小视屏
	 */
	@Test
	public void test004() {
		String xml = "<xml>"
				+ "<ToUserName><![CDATA[gh_5f58ae0646df]]></ToUserName>"
				+ "<FromUserName><![CDATA[oy_CjjrWbbbBQxMGSZxpA48XgIbo]]></FromUserName>"
				+ "<CreateTime>1450497127</CreateTime>"
				+ "<MsgType><![CDATA[shortvideo]]></MsgType>"
				+ "<MediaId><![CDATA[pTreJdkK7EKZM92nW93zns3ojLqkUjTybckcqrT1vmrQltM6ehF8JVffL7tt_ioT]]></MediaId>"
				+ "<ThumbMediaId><![CDATA[b4ZrHpXILajGoNFvLI6O0cpG9n4t8ALG5u-Zzt3j4Q2Opar8zdEjKP6k9ray7FGC]]></ThumbMediaId>"
				+ "<MsgId>6229837723808370244</MsgId>"
				+ "</xml>";
		
		InMsg inMsg = InMsgParser.parse(xml);
		
		Assert.assertTrue(inMsg instanceof InShortVideoMsg);
	}
	
	/**
	 * 连接
	 */
	@Test
	public void test005() {
		String xml = "<xml>"
				+ "<ToUserName><![CDATA[gh_5f58ae0646df]]></ToUserName>"
				+ "<FromUserName><![CDATA[oy_CjjrWbbbBQxMGSZxpA48XgIbo]]></FromUserName>"
				+ "<CreateTime>1450497332</CreateTime>"
				+ "<MsgType><![CDATA[link]]></MsgType>"
				+ "<Title><![CDATA[Rewrite规则在Nginx的使用中]]></Title>"
				+ "<Description><![CDATA[http://mp.weixin.qq.com/s?__biz=MzA3MzYwNjQ3NA==&mid=400220813&idx=2&sn=e25c6e8d37ae65812103ac98c0a302df&scene=2&srcid=1030mtcKdu5arVKLg0SCaLt1#rd]]></Description>"
				+ "<Url><![CDATA[http://mp.weixin.qq.com/s?__biz=MzA3MzYwNjQ3NA==&mid=400220813&idx=2&sn=e25c6e8d37ae65812103ac98c0a302df&scene=2&srcid=1030mtcKdu5arVKLg0SCaLt1#rd]]></Url>"
				+ "<MsgId>6229838604276665948</MsgId>"
				+ "</xml>";
		
		InMsg inMsg = InMsgParser.parse(xml);
		
		Assert.assertTrue(inMsg instanceof InLinkMsg);
	}
	
	/**
	 * 照片
	 */
	@Test
	public void test006() {
		String xml = "<xml>"
				+ "<ToUserName><![CDATA[gh_5f58ae0646df]]></ToUserName>"
				+ "<FromUserName><![CDATA[oy_CjjrWbbbBQxMGSZxpA48XgIbo]]></FromUserName>"
				+ "<CreateTime>1450497590</CreateTime>"
				+ "<MsgType><![CDATA[image]]></MsgType>"
				+ "<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/0ljjszjOiaqBE1g3zE5FjkhotCauUTnLBMXI0ibnBUvQXBfRIx4Df7eo0mugnc0XBkOe27HjnPutoF8rWDSyo1RA/0]]></PicUrl>"
				+ "<MsgId>6229839712378228337</MsgId>"
				+ "<MediaId><![CDATA[M6k1DEIr-St-rNLjcEawpKIGM99n1azlNxucZV9T6RTXzVT93rDIuJz4EgC9Io7I]]></MediaId>"
				+ "</xml>";
		
		InMsg inMsg = InMsgParser.parse(xml);
		
		Assert.assertTrue(inMsg instanceof InImageMsg);
	}
	
	/**
	 * 视频
	 */
	@Test
	public void test007() {
		String xml = "<xml>"
				+ "<ToUserName><![CDATA[gh_5f58ae0646df]]></ToUserName>"
				+ "<FromUserName><![CDATA[oy_CjjrWbbbBQxMGSZxpA48XgIbo]]></FromUserName>"
				+ "<CreateTime>1450497793</CreateTime>"
				+ "<MsgType><![CDATA[video]]></MsgType>"
				+ "<MediaId><![CDATA[HrmSoAdQVHkVw-tAmm-UDlKetmO90v6uvQPv5I2Qt5-JJnqkq0y9plbynoggXxxD]]></MediaId>"
				+ "<ThumbMediaId><![CDATA[8CFAuT5n_pjQl5Qt14As3-A0zl55yx2alGqGktCTBt3Yc8W2K0_RSuen7IxsxH--]]></ThumbMediaId>"
				+ "<MsgId>6229840584256589452</MsgId>"
				+ "</xml>";
		
		InMsg inMsg = InMsgParser.parse(xml);
		
		Assert.assertTrue(inMsg instanceof InVideoMsg);
	}
	
	/**
	 * 语音
	 */
	@Test
	public void test008() {
		String xml = "<xml>"
				+ "<ToUserName><![CDATA[gh_5f58ae0646df]]></ToUserName>"
				+ "<FromUserName><![CDATA[oy_CjjrWbbbBQxMGSZxpA48XgIbo]]></FromUserName>"
				+ "<CreateTime>1450497977</CreateTime>"
				+ "<MsgType><![CDATA[voice]]></MsgType>"
				+ "<MediaId><![CDATA[381ab57oKuQUsT4Hdgq5vOACQjPLvSKrN2_s9EKFWSrcFxHiP-d-hiF7wsBsWY9_]]></MediaId>"
				+ "<Format><![CDATA[amr]]></Format>"
				+ "<MsgId>6229841374129160192</MsgId>"
				+ "<Recognition><![CDATA[莎娃迪卡！]]></Recognition>"
				+ "</xml>";
		
		InMsg inMsg = InMsgParser.parse(xml);
		
		Assert.assertTrue(inMsg instanceof InVoiceMsg);
		Assert.assertTrue(inMsg instanceof InSpeechRecognitionResults);
	}
	
	/**
	 * 周边摇一摇
	 */
	@Test
	public void test009() {
		String xml = "<xml>"
				+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
				+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
				+ "<CreateTime>1433332012</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[ShakearoundUserShake]]></Event>"
				+ "<ChosenBeacon>"
					+ "<Uuid><![CDATA[FDA50693-A4E2-4FB1-AFCF-C6EB07647825]]></Uuid>"
					+ "<Major>1002</Major>"
					+ "<Minor>1223</Minor>"
					+ "<Distance>0.057</Distance>"
				+ "</ChosenBeacon>"
				+ "<AroundBeacons>"
					+ "<AroundBeacon>"
						+ "<Uuid><![CDATA[uuid]]></Uuid>"
						+ "<Major>1002</Major>"
						+ "<Minor>1223</Minor>"
						+ "<Distance>166.816</Distance>"
					+ "</AroundBeacon>"
					+ "<AroundBeacon>"
						+ "<Uuid><![CDATA[uuid]]></Uuid>"
						+ "<Major>1002</Major>"
						+ "<Minor>1223</Minor>"
						+ "<Distance>15.013</Distance>"
					+ "</AroundBeacon>"
				+ "</AroundBeacons>"
				+ "</xml>";
		
		InMsg inMsg = InMsgParser.parse(xml);
		
		Assert.assertTrue(inMsg instanceof InShakearoundUserShakeEvent);
	}
	
}
