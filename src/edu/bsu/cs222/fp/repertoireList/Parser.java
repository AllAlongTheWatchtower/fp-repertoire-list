package edu.bsu.cs222.fp.repertoireList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
	
	private Document searchResults;
	private NodeList compositionsNodeList;
	private ArrayList<Composition> compositionsList;
	
	// empty constructor to be used with testing
	public Parser () {
		try {
			this.searchResults = readXMLDocumentFromSample();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.compositionsNodeList = getNodeListOfCompositions();
		this.compositionsList = createListOfCompositions();
	}
	
	public Parser(Document searchResults) {
		this.searchResults = searchResults;
		this.compositionsNodeList = getNodeListOfCompositions();
		this.compositionsList = createListOfCompositions();
	}
    
	public ArrayList<Composition> getListOfCompositions() {
		return compositionsList;
	}
	
	public Document getSearchResults() {
		return searchResults;
	}
	
	public ArrayList<Composition> createListOfCompositions() {
    	compositionsList = new ArrayList<Composition>();
    	for(int i = 0; i < compositionsNodeList.getLength(); i++) {
    		Node songNode = compositionsNodeList.item(i);
    		Composition current = createComposition(songNode);
    		compositionsList.add(current);
    	}
    	return compositionsList;
    }
    
	public Composition createComposition(Node songData) {
		Node currentNode = songData.getLastChild();
		String title = currentNode.getTextContent();
		currentNode = currentNode.getPreviousSibling();
		String composer = currentNode.getTextContent();
		Composition nextComposition = new Composition.Builder().byComposer(composer).withTitle(title);
		return nextComposition;
	}
	
	public NodeList getNodeListOfCompositions() {
		Node compositions = getSongsNode();
		NodeList compositionsList = compositions.getChildNodes();
		return compositionsList;
	}
	
    public Node getSongsNode() {
    	XPathExpression pathway = createXPathExpression(PATH_TO_SONGS_ELEMENT);
    	Node compositionsNode = null;
		try {
			compositionsNode = (Node) pathway.evaluate(searchResults, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			new Exception("Error!  Try Again!");
		}
    	return compositionsNode;
    }
    
    public XPathExpression createXPathExpression(String path) {
    	XPathFactory xpathFactory = XPathFactory.newInstance();
    	XPath xpath = xpathFactory.newXPath();
    	XPathExpression pathway = null;
		try {
			pathway = xpath.compile(path);
		} catch (XPathExpressionException e) {
			new Exception("System error!  Try Again.");
		}
    	return pathway;
    }
    
    // to be used with unit tests
	public Document readXMLDocumentFromSample() throws ParserConfigurationException, SAXException, IOException {
		InputStream sampleFileInputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("sample.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document sampleXML = documentBuilder.parse(sampleFileInputStream);
		return sampleXML;
	}
}