package edu.bsu.cs222.fp.repertoireList;

import org.junit.Before;
import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireDataParser;
import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;

public class RepertoireDataParserTest {
	private String composer = "Antoine Foqueray";
	private String title = "Jupiter";
	private Composition current = Composition.byComposer(composer).withTitle(title);
	private String ensemble = "Ball State University Orchestra";
	private String year = "2014";
	private String ensembleType = "solo";

	
	@Before
	public void initialize() {
		current.setWasPerformed();
		current.setEnsemble(ensemble);
		current.setWasNotMemorized();
		current.setYearLearned(year);
		current.setEnsembleType(ensembleType);
	}
	
	@Test (expected = RuntimeException.class)
	public void testFlawedDocument() {
		new RepertoireDataParser("flawedDocument.xml");
	}
	
	
	@Test (expected = RuntimeException.class) 
	public void testFileMissing() {
		new RepertoireDataParser("thisDocumentDoesNotExist.xml");
	}
}
