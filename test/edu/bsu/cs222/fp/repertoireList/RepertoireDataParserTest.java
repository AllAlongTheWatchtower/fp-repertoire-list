package edu.bsu.cs222.fp.repertoireList;

import java.io.InputStream;

import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireDataParser;

public class RepertoireDataParserTest {
	@Test(expected = RuntimeException.class)
	public void testFlawedDocument() {
		InputStream fileInputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("flawedDocument.xml");

		new RepertoireDataParser(fileInputStream);
	}

	@Test(expected = RuntimeException.class)
	public void testFileMissing() {
		InputStream fileInputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("thisDocumentDoesNotExist.xml");
		new RepertoireDataParser(fileInputStream);
	}
}