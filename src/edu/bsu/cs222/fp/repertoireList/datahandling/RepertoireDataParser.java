package edu.bsu.cs222.fp.repertoireList.datahandling;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class RepertoireDataParser extends Parser {

	private Repertoire repertoire;
	
	public RepertoireDataParser(String xmlFile) {
		try {
			this.searchResults = readXMLDocumentFromFile(xmlFile);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		this.compositionsNodeList = getNodeListOfCompositions();
		this.compositionsList = createListOfCompositions();
		repertoire = new Repertoire(compositionsList);
	}
    
	public Repertoire getRepertoireObject() {
		return repertoire;
	}
	
	public Document getSearchResults() {
		return searchResults;
	}
	
	private Document readXMLDocumentFromFile(String inputFile) throws ParserConfigurationException, SAXException, IOException {
		InputStream sampleFileInputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(inputFile);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document sampleXML = documentBuilder.parse(sampleFileInputStream);
		return sampleXML;
	}
}