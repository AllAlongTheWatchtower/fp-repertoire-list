package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.w3c.dom.Document;

public class ParserTest {
	
	String composer = "Antoine Forqueray";
	XMLToDocumentConverter converter = new XMLToDocumentConverter("sample.xml");
	Document document = converter.getDocument();
	Parser parser = new Parser(document);
	
	@Test
	public void testReadXmlDocumentFromFile() {
		Document document = parser.getSearchResults();
		assertNotNull(document.getDocumentElement());
	}
	
	@Test
	public void testArrayListComposer() {
		ArrayList<Composition> listOfCompositions = parser.getListOfCompositions();
		Composition first = listOfCompositions.get(1);
		String composer = first.getComposer();
		assertTrue(composer.equals("Antoine Forqueray"));
	}
	
	@Test
	public void testArrayListTitle() {
		ArrayList<Composition> listOfCompositions = parser.getListOfCompositions();
		Composition first = listOfCompositions.get(1);
		String title = first.getTitle();
		assertTrue(title.equals("Pieces de viole: Suite No. 1 in D Minor: VI. La Couperin: Noblement et marque"));
	}
	
	@Test
	public void testLengthOfArrayList() {
		ArrayList<Composition> listOfCompositions = parser.getListOfCompositions();
		assertEquals(87, listOfCompositions.size());
	}
}