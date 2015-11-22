package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireDataParser;
import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class RepertoireTest {
	private Repertoire repertoire;
	private Composition testComposition = Composition.byComposer("Amadeus Mozart").withTitle("Symphony No. 40");
	
	@Before
	public void intialize() {
		RepertoireDataParser parser = new RepertoireDataParser("sampleRepertoireList.xml");
		repertoire = parser.getRepertoireObject();
	}
	
	@Test
	public void testAddComposition() {
		int lengthBefore = repertoire.getLength();
		repertoire.addComposition(testComposition);
		int lengthAfter = repertoire.getLength();
		assertTrue(lengthBefore + 1==lengthAfter);
	}
	
	@Test
	public void testAvoidDuplicate() {
		repertoire.addComposition(testComposition);
		int lengthBefore = repertoire.getLength();
		repertoire.addComposition(testComposition);
		int lengthAfter = repertoire.getLength();
		assertTrue(lengthBefore==lengthAfter);
	}
	
	@Test
	public void testRemoveComposition() {
		repertoire.addComposition(testComposition);
		int lengthBefore = repertoire.getLength();
		repertoire.removeComposition(testComposition);
		int lengthAfter = repertoire.getLength();
		assertTrue(lengthBefore -1==lengthAfter);
	}
}