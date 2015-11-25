package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireDataParser;
import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class RepertoireToDocumentConverterTest {
	private Repertoire testRep;
	private Document document;
	
	@Before
	public void initialize() {
		RepertoireDataParser parser = new RepertoireDataParser("sampleRepertoireList.xml");
		document = parser.getSearchResults();
		testRep = parser.getRepertoireObject();
	}

	@Test
	public void testEquivalentDocuments() {
		RepertoireToDocumentConverter converter2 = new RepertoireToDocumentConverter(testRep);
		Document duplicate = converter2.getDocument();
		/** try {
			printDocument(duplicate, System.out);
			printDocument(document, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		} */
		assertTrue(duplicate.isEqualNode(document));
	}
	
	public static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer = tf.newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	    transformer.transform(new DOMSource(doc), 
	         new StreamResult(new OutputStreamWriter(out, "UTF-8")));
	}
}