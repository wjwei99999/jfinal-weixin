/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.api;

import java.io.Serializable;

import com.jfinal.weixin.sdk.utils.RetryUtils.ResultCheck;

public class WxaAccessToken implements ResultCheck, Serializable {
	private static final long serialVersionUID = 4628857059125205404L;

	@Override
	public boolean matching() {
		return false;
	}

	@Override
	public String getJson() {
		return null;
	}

}
