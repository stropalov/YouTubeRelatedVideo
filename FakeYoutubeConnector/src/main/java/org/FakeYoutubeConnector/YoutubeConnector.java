package org.FakeYoutubeConnector;

/*
 * #%L
 * FakeYoutubeConnector
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


import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * This class is used to get information about related videos from html file from disk.
 * 
 * @author andrey
 */
public class YoutubeConnector {

	/**
	 * The address of video.
	 */
	private String htmlFile;

	/**
	 * Constructor.
	 * 
	 * @param htmlFile  - the address of video on youtube.com
	 */
	public YoutubeConnector(String htmlFile) {
		this.htmlFile = "data/NO GOD! PLEASE NO!!! NOOOOOOOOOO - YouTube.htm.html";
	}
	

	/**
	 * This method get html file from disk and parse it.
	 * 
	 * @return ArrayList<VideoInfo> - List with information about all related
	 *         video on page.
	 * @throws ConnectionException 
	 */
	public Document getHtmlDocument() throws ConnectionException {
		File input = new File(htmlFile);
		Document doc = null;
		try {
			doc = Jsoup.parse(input, "UTF-8", "");
		} catch (IOException e) {
			throw new ConnectionException("There is error while connect to youtube.com!");
		}

		return doc;
	}

}
