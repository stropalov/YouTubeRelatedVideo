package org.App;

/*
 * #%L
 * App
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

import org.YoutubeConnector.ConnectionException;
import org.junit.Test;


import static org.junit.Assert.*;
/**
 * Unit test for App.
 */
public class AppTest{

	/**
	 * Check the correct input Url (with "http://www....").
	 */
	@Test
	public void testUrlValidatorWithWWW() throws ConnectionException {
		String inputURL = "http://www.youtube.com/watch?v=YOdFtyv7yoQ";
		UrlValidator validator = new UrlValidator(inputURL);
		assertTrue(validator.isOk());
	}

	/**
	 * Check the correct input Url (without "www....").
	 */
	@Test
	public void testUrlValidatorWithoutWWW() throws ConnectionException {
		String inputURL = "http://youtube.com/watch?v=L1bbN08o6Y4";
		UrlValidator validator = new UrlValidator(inputURL);
		assertTrue(validator.isOk());
	}

	/**
	 * Check the correct input Url (short link "youtu.be").
	 */
	@Test
	public void testShortLinkValidator() throws ConnectionException {
		String inputURL = "http://youtu.be/ZW-YxvjiAhI";
		UrlValidator validator = new UrlValidator(inputURL);
		assertTrue(validator.isOk());
	}

	/**
	 * Check the Url to the nonexistent video-page of youtube.com.
	 */
	@Test
	public void testNonexistentPage() throws ConnectionException {
		String inputURL = "http://www.youtube.com/watch?v=YOdFtysdfs";
		UrlValidator validator = new UrlValidator(inputURL);
		assertFalse(validator.isOk());
	}

	/**
	 * If input Url is not youtube.com.
	 */
	@Test
	public void testInvalidURLvalidator() throws ConnectionException {
		String inputURL = "http://vimeo.com/56879439";
		UrlValidator validator = new UrlValidator(inputURL);
		assertFalse(validator.isOk());
	}

	/**
	 * If input Url is not correct Url.
	 */
	@Test(expected=ConnectionException.class)
	public void testNotURL() throws ConnectionException {
		String inputURL = "youtube";
		UrlValidator validator = new UrlValidator(inputURL);
		validator.isOk();
	}
	
	@Test

}
