package org.YoutubeConnector;

/*
 * #%L
 * YouTubeConnector
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * This class is used to get information about related videos from youtube.com
 * @author andrey
 */
public class YoutubeConnector {
	private static final String userAgent = 
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1309.0 Safari/537.17";
	/**
	 * The address of video.
	 */
	private String url;

	/**
	 * Constructor.
	 * @param url - the address of video on youtube.com
	 */
	public YoutubeConnector(String url) {
		this.url = url;
	}

	
	/**
	 * This method connect to youtube, get html page and parse it.
	 * 
	 * @return ArrayList<VideoInfo> - List with information about all related
	 *         video on page.
	 * @throws ConnectionException 
	 */
	public Document getHtmlDocument() throws ConnectionException {
		Document doc;
		try {
			doc = Jsoup.connect(url).userAgent(userAgent).get();
		} catch (IOException e) {
			throw new ConnectionException("There is error while connect to youtube.com!");
		}
		
		return doc;
	}

}