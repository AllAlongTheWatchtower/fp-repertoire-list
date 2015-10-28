package edu.bsu.cs222.fp.repertoireList;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLToDocumentConverter {
	private Document convertedXML;
	
	public XMLToDocumentConverter(String inputFile) {
		try {
			convertedXML = readXMLDocumentFromFile(inputFile);
		} catch (Exception e) {
			new ExceptionHandler("System error!  Please try again.");
		}
	}
	
	public Document getDocument() {
		return convertedXML;
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