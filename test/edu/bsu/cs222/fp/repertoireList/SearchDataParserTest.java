package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireDataParser;
import edu.bsu.cs222.fp.repertoireList.datahandling.SearchDataParser;
import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class SearchDataParserTest {
	private Document document;
	private Repertoire repertoire;

	@Before
	public void initialize() {
		InputStream fileInputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("sampleRepertoireList.xml");

		RepertoireDataParser parser = new RepertoireDataParser(fileInputStream);
		document = parser.getSearchResults();
		repertoire = parser.getRepertoireObject();
	}

	@Test
	public void testEqualListLength() {
		SearchDataParser parser2 = new SearchDataParser(document);
		List<Composition> list = parser2.getListOfCompositions();
		assertTrue(list.size() == repertoire.getLength());
	}

}