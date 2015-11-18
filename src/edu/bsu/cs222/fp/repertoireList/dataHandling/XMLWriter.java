package edu.bsu.cs222.fp.repertoireList.dataHandling;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XMLWriter {

	private final String xmlFile = "RepertoireListData/RepertoireList.xml";
	private Document repertoireListAsDocument;

	public XMLWriter(Document repertoireList) {
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
