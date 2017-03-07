/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.msg;

import java.lang.reflect.Field;

import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.utils.XmlHelper;
import com.jfinal.wxaapp.msg.bean.WxaMsg;

/**
 * xml格式消息解析
 * @author l.cm
 *
 */
public class XmlMsgParser extends MsgModelParser implements IMsgParser {
	private static Log log = Log.getLog(XmlMsgParser.class);
	
	@Override
	public WxaMsg parser(String msgStr) {
		XmlHelper xmlHelper = XmlHelper.of(msgStr);
		MsgModel msgModel = toMsgModel(xmlHelper);
		return parserMsg(msgModel);
	}

	private static MsgModel toMsgModel(XmlHelper xmlHelper) {
		MsgModel msgModel = new MsgModel();
		Field[] fields = MsgModel.class.getDeclaredFields();
		for (Field field : fields) {
			XPath xpathAnno = field.getAnnotation(XPath.class);
			if (xpathAnno == null) {
				continue;
			}
			
			String xpath    = xpathAnno.value();
			Class<?> type   = field.getType();
			String strValue = xmlHelper.getString(xpath);
			
			Object value = convert(type, strValue);
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			try {
				field.set(msgModel, value);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage(), e);
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}
		return msgModel;
	}
	
	private static final Object convert(Class<?> type, String s) {
		if (type == String.class) {
			return ("".equals(s) ? null : s);
		}
		s = s.trim();
		if ("".equals(s)) {
			return null;
		}
		if (type == Integer.class || type == int.class) {
			return Integer.parseInt(s);
		}
		if (type == Long.class || type == long.class) {
			return Long.parseLong(s);
		}
		throw new RuntimeException("Please add code in " + XmlMsgParser.class  + ". The type can't be converted: " + type.getName());
	}
}
