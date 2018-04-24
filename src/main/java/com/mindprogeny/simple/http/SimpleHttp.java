/*
 * SimpleHttp.java, 26 Mar 2018
 * Created by Joao Viegas (joao.viegas@mindprogeny.com)
 *
 * Copyright (c)2016, 2017 Mind Progeny.
 */

package com.mindprogeny.simple.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.mindprogeny.simple.http.impl.SimpleHttpException;
import com.mindprogeny.simple.http.impl.SimpleHttpResponseImpl;

/**
 * A Simple HTTP client for one-line calls.
 * 
 * @author Jo&atilde;o Viegas (joao.viegas@mindprogeny.com)
 * @since 26 Mar 2018
 *
 */
public class SimpleHttp {

	private SimpleHttp() {

	}

	public static final SimpleHttpResponse call(String method, String url) {
		return call(method, url, (byte[])null);
	}

	public static final SimpleHttpResponse call(String method, String url, String body) {
		return call(method, url, body.getBytes());
	}

	public static final SimpleHttpResponse call(String method, String url, byte[] body) {
		try {
			return call(method, new URL(url), body);
		} catch (MalformedURLException murle) {
			throw new SimpleHttpException(murle);
		}
	}

	public static final SimpleHttpResponse call(String method, URL url, byte[] body) {
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			if (body != null) {
				connection.setDoOutput(true);
				connection.getOutputStream().write(body);
			}
			return new SimpleHttpResponseImpl(connection);
		} catch (IOException ioe) {
			throw new SimpleHttpException(ioe);
		}
	}
}
