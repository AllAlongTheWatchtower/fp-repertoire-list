package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.bsu.cs222.fp.repertoireList.network.URLFactory;

public class URLFactoryTest {
	
	private String apiKey = "NDVFILMAVOOY8ITWS";
	
	@Test
	public void testURLFactory() {
		String targetURL = "http://developer.echonest.com/api/v4/song/search?api_key=NDVFILMAVOOY8ITWS&artist=forqueray&format=xml&results=100";
		URLFactory urlFactory = new URLFactory(apiKey);
		assertTrue(targetURL.equals(urlFactory.createURLForSearchTerm("forqueray")));
	}
	
	@Test
	public void testFirstAndLastName() {
		String targetURL = "http://developer.echonest.com/api/v4/song/search?api_key=NDVFILMAVOOY8ITWS&artist=Amadeus+Mozart&format=xml&results=100";
		URLFactory urlFactory = new URLFactory(apiKey);
		assertTrue(targetURL.equals(urlFactory.createURLForSearchTerm("Amadeus Mozart")));
	}
	
	@Test 
	public void testAccentEncoding() {
		String targetURL = "http://developer.echonest.com/api/v4/song/search?api_key=NDVFILMAVOOY8ITWS&artist=Am%C3%A9d%C3%A9e+M%C3%A9reaux&format=xml&results=100";
		URLFactory urlFactory = new URLFactory(apiKey);
		assertTrue(targetURL.equals(urlFactory.createURLForSearchTerm("Amédée Méreaux")));
	}
	
	@Test
	public void testInternationalCharacters() {
		String targetURL = "http://developer.echonest.com/api/v4/song/search?api_key=NDVFILMAVOOY8ITWS&artist=%E8%AD%9A%E7%9B%BE&format=xml&results=100";
		URLFactory urlFactory = new URLFactory(apiKey);
		assertTrue(targetURL.equals(urlFactory.createURLForSearchTerm("譚盾")));
	}
}