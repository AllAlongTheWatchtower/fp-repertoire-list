package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.bsu.cs222.fp.repertoireList.network.URLFactory;

public class URLFactoryTest {
	
	private String apiKey = "NDVFILMAVOOY8ITWS";
	private String urlStart = "http://developer.echonest.com/api/v4/song/search?api_key=";
	private String urlEnd = "&format=xml&results=100";
	@Test
	public void testURLFactory() {
		String targetURL = urlStart + apiKey + "&artist=forqueray" + urlEnd;
		URLFactory urlFactory = new URLFactory(apiKey);
		assertTrue(targetURL.equals(urlFactory.createURLForSearchTerm("forqueray")));
	}
	
	@Test
	public void testFirstAndLastName() {
		String targetURL = urlStart + apiKey + "&artist=Amadeus+Mozart" + urlEnd;
		URLFactory urlFactory = new URLFactory(apiKey);
		assertTrue(targetURL.equals(urlFactory.createURLForSearchTerm("Amadeus Mozart")));
	}
	
	@Test 
	public void testAccentEncoding() {
		String targetURL = urlStart + apiKey + "&artist=Am%C3%A9d%C3%A9e+M%C3%A9reaux" + urlEnd;
		URLFactory urlFactory = new URLFactory(apiKey);
		assertTrue(targetURL.equals(urlFactory.createURLForSearchTerm("Amédée Méreaux")));
	}
	
	@Test
	public void testInternationalCharacters() {
		String targetURL = urlStart + apiKey + "&artist=%E8%AD%9A%E7%9B%BE" + urlEnd;
		URLFactory urlFactory = new URLFactory(apiKey);
		assertTrue(targetURL.equals(urlFactory.createURLForSearchTerm("譚盾")));
	}
}