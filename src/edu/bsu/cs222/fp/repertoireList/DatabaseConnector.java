package edu.bsu.cs222.fp.repertoireList;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DatabaseConnector {
	private Document listOfCompositions;
	
	public DatabaseConnector(String webAddress) {
		try {
			URLConnection connection = connectToDatabase(webAddress);
			Document document = readXMLDocumentFrom(connection);
			this.listOfCompositions = document;
		}
		catch (Exception e) {
			new ExceptionHandler("Problem connecting to Music Database!  Try again.");
		}
	}
	
	public Document getListOfCompositions() {
		return listOfCompositions;
	}
	
	private URLConnection connectToDatabase(String webAddress) throws IOException {
		URL url = new URL(webAddress);
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("User-Agent",
				"CS222FinalProject/0.1 (http://www.cs.bsu.edu/homepages/pvg/courses/cs222Fa15/#!/final-project; repertoire list)");
		connection.connect();
		return connection;
	}
	
	private Document readXMLDocumentFrom(URLConnection connection) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		return documentBuilder.parse(connection.getInputStream());
	}
	
	public Document readXMLSample() {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		Document sampleXML = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			sampleXML = documentBuilder.parse("sample.xml");
		} catch (Exception e) {
			new ExceptionHandler("Please try again!");
		}
		return sampleXML;
	}
}