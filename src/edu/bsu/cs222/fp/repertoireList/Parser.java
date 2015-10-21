package edu.bsu.cs222.fp.repertoireList;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {
	private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
	
	private Document searchResults;
	private NodeList compositionsNodeList;
	private ArrayList<Composition> compositionsList;
	
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
}