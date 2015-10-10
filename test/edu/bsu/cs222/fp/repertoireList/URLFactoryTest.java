package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import org.junit.Test;

public class URLFactoryTest {
	
	@Test
	public void testURLFactory() {
		String targetURL = "http://developer.echonest.com/api/v4/song/search?api_key=NDVFILMAVOOY8ITWS&artist=forqueray&format=json&results=100";
		URLFactory urlFactory = new URLFactory("forqueray");
		assertTrue(targetURL.equals(urlFactory.getURL()));
	}
	
	@Test
	public void testFirstAndLastName() {
		String query = "Amadeus Mozart";
		String targetURL = "http://developer.echonest.com/api/v4/song/search?api_key=NDVFILMAVOOY8ITWS&artist=Amadeus+Mozart&format=json&results=100";
		URLFactory urlFactory = new URLFactory(query);
		assertTrue(targetURL.equals(urlFactory.getURL()));
	}
	
	@Test 
	public void testAccentEncoding() {
		String query = "Amédée Méreaux";
		String targetURL = "http://developer.echonest.com/api/v4/song/search?api_key=NDVFILMAVOOY8ITWS&artist=Am%C3%A9d%C3%A9e+M%C3%A9reaux&format=json&results=100";
		URLFactory urlFactory = new URLFactory(query);
		assertTrue(targetURL.equals(urlFactory.getURL()));
	}
	
	public void testInternationalCharacters() {
		String query = "譚盾";
		String targetURL = "http://developer.echonest.com/api/v4/song/search?api_key=NDVFILMAVOOY8ITWS&artist=%E8%AD%9A%E7%9B%BE&format=json&results=100";
		URLFactory urlFactory = new URLFactory(query);
		assertTrue(targetURL.equals(urlFactory.getURL()));
	}
}