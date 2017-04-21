package com.jfinal.weixin.iot.api;

import java.util.List;

import com.jfinal.kit.JMap;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 设备相关 API
 * <p>
 * https://api.weixin.qq.com/device/ 
 * 下的API为设备相关API， 测试号可以调用，正式服务号需要申请权限后才能调用。
 */
public class DeviceApi {
	private static final String TransMsgUrl = "https://api.weixin.qq.com/device/transmsg?access_token=ACCESS_TOKEN";
	private static final String AuthorizeUrl = "https://api.weixin.qq.com/device/authorize_device?access_token=ACCESS_TOKEN";
	private static final String CreateQrcode = "https://api.weixin.qq.com/device/create_qrcode?access_token=ACCESS_TOKEN";
	private static final String CreateQrcodeNew ="https://api.weixin.qq.com/device/getqrcode?access_token=ACCESS_TOKEN&product_id=PRODUCT_ID";
	private static final String GetStatUrl = "https://api.weixin.qq.com/device/get_stat?access_token=ACCESS_TOKEN&device_id=DEVICE_ID";
	private static final String VerifyQrcodeUrl = "https://api.weixin.qq.com/device/verify_qrcode?access_token=ACCESS_TOKEN";
	private static final String GetOpenidUrl = "https://api.weixin.qq.com/device/get_openid?access_token=ACCESS_TOKEN&device_type=DEVICE_TYPE&device_id=DEVICE_ID";
	private static final String bindUrl ="https://api.weixin.qq.com/device/bind?access_token=ACCESS_TOKEN";
	private static final String unbindUrl ="https://api.weixin.qq.com/device/unbind?access_token=ACCESS_TOKEN";
	private static final String compelbindUrl ="https://api.weixin.qq.com/device/compel_bind?access_token=ACCESS_TOKEN";

	/**
	 * 向设备推送消息
	 */
	public ApiResult transMsg(String deviceType, String deviceID, String openID, String content) {
		JMap data = JMap.create("device_type", deviceType);
		data.set("device_id", deviceID);
		data.set("open_id", openID);
		data.set("content", content);
		String url = TransMsgUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		return new ApiResult(HttpUtils.post(url, JsonUtils.toJson(data)));
	}

	/**
	 * 根据设备id获取二维码生成串
	 */
	public ApiResult createQrcode(List<String> deviceIds) {
		JMap data = JMap.create("device_num", deviceIds.size());
		data.set("device_id_list", deviceIds);
		String url = CreateQrcode.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		return new ApiResult(HttpUtils.post(url, JsonUtils.toJson(data)));
	}
	
	/**
	 * 根据product_id获取设备id和二维码生成串
	 */
	public ApiResult createQrcodeNew(String product_id) {
		String url = CreateQrcodeNew.replace("ACCESS_TOKEN",AccessTokenApi.getAccessTokenStr()).replace("PRODUCT_ID", product_id);
		return new ApiResult(HttpUtils.get(url));
	}	
	
	/**
	 * 绑定
	 */	
	public ApiResult bind(String tikect, String deviceid, String openid) {
		JMap data = JMap.create("ticket", tikect);
		data.set("device_id", deviceid);
		data.set("openid", openid);
		String url = bindUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		return new ApiResult(HttpUtils.post(url, JsonUtils.toJson(data)));
	}
	
	/**
	 * 强制绑定
	 */	
	public ApiResult compelBind(String deviceid, String openid) {
		JMap data = JMap.create("device_id", deviceid);
		data.set("openid", openid);
		String url = compelbindUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		return new ApiResult(HttpUtils.post(url, JsonUtils.toJson(data)));
	}
	
	/**
	 * 解绑
	 */
	public ApiResult unbind(String tikect, String deviceid, String openid) {
		JMap data = JMap.create("ticket", tikect);
		data.set("device_id", deviceid);
		data.set("openid", openid);
		String url = unbindUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		return new ApiResult(HttpUtils.post(url, JsonUtils.toJson(data)));
	}

	/**
	 * 批量授权/更新设备属性
	 * <p>
	 * 授权后设备才能进行绑定操作
	 * 
	 * @param devices
	 *            设备属性列表
	 * @param isCreate
	 *            是否首次授权： true 首次授权； false 更新设备属性
	 */
	public ApiResult authorize(List<DeviceAuth> devices, boolean isCreate, String productId) {
		JMap data = JMap.create("device_num", String.valueOf(devices.size()));
		data.set("op_type", isCreate ? "0" : "1");// 请求操作的类型 0：设备授权（缺省值为0） 1：设备更新（更新已授权设备的各属性值）
		data.set("product_id", productId);
		data.set("device_list", devices);
		String url = AuthorizeUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		System.out.println(JsonUtils.toJson(data));
		return new ApiResult(HttpUtils.post(url, JsonUtils.toJson(data)));
	}

	/**
	 * 设备状态查询
	 * <p>
	 * status 0：未授权 1：已经授权（尚未被用户绑定） 2：已经被用户绑定<br/>
	 * {"errcode":0,"errmsg":"ok","status":1,"status_info":"authorized"}
	 */
	public ApiResult getStat(String deviceId) {
		String url = GetStatUrl.replace("DEVICE_ID", deviceId)
				.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		return new ApiResult(HttpUtils.get(url));
	}

	/**
	 * 验证二维码 获取二维码对应设备属性
	 * 
	 * @param ticket
	 *            二维码生成串
	 */
	public ApiResult verifyQrcode(String ticket) {
		JMap data = JMap.create("ticket", ticket);
		String url = VerifyQrcodeUrl.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		return new ApiResult(HttpUtils.post(url, JsonUtils.toJson(data)));
	}

	/**
	 * 根据设备类型和设备id查询绑定的openid
	 */
	public ApiResult getOpenId(String deviceType, String deviceId) {
		String url = GetOpenidUrl.replace("DEVICE_TYPE", deviceType)
				.replace("DEVICE_ID", deviceId)
				.replace("ACCESS_TOKEN", AccessTokenApi.getAccessTokenStr());
		return new ApiResult(HttpUtils.get(url));
	}
}
