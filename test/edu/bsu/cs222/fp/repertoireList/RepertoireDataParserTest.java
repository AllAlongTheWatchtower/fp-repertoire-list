package edu.bsu.cs222.fp.repertoireList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireDataParser;
import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.datahandling.XmlWriter;
import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class RepertoireDataParserTest {
	private String composer = "Antoine Foqueray";
	private String title = "Jupiter";
	private Composition current = Composition.byComposer(composer).withTitle(title);
	private String ensemble = "Ball State University Orchestra";
	private String year = "2014";
	private String ensembleType = "solo";
	
	private Repertoire repertoire;
	
	@Before
	public void initialize() {
		current.setWasPerformed();
		current.setEnsemble(ensemble);
		current.setWasNotMemorized();
		current.setYearLearned(year);
		current.setEnsembleType(ensembleType);
		RepertoireDataParser parser = new RepertoireDataParser("sampleRepertoireList.xml");
		repertoire = parser.getRepertoireObject();
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
