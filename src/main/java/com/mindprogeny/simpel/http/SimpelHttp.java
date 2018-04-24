/*
 * SimpelHttp.java, 26 Mar 2018
 * Created by Joao Viegas (joao.viegas@mindprogeny.com)
 *
 * Copyright 2018 Mind Progeny
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.mindprogeny.simpel.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.mindprogeny.simpel.http.impl.SimpelHttpException;
import com.mindprogeny.simpel.http.impl.SimpelHttpResponseImpl;

/**
 * A Simple HTTP client for one-line calls.
 * 
 * @author Jo&atilde;o Viegas (joao.viegas@mindprogeny.com)
 * @since 26 Mar 2018
 *
 */
public class SimpelHttp {

	private SimpelHttp() {

	}

	public static final SimpelHttpResponse call(String method, String url) {
		return call(method, url, (byte[])null);
	}

	public static final SimpelHttpResponse call(String method, String url, String body) {
		return call(method, url, body.getBytes());
	}

	public static final SimpelHttpResponse call(String method, String url, byte[] body) {
		try {
			return call(method, new URL(url), body);
		} catch (MalformedURLException murle) {
			throw new SimpelHttpException(murle);
		}
	}

	public static final SimpelHttpResponse call(String method, URL url, byte[] body) {
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			if (body != null) {
				connection.setDoOutput(true);
				connection.getOutputStream().write(body);
			}
			return new SimpelHttpResponseImpl(connection);
		} catch (IOException ioe) {
			throw new SimpelHttpException(ioe);
		}
	}
}
