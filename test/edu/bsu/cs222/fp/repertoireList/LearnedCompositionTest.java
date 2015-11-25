package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.LearnedComposition;

public class LearnedCompositionTest {

	private String composer = "Antoine Foqueray";
	private String title = "Jupiter";
	private Composition current = Composition.byComposer(composer).withTitle(title);
	private LearnedComposition currentPlus = new LearnedComposition(current);
	private boolean wasPerformed = true;
	private String ensemble = "Ball State University Orchestra";
	private int year = 2014;
	private boolean wasMemorized = false;
	private String ensembleType = "orchestral";
	
	@Before
	public void initialize() {
		currentPlus.setWasPerformed();
		currentPlus.setEnsemble(ensemble);
		currentPlus.setWasNotMemorized();
		currentPlus.setYearLearned(year);
		currentPlus.setEnsembleType(ensembleType);
	}
	
	@Test 
	public void findDuplicate() {
		assertTrue(current.equals(current));
	}
	
	@Test
	public void testGetEnsembleType() {
		assertEquals(currentPlus.getEnsembleType(), ensembleType);
	}
	
	@Test 
	public void testGetYear() {
		assertEquals(currentPlus.yearLearned(), year);
	}
	
	@Test 
	public void testWasMemorized() {
		assertEquals(currentPlus.wasMemorized(), wasMemorized);
	}
	
	@Test 
	public void testWasPerformed() {
		assertEquals(currentPlus.wasPerformed(), wasPerformed);
	}
	
	@Test
	public void retrieveEnsemble() {
		assertEquals(currentPlus.getEnsemble(), ensemble);
	}
	
	@Test
	public void testComposition() {
		assertEquals(current, currentPlus.getComposition());
	}

	@Test
	public void testComposer() {
		assertEquals(composer, current.getComposer());
	}
	
	@Test
	public void testTitle() {
		assertEquals(title, current.getTitle());		
	}
}
