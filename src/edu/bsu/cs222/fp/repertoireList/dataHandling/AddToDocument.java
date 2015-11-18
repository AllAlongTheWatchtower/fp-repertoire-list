package edu.bsu.cs222.fp.repertoireList.dataHandling;

import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;

public class AddToDocument {
    private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
   
    private String xmlFile;
    private Document repertoireListAsDocument;
    private boolean compositionAdded = false;
   
    public AddToDocument(String xmlFile) {
        this.xmlFile = xmlFile;
        setDocument();
    }

    public Document getDocument() {
        return this.repertoireListAsDocument;
    }
   
    public void addComposition(Composition composition) {   
        if (!isDuplicate(composition)) {
            addNewComposition(composition);
            this.compositionAdded = true;
        }
    }
   
    private void addNewComposition(Composition toAdd) {
        Node songs = getSongsNode();
        Element song = repertoireListAsDocument.createElement("song");
        songs.appendChild(song);
        addComposer(song, toAdd);
        addTitle(song, toAdd);
    }
   
    public boolean compositionAdded() {
        return compositionAdded;
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
   
    private void addComposer(Element elementAdded, Composition toAdd) {
        Element composer = repertoireListAsDocument.createElement("artist_name");   
        Text composerData = repertoireListAsDocument.createTextNode(toAdd.getComposer());
        elementAdded.appendChild(composer);
        composer.appendChild(composerData);
    }
   
    private void addTitle(Element elementAdded, Composition toAdd) {
        Element title = repertoireListAsDocument.createElement("title");
        Text titleData = repertoireListAsDocument.createTextNode(toAdd.getTitle());
        elementAdded.appendChild(title);       
        title.appendChild(titleData);
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