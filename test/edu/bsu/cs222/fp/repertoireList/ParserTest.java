package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParserTest {
	
	String composer = "Antoine Forqueray";
	URLFactory urlMaker = new URLFactory(composer);
	String url = urlMaker.getURL();	
	DatabaseConnector connection = new DatabaseConnector(url);
	Document results = connection.getListOfCompositions();
	Parser parser = new Parser(results);
	
	@Test 
	public void testGetSongsNode() {
		Node songs = parser.getSongsNode();
		assertTrue(songs.getNodeName().equals("songs"));
	}
	
	public void testCountSongs() {
		Node songs = parser.getSongsNode();
		assertEquals(songs.getChildNodes().getLength(), 87);
	}
	
	public void testCountSongsList() {
		NodeList songList = parser.getNodeListOfCompositions();
		assertEquals(songList.getLength(), 87);
	}

	public void testFindComposerInFirstNode() {
		NodeList songList = parser.getNodeListOfCompositions();
		Element first = (Element) songList.item(1);
		String composer = first.getAttribute("artist_name");
		assertTrue(composer.equals("Antoine Forqueray"));
	}
	
	public void testFindTitleInFirstNode() {
		NodeList songList = parser.getNodeListOfCompositions();
		Element first = (Element) songList.item(1);
		String composer = first.getAttribute("title");
		assertTrue(composer.equals("Pieces de viole: Suite No. 1 in D Minor: VI. La Couperin"));
	}
	
	public void testArrayListComposer() {
		ArrayList<Composition> listOfCompositions = parser.getListOfCompositions();
		Composition first = listOfCompositions.get(1);
		String composer = first.getComposer();
		assertTrue(composer.equals("Antoine Forqueray"));
	}
	
	public void testArrayListTitle() {
		ArrayList<Composition> listOfCompositions = parser.getListOfCompositions();
		Composition first = listOfCompositions.get(1);
		String composer = first.getTitle();
		assertTrue(composer.equals("Pieces de viole: Suite No. 1 in D Minor: VI. La Couperin"));
	}
	
	public void testLengthOfArrayList() {
		ArrayList<Composition> listOfCompositions = parser.getListOfCompositions();
		assertEquals(87, listOfCompositions.size());
	}
}