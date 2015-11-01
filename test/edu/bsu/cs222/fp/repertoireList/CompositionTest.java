package edu.bsu.cs222.fp.repertoireList;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.dataHandling.Composition;

public class CompositionTest {
	
	private String composer = "Antoine Foqueray";
	private String title = "Jupiter";
	private Composition current = Composition.byComposer(composer).withTitle(title);
	
	@Test
	public void testComposer() {
		assertEquals(composer, current.getComposer());
	}
	
	@Test
	public void testTitle() {
		assertEquals(title, current.getTitle());		
	}
}