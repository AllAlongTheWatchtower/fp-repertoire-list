package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.RepertoireDataParser;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;

public class RepertoireTest {
	private Repertoire repertoire;
	private Composition testComposition = Composition.byComposer("Amadeus Mozart").withTitle("Symphony No. 40");
	
	@Before
	public void intialize() {
		XMLToDocumentConverter converter = new XMLToDocumentConverter("sampleRepertoireList.xml");
		Document document = converter.getDocument();
		RepertoireDataParser parser = new RepertoireDataParser(document);
		repertoire = parser.getRepertoireObject();
	}
	
	@Test
	public void testAddComposition() {
		int lengthBefore = repertoire.getLength();
		repertoire.addComposition(testComposition);
		int lengthAfter = repertoire.getLength();
		assertEquals(lengthBefore + 1, lengthAfter);
	}
	
	@Test
	public void testAvoidDuplicate() {
		repertoire.addComposition(testComposition);
		int lengthBefore = repertoire.getLength();
		repertoire.addComposition(testComposition);
		int lengthAfter = repertoire.getLength();
		assertEquals(lengthBefore, lengthAfter);
	}
	
	@Test
	public void testRemoveComposition() {
		repertoire.addComposition(testComposition);
		int lengthBefore = repertoire.getLength();
		repertoire.removeComposition(testComposition);
		int lengthAfter = repertoire.getLength();
		assertEquals(lengthBefore, lengthAfter + 1);
	}
}