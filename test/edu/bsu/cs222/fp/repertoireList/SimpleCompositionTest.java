package edu.bsu.cs222.fp.repertoireList;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.datatypes.SimpleComposition;

public class SimpleCompositionTest {
	
	private String composer = "Antoine Foqueray";
	private String title = "Jupiter";
	private SimpleComposition current = SimpleComposition.byComposer(composer).withTitle(title);
	private SimpleComposition other = SimpleComposition.byComposer(composer).withTitle(title);
	
	@Test
	public void testComposer() {
		assertEquals(composer, current.getComposer());
	}
	
	@Test
	public void testTitle() {
		assertEquals(title, current.getTitle());		
	}
	
	@Test
	public void testEqualsFunction() {
		assertTrue(current.equals(other));
	}
}