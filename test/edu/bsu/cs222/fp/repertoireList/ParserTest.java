package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.Parser;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;

public class ParserTest {
	
	private XMLToDocumentConverter converter;
	private Document document;
	private Parser parser;
	private XMLToDocumentConverter flawedConverter;
	private Document flawedDocument;

	@Before 
	public void initialize() {
		converter = new XMLToDocumentConverter("sample.xml");
		document = converter.getDocument();
		parser = new Parser(document);
		flawedConverter = new XMLToDocumentConverter("flawedDocument.xml");
		flawedDocument = flawedConverter.getDocument();
	}

	@Test(expected = RuntimeException.class) 
	public void testException() {
		try {
			new Parser(flawedDocument);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testReadXmlDocumentFromFile() {
		Document document = parser.getSearchResults();
		assertNotNull(document.getDocumentElement());
	}
	
	@Test
	public void testArrayListComposer() {
		List<Composition> listOfCompositions = parser.getRepertoire().getRepertoire();
		Composition first = listOfCompositions.get(1);
		String composer = first.getComposer();
		assertTrue(composer.equals("Antoine Forqueray"));
	}
	
	@Test
	public void testArrayListTitle() {
		List<Composition> listOfCompositions = parser.getRepertoire().getRepertoire();
		Composition first = listOfCompositions.get(1);
		String title = first.getTitle();
		assertTrue(title.equals("Pieces de viole: Suite No. 1 in D Minor: VI. La Couperin: Noblement et marque"));
	}
	
	@Test
	public void testLengthOfArrayList() {
		List<Composition> listOfCompositions = parser.getRepertoire().getRepertoire();
		assertEquals(87, listOfCompositions.size());
	}
}