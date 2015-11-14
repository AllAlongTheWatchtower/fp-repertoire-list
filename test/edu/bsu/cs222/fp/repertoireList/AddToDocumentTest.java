package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.bsu.cs222.fp.repertoireList.dataHandling.AddToDocument;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;

public class AddToDocumentTest {
	private static final String PATH_TO_SONGS_ELEMENT = "response/songs";

	private XMLToDocumentConverter converter;
	private Document beforeDocument;
	private int numOfCompositionsBefore;
	private Composition testComposition;
	private AddToDocument updater;
	Document afterDocument;
	
	@Before 
	public void initialize () {
		converter = new XMLToDocumentConverter("sampleRepertoireList.xml");
		beforeDocument = converter.getDocument();
		numOfCompositionsBefore = getSongsNode(beforeDocument).getChildNodes().getLength();
		testComposition = Composition.byComposer("Francois Couperin").withTitle("Les Baricades Mysterieuses");
		updater = new AddToDocument("sampleRepertoireList.xml");
		updater.addComposition(testComposition); 
		afterDocument = updater.getDocument();
	}
	
	@Test 
	public void refuseDuplicate() {
		updater.addComposition(testComposition);
		Document noDuplicate = updater.getDocument();
		int numOfCompositionsAfter = getSongsNode(afterDocument).getChildNodes().getLength();
		int numOfCompositionsAfterDuplicate = getSongsNode(noDuplicate).getChildNodes().getLength();
		assertEquals(numOfCompositionsAfter, numOfCompositionsAfterDuplicate);
	}
	
	@Test
	public void testReturnsDocument() {
		assertNotNull(afterDocument.getDocumentElement());
	}

	@Test
	public void getNewestAddition() {
		int numOfCompositionsAfter = getSongsNode(afterDocument).getChildNodes().getLength();
		assertEquals(numOfCompositionsBefore + 1, numOfCompositionsAfter);
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