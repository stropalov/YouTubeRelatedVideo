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

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Class ised to parse youtube html page.
 * @author andrey
 *
 */
public class YoutubeParser {
	private static final String youtubeUrl = "http://www.youtube.com";
	private static final String cssVideoItemClass = "video-list-item";
	private static final String cssLink = "a";
	private static final String cssHrefClass = "href";
	private static final String cssTitleClass = "title";
	private static final String cssViewCountClass = "stat";
	private static final String cssPlaylistClass = "related-playlist yt-pl-thumb-link  yt-uix-sessionlink";

	/**
	 * Document with HTML code.
	 */
	private Document doc;

	/**
	 * Constructor.
	 * 
	 * @param doc - Document with HTML code.
	 */
	public YoutubeParser(Document doc) {
		this.doc = doc;
	}

	/**
	 * <p>
	 * Method receive some information (address, title, rating) about related
	 * videos, which are placed on the page, near to the main video.
	 * <p>
	 * Notice! that on different Web browsers related video may differ and can
	 * be displayed in different order.
	 * 
	 * @return ArrayList<VideoInfo> List with information about all related
	 *         video on page.
	 */
	public ArrayList<VideoInfo> parseYoutubePage() {
		ArrayList<VideoInfo> relatedVideos = new ArrayList<VideoInfo>();

		Elements allVideoBlocks = getVideoBlocks();

		for (Element videoBlock : allVideoBlocks) {
			Elements a = getLinkBlock(videoBlock);

			for (Element link : a) {
				String linkHref = getLink(link);
				String title = getTitle(link);
				String rating = getRating(link);

				relatedVideos.add(new VideoInfo(linkHref, title, rating));
			}
		}
		return relatedVideos;
	}

	/**
	 * Get all html blocks, which contains information about the video.
	 * <p>
	 * One block in simplified form looks like:
	 * 
	 * <pre>
	 * {@code
	 * <li class="video-list-item">
	 * 		<a href="/watch?v=uGOU8X2JZbc">
	 * 			<span class="title" title="Name of video">Name of video</span>
	 * 			<span class="view-count">133 056 просмотров</span>
	 * 		</a>
	 * </li>
	 * }
	 * 
	 * <pre>
	 * @return all related video blocks on page.
	 */
	private Elements getVideoBlocks() {
		return doc.getElementsByClass(cssVideoItemClass);
	}

	/**
	 * Get an HTML block with link.
	 * 
	 * @param videoBlock - A HTML Element with block of link inside.
	 * @return A list of HTML link Elements.
	 */
	private Elements getLinkBlock(Element videoBlock) {
		return videoBlock.getElementsByTag(cssLink);
	}

	/**
	 * Get the href attribute from <a> block.
	 * 
	 * @param link - A HTML Element with link inside.
	 * @return link of video.
	 */
	private String getLink(Element link) {
		return youtubeUrl + link.attr(cssHrefClass);
	}

	/**
	 * Get the title attribute of video.
	 * 
	 * @param link - A HTML link Element with title inside.
	 * @return title of video.
	 */
	private String getTitle(Element link) {
		Elements titles = link.getElementsByClass(cssTitleClass);
		return titles.get(0).text();
	}

	/**
	 * Get the rating of video.
	 * 
	 * @param link
	 *            - A HTML link Element with rating inside.
	 * @return rating of video.
	 */
	private String getRating(Element link) {
		/**
		 * Input element look like:
		 * 
		 * <pre>
		 * {@code
		 * 			<span class="view-count">133 056 просмотров</span>
		 * }
		 * 
		 * <pre>
		 * There are some videos with a playlist, which do not have rating.
		 */
		if (!link.className().equals(cssPlaylistClass)) {
			Elements rating = link.getElementsByClass(cssViewCountClass);
			// Select numbers from the rating string.
			return rating.get(1).text().split(" ")[0];
		} else {
			return "This is playlist";
		}
	}

}
