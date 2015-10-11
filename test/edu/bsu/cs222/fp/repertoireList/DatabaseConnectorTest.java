package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.w3c.dom.Document;

public class DatabaseConnectorTest {
	DatabaseConnector connection = new DatabaseConnector("http://developer.echonest.com/api/v4/song/search?api_key=NDVFILMAVOOY8ITWS&artist=antoine+forqueray&format=xml&results=100");
	
	@Test
	public void returnsDocument() {
		assertTrue((connection.getListOfCompositions() instanceof Document));
	}
}
