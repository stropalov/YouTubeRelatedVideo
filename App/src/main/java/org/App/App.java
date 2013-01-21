package org.App;

/*
 * #%L
 * YouTubeRelatedVideos
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

import java.util.ArrayList;
import java.util.Scanner;

import org.YoutubeConnector.ConnectionException;
import org.YoutubeConnector.YoutubeConnector;
import org.jsoup.nodes.Document;

/**
 * This class is used to interact with the user.
 */
public class App {

	/**
	 * User enters URL of YouTube video and receives a list of related video.
	 * 
	 * @param args - the arguments
	 * @throws ConnectionException 
	 */
	public static void main(String[] args) throws ConnectionException {
		while (true) {
			String inputURL = setUrl();
			UrlValidator validator = new UrlValidator(inputURL);

			if (validator.isOk()) {
				printVideoList(inputURL);
				break;
			} else {
				System.out.println("Error in URL.");
			}
		}
	}

	/**
	 * Get input Url from user.
	 * @return input Url.
	 */
	private static String setUrl() {
		System.out.println("Enter the address of video on youtube.com:");
		Scanner input = new Scanner(System.in);
		return input.next();
	}

	/**
	 * Print list with information about related videos.
	 * @param connect - Url of video on youtube.
	 * @throws ConnectionException 
	 */
	private static void printVideoList(String inputURL) throws ConnectionException {
		YoutubeConnector connector = new YoutubeConnector(inputURL);
		Document doc = connector.getHtmlDocument();
		YoutubeParser parser = new YoutubeParser(doc);
		ArrayList<VideoInfo> videos = parser.parseYoutubePage();
		
		for (int i = 0; i < videos.size(); i++) {
			System.out.println("\n№" + i);
			System.out.println("Address: " + videos.get(i).getAddress());
			System.out.println("Title: " + videos.get(i).getTitle());
			System.out.println("Rating: " + videos.get(i).getRating());
		}
	}

}
