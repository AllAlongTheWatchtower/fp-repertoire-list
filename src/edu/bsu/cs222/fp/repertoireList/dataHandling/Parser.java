package edu.bsu.cs222.fp.repertoireList.dataHandling;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;

public class Parser {
	private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
	
	private Document searchResults;
	private NodeList compositionsNodeList;
	private List<Composition> compositionsList = new ArrayList<Composition>();
	private Repertoire repertoire;
	
	public Parser(Document searchResults) {
		this.searchResults = searchResults;
		this.compositionsNodeList = getNodeListOfCompositions();
		this.compositionsList = createListOfCompositions();
		repertoire = new Repertoire(compositionsList);
	}
    
	public Repertoire getRepertoire() {
		return repertoire;
	}
	
	public Document getSearchResults() {
		return searchResults;
	}
	
	private List<Composition> createListOfCompositions() {
    	for(int i = 0; i < compositionsNodeList.getLength(); i++) {
    		addComposerAtIndex(i);
    	}
    	return compositionsList;
    }
	
	private void addComposerAtIndex(int i) {
		Node currentNode = compositionsNodeList.item(i).getLastChild();
		Composition current = createComposition(currentNode);
		compositionsList.add(current);
	}
    
	private Composition createComposition(Node currentNode) {			
		String composer = currentNode.getPreviousSibling().getTextContent();
		String title = currentNode.getTextContent();
		Composition composition = Composition.byComposer(composer).withTitle(title);
		return composition;
	}
	
	private NodeList getNodeListOfCompositions() {
		Node compositions = getSongsNode();
		NodeList compositionsList = compositions.getChildNodes();
		return compositionsList;
	}
	
	private Node getSongsNode() {
    	XPathExpression pathway = createXPathExpression(PATH_TO_SONGS_ELEMENT);
    	Node compositionsNode = null;
		try {
			compositionsNode = (Node) pathway.evaluate(searchResults, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
    	return compositionsNode;
    }
    
    private XPathExpression createXPathExpression(String path) {
    	XPathFactory xpathFactory = XPathFactory.newInstance();
    	XPath xpath = xpathFactory.newXPath();
    	XPathExpression pathway = null;
		try {
			pathway = xpath.compile(path);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
    	return pathway;
    }
}