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

/**
 * Information storage.
 * @author andrey
 *
 */
public class VideoInfo {
	/**
	 * Link to video.
	 */
	private String address;
	/**
	 * Name of video.
	 */
	private String title;
	/** 
	 * Rating means the number of views.
	 */
	private String rating;

	/**
	 * Constructor.
	 * @param address - Link to video.
	 * @param title - Name of video.
	 * @param rating - Number of video`s views.
	 */
	public VideoInfo(String address, String title, String rating) {
		this.address = address;
		this.title = title;
		this.rating = rating;
	}

	public String getAddress() {
		return address;
	}

	public String getTitle() {
		return title;
	}

	public String getRating() {
		return rating;
	}
}
