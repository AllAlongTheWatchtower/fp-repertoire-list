package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.datahandling.XmlDeserializer;
import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class RepertoireTest {
	private Repertoire repertoire;
	private Composition testComposition = Composition.byComposer("Amadeus Mozart").withTitle("Symphony No. 40");
	
	@Before
	public void setup() {
		File source = new File("test-assets/sampleRepertoireList.xml");
		XmlDeserializer XmlReader = new XmlDeserializer(source);
		repertoire = XmlReader.getRepertoireList();
	}
	
	@Test
	public void testAddComposition() {
		int lengthBefore = repertoire.getLength();
		repertoire.addComposition(testComposition);
		int lengthAfter = repertoire.getLength();
		assertEquals(lengthBefore+1, lengthAfter);
	}
	
	@Test 
	public void testDuplicateFunction() {
		repertoire.addComposition(testComposition);
		assertTrue(repertoire.isDuplicate(testComposition));
	}
	
	@Test
	public void testTryToAddDuplicate() {
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
		assertTrue(lengthBefore-1 == lengthAfter);
	}
}