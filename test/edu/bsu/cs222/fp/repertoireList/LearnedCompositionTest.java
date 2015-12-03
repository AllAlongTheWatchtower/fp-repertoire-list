package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.datatypes.LearnedComposition;

public class LearnedCompositionTest {

	private String composer = "Antoine Foqueray";
	private String title = "Jupiter";
	private LearnedComposition current = LearnedComposition.byComposer(composer).withTitle(title);
	private boolean wasPerformed = true;
	private String ensemble = "Ball State University Orchestra";
	private int year = 2014;
	private boolean wasMemorized = false;
	private String ensembleType = "solo";
	
	@Before
	public void initialize() {
		current.setWasPerformed();
		current.setEnsemble(ensemble);
		current.setWasNotMemorized();
		current.setYearLearned(year);
		current.setEnsembleType(ensembleType);
	}
	
	@Test 
	public void findDuplicate() {
		assertTrue(current.equals(current));
	}
	
	@Test
	public void testRetrieveEnsembleType() {
		assertEquals(current.getEnsembleType(), ensembleType);
	}
	
	@Test 
	public void testRetrieveYear() {
		assertEquals(current.yearLearned(), year);
	}
	
	@Test 
	public void testRetrieveWasMemorized() {
		assertEquals(current.wasMemorized(), wasMemorized);
	}
	
	@Test 
	public void testRetrieveWasPerformed() {
		assertEquals(current.wasPerformed(), wasPerformed);
	}
	
	@Test
	public void testRetrieveEnsemble() {
		assertEquals(current.getEnsemble(), ensemble);
	}

	@Test
	public void testRetrieveComposer() {
		assertEquals(composer, current.getComposer());
	}
	
	@Test
	public void testRetrieveTitle() {
		assertEquals(title, current.getTitle());		
	}
}