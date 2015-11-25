package edu.bsu.cs222.fp.repertoireList.datahandling;

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

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;

public abstract class Parser {
	private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
	public Document searchResults;
	public NodeList compositionsNodeList;
	public List<Composition> compositionsList = new ArrayList<Composition>();
	
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