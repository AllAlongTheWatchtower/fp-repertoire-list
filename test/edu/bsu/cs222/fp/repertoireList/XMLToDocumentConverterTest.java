package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLToDocumentConverterTest {
	XMLToDocumentConverter converter = new XMLToDocumentConverter("sample.xml");
	Document document = converter.getDocument();
	Parser parser = new Parser(document);
	
	@Test
	public void testReadXmlDocumentFromFile() {
		assertNotNull(document.getDocumentElement());
	}
	
	@Test 
	public void testGetSongsNode() {
		Node songs = parser.getSongsNode();
		assertTrue(songs.getNodeName().equals("songs"));
	}
	
	@Test
	public void testCountSongs() {
		Node songs = parser.getSongsNode();
		assertEquals(songs.getChildNodes().getLength(), 87);
	}
	
	@Test
	public void testCountSongsList() {
		NodeList songList = parser.getNodeListOfCompositions();
		assertEquals(songList.getLength(), 87);
	}

	@Test
	public void testFindComposerInFirstNode() {
		NodeList songList = parser.getNodeListOfCompositions();
		Element first = (Element) songList.item(1);
		String composer = first.getLastChild().getPreviousSibling().getTextContent();
		assertTrue(composer.equals("Antoine Forqueray"));
	}
	
	@Test
	public void testFindTitleInFirstNode() {
		NodeList songList = parser.getNodeListOfCompositions();
		Element first = (Element) songList.item(1);
		String title = first.getLastChild().getTextContent();
		assertTrue(title.equals("Pieces de viole: Suite No. 1 in D Minor: VI. La Couperin: Noblement et marque"));
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