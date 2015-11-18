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

public abstract class Parser {
	private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
	public Document searchResults;
	public NodeList compositionsNodeList;
	public List<Composition> compositionsList = new ArrayList<Composition>();

	
	
	public List<Composition> createListOfCompositions() {
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
	
	public NodeList getNodeListOfCompositions() {
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
			throw new RuntimeException();
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
			throw new RuntimeException();
		}
    	return pathway;
    }
}