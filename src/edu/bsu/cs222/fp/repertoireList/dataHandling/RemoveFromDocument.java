package edu.bsu.cs222.fp.repertoireList.dataHandling;

import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;

public class RemoveFromDocument {
    private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
   
    private String xmlFile;
    private Document repertoireListAsDocument;
    private boolean compositionDeleted = false;
   
    public RemoveFromDocument(String xmlFile) {
        this.xmlFile = xmlFile;
        setDocument();
    }

    public Document getDocument() {
        return this.repertoireListAsDocument;
    }
   
    public boolean compositionDeleted() {
        return compositionDeleted;
    }
   
    public void removeComposition(Composition toRemove) {
        if (isDuplicate(toRemove)) {
            Node songs = getSongsNode();
            reviewCompositionForRemoval(songs, toRemove);
        }
    }
   
    private void reviewCompositionForRemoval(Node songs, Composition toRemove) {
        for (int i = 0; i < songs.getChildNodes().getLength(); i++) {
            Node current = songs.getChildNodes().item(i);
            String composer = current.getFirstChild().getTextContent();
            String title = current.getLastChild().getTextContent();
            if (toRemove.getComposer().equals(composer) && toRemove.getTitle().equals(title)) {
                current.getParentNode().removeChild(current);
            }
        }
    }

    private boolean isDuplicate(Composition composition) {
        RepertoireDataParser parser = new RepertoireDataParser(repertoireListAsDocument);
        List<Composition> compositions = parser.getRepertoireObject().getRepertoireList();
        for (Composition current : compositions) {
            if (current.equals(composition)) {
                return true;
            }
        }
        return false;
    }
   
    private void setDocument() {
        XMLToDocumentConverter converter = new XMLToDocumentConverter(xmlFile);
        Document convertedDocument = converter.getDocument();
        this.repertoireListAsDocument = convertedDocument;       
    }
   
    private Node getSongsNode() {
        XPathExpression pathway = createXPathExpression(PATH_TO_SONGS_ELEMENT);
        Node compositionsNode = null;
        try {
            compositionsNode = (Node) pathway.evaluate(repertoireListAsDocument, XPathConstants.NODE);
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