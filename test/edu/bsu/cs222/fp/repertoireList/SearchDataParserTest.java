package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import edu.bsu.cs222.fp.repertoireList.datahandling.SearchDataParser;
import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class SearchDataParserTest {
	private Document document;
	private Repertoire repertoire;

	@Before
	public void setup() {
		InputStream fileInputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("sampleSearchResults.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(fileInputStream);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testEqualListLength() {
		SearchDataParser parser2 = new SearchDataParser(document);
		List<Composition> list = parser2.getListOfCompositions();
		assertTrue(list.size() == repertoire.getLength());
	}
}