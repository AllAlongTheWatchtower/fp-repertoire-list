package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireDataParser;
import edu.bsu.cs222.fp.repertoireList.datahandling.XmlWriter;

public class XmlWriterTest {
	Document document;
		
	@Before
	public void initialize() {
		RepertoireDataParser parser = new RepertoireDataParser("sampleRepertoireList.xml");
		document = parser.getSearchResults();
	}
	
	@Test
	public void testWritesToXml() {
		XmlWriter writer = new XmlWriter("sampleRepertoireList.xml");
		writer.writeDocument(document);
		RepertoireDataParser parser2 = new RepertoireDataParser("sampleRepertoireList.xml");
		Document document2 = parser2.getSearchResults();
		assertTrue(document.isEqualNode(document2));
	}
}
