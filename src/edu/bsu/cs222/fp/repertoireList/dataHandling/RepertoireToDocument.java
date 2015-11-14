package edu.bsu.cs222.fp.repertoireList.dataHandling;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;

public class RepertoireToDocument {
    private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
    
	private Repertoire repertoire;
	private Document repertoireAsDocument;
	
	public RepertoireToDocument(Repertoire repertoire) {
		this.repertoire = repertoire;
		convertDocument();
	}
	
	public Document getDocument() {
		return repertoireAsDocument;
	}
	
	private void convertDocument() {
		XMLToDocumentConverter getXml = new XMLToDocumentConverter("RepertoireList.xml");
		this.repertoireAsDocument = getXml.getDocument();
		Node songs = getSongsNode(repertoireAsDocument);
		songs = getEmptySongsNode(songs);
		songs = constructSongsNode(songs);
	}
	
	private Node constructSongsNode(Node songs) {
		for (Composition piece : repertoire) {
	        CreateElement elementFactory = new CreateElement(piece);
	        Element song = elementFactory.getSong();
	        songs.appendChild(song);
		}
		return songs;
	}
    
	private Node getEmptySongsNode(Node songs) {
		NodeList children = songs.getChildNodes();
		while (children.getLength() > 0) {
			Node child = children.item(0);
			songs.removeChild(child);
		}
		return songs;
	}
	
    private Node getSongsNode(Document convertedDocument) {
        XPathExpression pathway = createXPathExpression(PATH_TO_SONGS_ELEMENT);
        Node compositionsNode = null;
        try {
            compositionsNode = (Node) pathway.evaluate(convertedDocument, XPathConstants.NODE);
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
    
    private class CreateElement {
    	private Element song;
    	
    	private CreateElement(Composition current) {
	        song = repertoireAsDocument.createElement("song");
			String composer = current.getComposer();
			String title = current.getTitle();
			addComposer(composer);
			addTitle(title);
    	}
    	
    	private Element getSong() {
    		return song;
    	}
    	
        private void addComposer(String artist) {
            Element composer = repertoireAsDocument.createElement("artist_name");   
            Text composerData = repertoireAsDocument.createTextNode(artist);
            song.appendChild(composer);
            composer.appendChild(composerData);
        }
       
        private void addTitle(String piece) {
            Element title = repertoireAsDocument.createElement("title");
            Text titleData = repertoireAsDocument.createTextNode(piece);
            song.appendChild(title);       
            title.appendChild(titleData);
        }
    }
}