package edu.bsu.cs222.fp.repertoireList;
import static org.junit.Assert.*;

import org.junit.Test;

public class CompositionTest {
	
	String composer = "Antoine Foqueray";
	String title = "Jupiter";
	
	@Test
	public void testProducesComposition() {
		assertTrue(Composition.byComposer(composer).withTitle(title) instanceof Composition);
	}
	
	Composition current = Composition.byComposer(composer).withTitle(title);
	
	@Test
	public void testComposer() {
		assertEquals(composer, current.getComposer());
	}
	
	@Test
	public void testTitle() {
		assertEquals(title, current.getTitle());		
	}
}