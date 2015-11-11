package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.assertEquals;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.bsu.cs222.fp.repertoireList.dataHandling.Composition;
import edu.bsu.cs222.fp.repertoireList.dataHandling.RemoveFromDocument;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLToDocumentConverter;

public class RemoveFromDocumentTest {
	private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
	
	private XMLToDocumentConverter converter;
	private Document beforeDocument;
	private int numOfCompositionsBefore;
	private Composition testComposition;
	private RemoveFromDocument updater;
	Document afterDocument;
	
	@Before 
	public void initialize () {
		converter = new XMLToDocumentConverter("sampleRepertoireList.xml");
		beforeDocument = converter.getDocument();
		numOfCompositionsBefore = getSongsNode(beforeDocument).getChildNodes().getLength();
		testComposition = Composition.byComposer("Francois Couperin").withTitle("Les Baricades Mysterieuses");
		updater = new RemoveFromDocument("sampleRepertoireList.xml");
		updater.removeComposition(testComposition); 
		afterDocument = updater.getDocument();
	}
	
	@Test 
	public void removeNewestAddition() {
		updater.removeComposition(testComposition);
		int numOfCompositionsAfter = getSongsNode(afterDocument).getChildNodes().getLength();
		assertEquals(numOfCompositionsBefore, numOfCompositionsAfter);
	}

	private Node getSongsNode(Document document) {
		XPathExpression pathway = createXPathExpression(PATH_TO_SONGS_ELEMENT);
		Node compositionsNode = null;
		try {
			compositionsNode = (Node) pathway.evaluate(document, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			new Exception("Error!  Try Again!");
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
			new Exception("System error!  Try Again.");
		}
		return pathway;
	}
}
