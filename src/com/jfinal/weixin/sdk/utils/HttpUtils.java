package com.jfinal.weixin.sdk.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.kit.StrKit;

/**
 * JFinal-weixin Http请求工具类
 * @author L.cm
 */
public final class HttpUtils {
	
	private HttpUtils() {}
	
	public static String get(String url) {
		return delegate.get(url);
	}
	
	public static String get(String url, Map<String, String> queryParas) {
		return delegate.get(url, queryParas);
	}
	
	public static String post(String url, String data) {
		return delegate.post(url, data);
	}
	
	public static InputStream download(String url) {
		return delegate.download(url);
	}
	public static InputStream download(String url, String params){
		return delegate.download(url, params);
	}
	
	public static String upload(String url, File file, String params) {
		return delegate.upload(url, file, params);
	}
	
	/**
	 * http请求工具 委托，默认使用
	 * 默认使用OkHttp 
	 * 最后使用JFinal HttpKit
	 */
	private interface HttpDelegate {
		String get(String url);
		String get(String url, Map<String, String> queryParas);
		
		String post(String url, String data);
		
		InputStream download(String url);
		InputStream download(String url, String params);
		
		String upload(String url, File file, String params);
	}
	
	// http请求工具代理对象
	private static final HttpDelegate delegate;

	static {
		HttpDelegate delegateToUse = null;
		// com.squareup.okhttp.OkHttpClient?
		if (ClassUtils.isPresent("com.squareup.okhttp.OkHttpClient", JsonUtils.class.getClassLoader())) {
			delegateToUse = new OkHttpDelegate();
		}
		// com.jfinal.kit.HttpKit
		else if (ClassUtils.isPresent("com.jfinal.kit.HttpKit", JsonUtils.class.getClassLoader())) {
			delegateToUse = new HttpKitDelegate();
		}
		delegate = delegateToUse;
	}
	
	private static class OkHttpDelegate implements HttpDelegate {
		com.squareup.okhttp.OkHttpClient httpClient = new com.squareup.okhttp.OkHttpClient();
		
		public static final com.squareup.okhttp.MediaType CONTENT_TYPE_FORM = 
				com.squareup.okhttp.MediaType.parse("application/x-www-form-urlencoded");
		
		private String base(com.squareup.okhttp.Request request) {
			try {
				com.squareup.okhttp.Response response = httpClient.newCall(request).execute();
				return response.body().string();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		@Override
		public String get(String url) {
			com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder().url(url).get().build();
			return base(request);
		}

		@Override
		public String get(String url, Map<String, String> queryParas) {
			com.squareup.okhttp.HttpUrl.Builder urlBuilder = com.squareup.okhttp.HttpUrl.parse(url).newBuilder();
			for (Entry<String, String> entry : queryParas.entrySet()) {
				urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
			}
			com.squareup.okhttp.HttpUrl httpUrl = urlBuilder.build();
			com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder().url(httpUrl).get().build();
			return base(request);
		}

		@Override
		public String post(String url, String params) {
			com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(CONTENT_TYPE_FORM, params);
			com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
				.url(url)
				.post(body)
				.build();
			return base(request);
		}

		@Override
		public InputStream download(String url) {
			return null;
		}

		@Override
		public InputStream download(String url, String params) {
			return null;
		}

		@Override
		public String upload(String url, File file, String params) {
			com.squareup.okhttp.RequestBody fileBody = com.squareup.okhttp.RequestBody
					.create(com.squareup.okhttp.MediaType.parse("application/octet-stream"), file);
			
			com.squareup.okhttp.MultipartBuilder builder = new com.squareup.okhttp.MultipartBuilder()
					.type(com.squareup.okhttp.MultipartBuilder.FORM)
					.addPart(com.squareup.okhttp.Headers.of("Content-Disposition", "form-data; name=\"media\"; filename=\""+ file.getName() + "\""), fileBody);
			
			if (StrKit.notBlank(params)) {
				builder.addPart(com.squareup.okhttp.Headers.of("Content-Disposition", "form-data; name=\"description\""), com.squareup.okhttp.RequestBody.create(null, params));
			}
			
			com.squareup.okhttp.RequestBody requestBody = builder.build();
			com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
					.url(url)
					.post(requestBody)
					.build();
			
			return base(request);
		}
		
	}
	
	private static class HttpKitDelegate implements HttpDelegate {

		@Override
		public String get(String url) {
			return com.jfinal.kit.HttpKit.get(url);
		}

		@Override
		public String get(String url, Map<String, String> queryParas) {
			return com.jfinal.kit.HttpKit.get(url, queryParas);
		}

		@Override
		public String post(String url, String data) {
			return com.jfinal.kit.HttpKit.post(url, data);
		}

		@Override
		public InputStream download(String url) {
			return null;
		}

		@Override
		public InputStream download(String url, String params) {
			return null;
		}

		@Override
		public String upload(String url, File file, String params) {
			return null;
		}
		
	}
}
