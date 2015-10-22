package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.bsu.cs222.fp.repertoireList.Composition.Builder;

public class XMLWriterTest {
	Parser beforeParser = createParser();
	int lengthOfListBefore = beforeParser.getNodeListOfCompositions().getLength();

	Composition testComposition = new Builder().byComposer("Francois Couperin").withTitle("Les Baricades Mysterieuses");
	
	XMLWriter writer = new XMLWriter(testComposition);
	
	@Test 
	public void testReturnsDocument() {
		Document document = writer.getDocument();	
		assertNotNull(document.getDocumentElement());
	}
	
	@Test 
	public void testGetSongsNode() {
		Node songs = writer.getSongsNode();
		assertTrue(songs.getNodeName().equals("songs"));
	}
	
	@Test
	public void testAddsElementToDocument() {
		Node songsBefore = beforeParser.getSongsNode();
		int countSongsBefore = songsBefore.getChildNodes().getLength();
		Node songsAfter = writer.getSongsNode();
		int countSongsAfter = songsAfter.getChildNodes().getLength();
		assertEquals(countSongsBefore + 1, countSongsAfter);
	}
	
	@Test
	public void testLengthOfListIncreases() {
		System.out.println(lengthOfListBefore);
		Parser afterParser = createParser();
		int lengthOfListAfter = afterParser.getNodeListOfCompositions().getLength();
		System.out.println(lengthOfListAfter);
		assertEquals(lengthOfListBefore + 1, lengthOfListAfter);
	}

	private Parser createParser() {
		XMLToDocumentConverter converter = new XMLToDocumentConverter("RepertoireList.xml");
		Document afterDocument = converter.getDocument();
		Parser parser = new Parser(afterDocument);
		return parser;
	}
}