package edu.bsu.cs222.fp.repertoireList;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class XMLWriter {
	private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
	
	private final String xmlFile = "RepertoireList.xml";
	private Document repertoireListAsDocument;
	private Composition compositionToAdd;
	
	public XMLWriter(Composition composition) {	
        setDocument(xmlFile);
        this.compositionToAdd = composition;
        addNewComposition();
        try {
			writeChangesToXmlFile();
		} catch (Exception e) {
			new Exception("Error!  Try again!");
		}
    }
 
	private void setDocument(String xmlFile) {
        XMLToDocumentConverter converter = new XMLToDocumentConverter(xmlFile);
        Document convertedDocument = converter.getDocument();
        this.repertoireListAsDocument = convertedDocument;		
	}
	
	public Document getDocument() {
		return repertoireListAsDocument;
	}
	
    private void addNewComposition() {
    	Node songs = getSongsNode();
    	Element song = repertoireListAsDocument.createElement("song");
        songs.appendChild(song);
        addComposer(song);
        addTitle(song);
    }
    
    
    private void addComposer(Element elementAdded) {
    	Element composer = repertoireListAsDocument.createElement("artist_name");    
    	Text composerData = repertoireListAsDocument.createTextNode(compositionToAdd.getComposer());
    	elementAdded.appendChild(composer);
    	composer.appendChild(composerData);
    }
    
    private void addTitle(Element elementAdded) {
    	Element title = repertoireListAsDocument.createElement("title");
    	Text titleData = repertoireListAsDocument.createTextNode(compositionToAdd.getTitle());
    	elementAdded.appendChild(title);    	
    	title.appendChild(titleData);
    }
    
    private void writeChangesToXmlFile() throws TransformerException {
    	TransformerFactory transformerFactory = TransformerFactory.newInstance();  
    	Transformer transformer = transformerFactory.newTransformer();  
    	DOMSource domSource = new DOMSource(repertoireListAsDocument);  
    	StreamResult streamResult = new StreamResult(new File("RepertoireListData/RepertoireList.xml"));  
    	transformer.transform(domSource, streamResult);  
    }
    
    public Node getSongsNode() {
    	XPathExpression pathway = createXPathExpression(PATH_TO_SONGS_ELEMENT);
    	Node compositionsNode = null;
		try {
			compositionsNode = (Node) pathway.evaluate(repertoireListAsDocument, XPathConstants.NODE);
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