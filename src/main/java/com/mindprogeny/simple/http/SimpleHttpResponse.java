/*
 * SimpleHttpResponse.java, 26 Mar 2018
 * Created by Joao Viegas (joao.viegas@mindprogeny.com)
 *
 * Copyright (c)2016, 2017 Mind Progeny.
 */

package com.mindprogeny.simple.http;

import java.io.InputStream;

/**
 * @author Jo&atilde;o Viegas (joao.viegas@mindprogeny.com)
 * @since 26 Mar 2018
 *
 */
public interface SimpleHttpResponse {

	/**
	 * @return
	 */
	int getResponseCode();

	/**
	 * @return
	 */
	InputStream getInputStream();

}
