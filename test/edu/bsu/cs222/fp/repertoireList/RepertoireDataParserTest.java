package edu.bsu.cs222.fp.repertoireList;

import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireDataParser;

public class RepertoireDataParserTest {	
	@Test (expected = RuntimeException.class)
	public void testFlawedDocument() {
		new RepertoireDataParser("flawedDocument.xml");
	}
	
	
	@Test (expected = RuntimeException.class) 
	public void testFileMissing() {
		new RepertoireDataParser("thisDocumentDoesNotExist.xml");
	}
}