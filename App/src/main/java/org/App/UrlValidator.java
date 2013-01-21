package org.App;

/*
 * #%L
 * youtubeRelatedVideo
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2013 Stropalov Andrey
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.YoutubeConnector.ConnectionException;

/**
 * This class us used to verify the entered URL.
 * 
 * @author andrey
 * 
 */
public class UrlValidator {
	/**
	 * The address of video.
	 */
	private String url;

	/**
	 * Regular expression to check the validity of the address.
	 */
	private static final String regex = "((http://www.|http://|www.|)(youtube.com/watch\\?v=|youtu.be/))[-&=\\w]*";

	/**
	 * Constructor. Set a URL and check it.
	 * 
	 * @param url
	 *            - the address of video on youtube.com
	 */
	public UrlValidator(String url) {
		this.url = url;
	}

	/**
	 * Get the result of check address for validity and accessibility.
	 * 
	 * @return Success or failure of the check.
	 * @throws ConnectionException 
	 */
	public boolean isOk() throws ConnectionException {
		return checkUrl() & makeConnection() ? true : false;
	}

	/**
	 * <p>
	 * Method checks the entered URL.
	 * <p>
	 * URL must point to a video on youtube.com or youto.be
	 * 
	 * @return Success or failure of the check.
	 */
	private boolean checkUrl() {
		return url.matches(regex) ? true : false;
	}

	/**
	 * <p>
	 * Method try to do connection to URL.
	 * <p>
	 * Connection is created using openConnection() and send a request to it
	 * using setRequestMethod().
	 * <p>
	 * Then we receive response code from server and analyze it.
	 * 
	 * @return Success or failure of the check.
	 * @throws ConnectionException 
	 */
	private boolean makeConnection() throws ConnectionException {

		int responseCode = 0;
		try {
			responseCode = getResponse();
		} catch (IOException e) {
			throw new ConnectionException("There is error while connect to youtube.com!");
		}

		// Code 200 means that the request has succeeded.
		return responseCode != 200 ? false : true;
	}

	/**
	 * Get request from server.
	 * @return responseCode - request from server.
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws ProtocolException
	 */
	private int getResponse() throws IOException, MalformedURLException,
			ProtocolException {
		int responseCode;
		HttpURLConnection connection = (HttpURLConnection) new URL(url)
				.openConnection();
		connection.setRequestMethod("HEAD");
		responseCode = connection.getResponseCode();
		return responseCode;
	}
}
