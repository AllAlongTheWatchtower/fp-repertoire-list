package edu.bsu.cs222.fp.repertoireList.datahandling;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
import org.xml.sax.SAXException;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class RepertoireToDocumentConverter {
    private static final String PATH_TO_SONGS_ELEMENT = "response/songs";
    
	private Repertoire repertoire;
	private Document repertoireAsDocument;
	
	public RepertoireToDocumentConverter(Repertoire repertoire) {
		this.repertoire = repertoire;
		convertDocument();
	}
	
	public Document getDocument() {
		return repertoireAsDocument;
	}
	
	private void convertDocument() {
		try {
			this.repertoireAsDocument = readXmlDocumentFromFile("RepertoireList.xml");
		} catch (Exception e) {
			throw new RuntimeException();
		}
		Node songs = getSongsNode(repertoireAsDocument);
		songs = getEmptySongsNode(songs);
		songs = constructSongsNode(songs);
	}
	
	private Document readXmlDocumentFromFile(String inputFile) throws ParserConfigurationException, SAXException, IOException {
		InputStream sampleFileInputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(inputFile);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document sampleXML = documentBuilder.parse(sampleFileInputStream);
		return sampleXML;
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
    	private Composition current;
    	
    	private CreateElement(Composition current) {
    		if (current instanceof Composition) {
    			this.current = (Composition) current;
    			song = repertoireAsDocument.createElement("song");
    			addComposer();
    			addTitle();
    			addNotes();
    		}
    	}
    	
    	private Element getSong() {
    		return song;
    	}
    	
        private void addComposer() {
			String artist = current.getComposer();
            Element composer = repertoireAsDocument.createElement("artist_name");   
            Text composerData = repertoireAsDocument.createTextNode(artist);
            song.appendChild(composer);
            composer.appendChild(composerData);
        }
       
        private void addTitle() {
			String piece = current.getTitle();
            Element title = repertoireAsDocument.createElement("title");
            Text titleData = repertoireAsDocument.createTextNode(piece);
            song.appendChild(title);       
            title.appendChild(titleData);
        }
        
        private void addNotes() {
        	Element notes = repertoireAsDocument.createElement("notes");
			addPerformed(notes);
			addMemorized(notes);
			addYear(notes);
			addEnsemble(notes);
			addEnsembleType(notes);   
			song.appendChild(notes);
        }

		private void addPerformed(Element notes) {
			if (current.performedSet()) {
				boolean performed = current.wasPerformed();
				if (performed) {
					notes.setAttribute("performed", "true");
				} else {
					notes.setAttribute("performed", "false");
				}
			}
        }
        
        private void addMemorized(Element notes) {
        	if (current.memorizedSet()) {
        		boolean memorized = current.wasMemorized();
        		if (memorized) {
        			notes.setAttribute("memorized", "true");
        		} else {
        			notes.setAttribute("memorized", "false");
        		}
        	}
        }
        
        private void addYear(Element notes) {
        	if (current.yearSet()) {		
             	int year = current.yearLearned();
             	String yearString = String.valueOf(year);
             	notes.setAttribute("year", yearString);
        	}
        }
        
        private void addEnsemble(Element notes) {
        	if (current.ensembleSet()) {
        		String ensemble = current.getEnsemble();
        		notes.setAttribute("ensemble", ensemble);
        	}
        }
        
        private void addEnsembleType(Element notes) {
        	if (current.ensembleTypeSet()) {
        		String ensembleType = current.getEnsembleType();
        		notes.setAttribute("ensembleType", ensembleType);
        	}
		}
    }
}