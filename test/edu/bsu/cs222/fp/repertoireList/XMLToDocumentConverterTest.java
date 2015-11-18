package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.RepertoireDataParser;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;

public class XMLToDocumentConverterTest {
	private XMLToDocumentConverter converter;
	private Document document;
	private RepertoireDataParser parser;
	
	@Before 
	public void initialize() {
		converter = new XMLToDocumentConverter("sample.xml");
		document = converter.getDocument();
		parser = new RepertoireDataParser(document);
	}
	
	@Test(expected = RuntimeException.class) 
	public void testException() {
		try {
			new XMLToDocumentConverter("fake.xml");
		} catch (RuntimeException e) {
			throw new RuntimeException();
		}
	}
	
	@Test
	public void testReadXmlDocumentFromFile() {
		assertNotNull(document.getDocumentElement());
	}
	
	@Test
	public void testArrayListComposer() {
		List<Composition> listOfCompositions = parser.getRepertoireObject().getRepertoireList();
		Composition first = listOfCompositions.get(1);
		String composer = first.getComposer();
		assertTrue(composer.equals("Antoine Forqueray"));
	}
	
	@Test
	public void testArrayListTitle() {
		List<Composition> listOfCompositions = parser.getRepertoireObject().getRepertoireList();
		Composition first = listOfCompositions.get(1);
		String title = first.getTitle();
		assertTrue(title.equals("Pieces de viole: Suite No. 1 in D Minor: VI. La Couperin: Noblement et marque"));
	}
	
	@Test
	public void testLengthOfArrayList() {
		List<Composition> listOfCompositions = parser.getRepertoireObject().getRepertoireList();
		assertEquals(87, listOfCompositions.size());
	}
}