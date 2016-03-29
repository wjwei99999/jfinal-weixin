package com.jfinal.weixin.sdk.utils;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * 微信证书管理器
 */
class WeiXinTrustManager implements X509TrustManager {
	X509TrustManager weixinX509TrustManager;

	WeiXinTrustManager(KeyStore keyStore) throws Exception {
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(keyStore);
		TrustManager tms [] = tmf.getTrustManagers();
		for (int i = 0; i < tms.length; i++) {
			if (tms[i] instanceof X509TrustManager) {
				weixinX509TrustManager = (X509TrustManager) tms[i]; 
				return; 
			} 
		}
		throw new Exception("Couldn't initialize");
	}

	public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		weixinX509TrustManager.checkClientTrusted(chain, authType);
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException { 
		weixinX509TrustManager.checkServerTrusted(chain, authType); 
	}

	public X509Certificate[] getAcceptedIssuers() {
		return weixinX509TrustManager.getAcceptedIssuers();
	}
}