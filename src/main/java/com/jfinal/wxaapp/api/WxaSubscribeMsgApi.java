
package com.jfinal.wxaapp.api;

import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * 订阅消息
 */
public class WxaSubscribeMsgApi {
    private static String addTemplateUrl = "https://api.weixin.qq.com/wxaapi/newtmpl/addtemplate?access_token=";

    /**
     * 组合模板并添加至帐号下的个人模板库
     *
     * @param tid       模板标题 id
     * @param kidList   开发者自行组合好的模板关键词列表
     * @param sceneDesc 服务场景描述，15个字以内
     * @return {ApiResult}
     */
    public static ApiResult addTemplate(String tid, ArrayList<Integer> kidList, String sceneDesc) {
        String accessToken = WxaAccessTokenApi.getAccessTokenStr();
        Kv kv = Kv.by("tid", tid)
                .set("kidList", kidList);
        if (StrKit.notBlank(sceneDesc)) {
            kv.set("sceneDesc", sceneDesc);
        }
        String jsonResult = HttpUtils.post(addTemplateUrl + accessToken, JsonUtils.toJson(kv));
        return new ApiResult(jsonResult);
    }

    private static String delTemplateUrl = "https://api.weixin.qq.com/wxaapi/newtmpl/deltemplate?access_token=";

    /**
     * 删除帐号下的个人模板
     *
     * @param priTmplId 要删除的模板id
     * @return {ApiResult}
     */
    public static ApiResult delTemplate(String priTmplId) {
        String accessToken = WxaAccessTokenApi.getAccessTokenStr();
        Kv kv = Kv.by("priTmplId", priTmplId);
        String jsonResult = HttpUtils.post(delTemplateUrl + accessToken, JsonUtils.toJson(kv));
        return new ApiResult(jsonResult);
    }

    private static String getCategoryUrl = "https://api.weixin.qq.com/wxaapi/newtmpl/getcategory?access_token=";

    /**
     * 获取小程序账号的类目
     *
     * @return {ApiResult}
     */
    public static ApiResult getCategory() {
        return new ApiResult(HttpUtils.get(getCategoryUrl + WxaAccessTokenApi.getAccessTokenStr()));
    }

    private static String getPubTemplateKeyWordsUrl = "https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatekeywords?access_token=";

    /**
     * 获取模板标题下的关键词列表
     *
     * @param tid 模板标题 id
     * @return {ApiResult}
     */
    public static ApiResult getPubTemplateKeyWords(String tid) {
        Map<String, String> queryParas = ParaMap.create("tid", tid).getData();
        return new ApiResult(HttpUtils.get(getPubTemplateKeyWordsUrl + WxaAccessTokenApi.getAccessTokenStr(), queryParas));
    }

    private static String getPubTemplateTitlesUrl = "https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatetitles?access_token=";

    /**
     * 获取帐号所属类目下的公共模板标题
     *
     * @param ids   类目 id，多个用逗号隔开
     * @param start 用于分页，表示从 start 开始。从 0 开始计数
     * @param limit 用于分页，表示拉取 limit 条记录。最大为 30
     * @return {ApiResult}
     */
    public static ApiResult getPubTemplateTitles(String ids, String start, String limit) {
        Map<String, String> queryParas = ParaMap.create("ids", ids)
                .put("start", StrKit.isBlank(start) ? "0" : start)
                .put("limit", StrKit.isBlank(limit) ? "30" : limit)
                .getData();
        return new ApiResult(HttpUtils.get(getPubTemplateTitlesUrl + WxaAccessTokenApi.getAccessTokenStr(), queryParas));
    }

    private static String getTemplateUrl = "https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?access_token=";

    /**
     * 获取当前帐号下的个人模板列表
     *
     * @return {ApiResult}
     */
    public static ApiResult getTemplate() {
        return new ApiResult(HttpUtils.get(getTemplateUrl + WxaAccessTokenApi.getAccessTokenStr()));
    }

    private static String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";

    /**
     * 发送订阅消息
     *
     * @param toUser     接收者（用户）的 openid
     * @param templateId 所需下发的订阅模板id
     * @param data
     * @param page       示例index?foo=bar
     * @return {ApiResult}
     */
    public static ApiResult send(String toUser, String templateId, SubTemplateItem data, String page) {
        String accessToken = WxaAccessTokenApi.getAccessTokenStr();
        Kv kv = Kv.by("touser", toUser)
                .set("template_id", templateId)
                .set("data", data);
        if (StrKit.notBlank(page)) {
            kv.set("page", page);
        }
        String jsonResult = HttpUtils.post(sendUrl + accessToken, JsonUtils.toJson(kv));
        return new ApiResult(jsonResult);
    }

    public class SubTemplateItem extends HashMap<String, Item> {
        private static final long serialVersionUID = -3728490424738325020L;

        public SubTemplateItem() {
        }

        public SubTemplateItem(String key, Item item) {
            this.put(key, item);
        }
    }

    public class Item {
        private Object value;

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Item(Object value) {
            this.value = value;
        }
    }
}








