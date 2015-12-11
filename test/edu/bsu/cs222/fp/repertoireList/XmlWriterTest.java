
package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireDataParser;
import edu.bsu.cs222.fp.repertoireList.datahandling.XmlWriter;

public class XmlWriterTest {
	private Document productCodeDocument;
	private InputStream fileInputStream;
	
	@Before
	public void initialize() {
		fileInputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("sampleRepertoireList.xml");
		RepertoireDataParser productParser = new RepertoireDataParser(fileInputStream);
		productCodeDocument = productParser.getSearchResults();
	}

	@Test
	public void testWritesToXml() {
		XmlWriter writer = new XmlWriter("sampleRepertoireList.xml");
		writer.writeDocument(productCodeDocument);
		InputStream fileInputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("sampleRepertoireList.xml");
		RepertoireDataParser testParser = new RepertoireDataParser(fileInputStream);
		Document testDocument = testParser.getSearchResults();
		assertTrue(productCodeDocument.isEqualNode(testDocument));
	}
}