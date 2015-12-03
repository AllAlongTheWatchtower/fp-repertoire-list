package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireDataParser;
import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class RepertoireToDocumentConverterTest {
	private Repertoire testRep;
	private Document document;
	
	@Before
	public void initialize() {
		RepertoireDataParser parser = new RepertoireDataParser("sampleRepertoireList.xml");
		document = parser.getSearchResults();
		testRep = parser.getRepertoireObject();
	}

	@Test
	public void testEquivalentDocuments() {
		RepertoireToDocumentConverter converter2 = new RepertoireToDocumentConverter(testRep);
		Document duplicate = converter2.getDocument();
		assertTrue(duplicate.isEqualNode(document));
	}
}