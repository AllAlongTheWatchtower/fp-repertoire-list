package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.RepertoireToDocument;
import edu.bsu.cs222.fp.repertoireList.dataHandling.Parser;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;

public class RepertoireToDocumentTest {
	private Repertoire testRep;
	private Document document;
	
	@Before
	public void initialize() {
		XMLToDocumentConverter converter = new XMLToDocumentConverter("sampleRepertoireList.xml");
		document = converter.getDocument();
		Parser parser = new Parser(document);
		testRep = parser.getRepertoireObject();
	}

	@Test
	public void testEquivalentDocuments() {
		RepertoireToDocument converter2 = new RepertoireToDocument(testRep);
		Document duplicate = converter2.getDocument();
		assertTrue(duplicate.isEqualNode(document));
	}
}