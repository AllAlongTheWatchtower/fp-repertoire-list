package edu.bsu.cs222.fp.repertoireList;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.Composition.Builder;

public class CompositionTest {
	
	String composer = "Antoine Foqueray";
	String title = "Jupiter";
	
	@Test
	public void testProducesComposition() {
		assertTrue(new Builder().byComposer(composer).withTitle(title) instanceof Composition);
	}
	
	Composition current = new Builder().byComposer(composer).withTitle(title);
	
	@Test
	public void testComposer() {
		assertEquals(composer, current.getComposer());
	}
	
	public void testTitle() {
		assertEquals(title, current.getTitle());		
	}
	
	
}
