/*
 * SimpelHttpResponseImpl.java, 26 Mar 2018
 * Created by Joao Viegas (joao.viegas@mindprogeny.com)
 *
 * Copyright (c)2016, 2017 Mind Progeny.
 */

package com.mindprogeny.simpel.http.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.mindprogeny.simpel.http.SimpelHttpResponse;

/**
 * @author Jo&atilde;o Viegas (joao.viegas@mindprogeny.com)
 * @since 26 Mar 2018
 *
 */
public class SimpelHttpResponseImpl implements SimpelHttpResponse {

	private int responseCode;
	private InputStream inputStream;

	/**
	 * @param connection
	 */
	public SimpelHttpResponseImpl(HttpURLConnection connection) {
		try {
			responseCode = connection.getResponseCode();
			inputStream = connection.getInputStream();
		} catch (IOException ioe) {
			throw new SimpelHttpException(ioe);
		}
	}

	/**
	 * Gets the value for responseCode
	 * 
	 * @return the responseCode
	 */
	@Override
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * Gets the value for inputStream
	 * 
	 * @return the inputStream
	 */
	@Override
	public InputStream getInputStream() {
		return inputStream;
	}

}
