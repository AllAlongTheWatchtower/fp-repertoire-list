package edu.bsu.cs222.fp.repertoireList.datahandling;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XmlWriter {

	private final String xmlFile;
	private Document repertoireListAsDocument;

	public XmlWriter(String xmlFile) {
		this.xmlFile = xmlFile;
	}
			
	public void writeDocument(Document repertoireList) {
		this.repertoireListAsDocument = repertoireList;
		try {
			writeChangesToXmlFile();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	private void writeChangesToXmlFile() throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(repertoireListAsDocument);
		StreamResult streamResult = new StreamResult(new File(xmlFile));
		transformer.transform(domSource, streamResult);
	}
}
