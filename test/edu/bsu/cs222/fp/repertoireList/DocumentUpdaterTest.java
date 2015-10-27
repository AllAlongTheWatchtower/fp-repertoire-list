package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.bsu.cs222.fp.repertoireList.Composition.Builder;

public class DocumentUpdaterTest {
	private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
	
	XMLToDocumentConverter converter = new XMLToDocumentConverter("RepertoireList.xml");
	Document beforeDocument = converter.getDocument();
	int numOfCompositionsBefore = getSongsNode(beforeDocument).getChildNodes().getLength();
			
	Composition testComposition = new Builder().byComposer("Francois Couperin").withTitle("Les Baricades Mysterieuses");
	DocumentUpdater updater = new DocumentUpdater(testComposition);
	Document afterDocument = updater.getDocument();
	
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
