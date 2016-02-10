package edu.bsu.cs222.fp.repertoireList;

import java.io.File;

import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.datahandling.XmlDeserializer;

public class XmlDeserializationTest {
	private XmlDeserializer XmlReader;
	
	@Test (expected = RuntimeException.class)
	public void testFileDoesNotExist() {
		File source = new File("thisFileDoesNotExist.xml");
		XmlReader = new XmlDeserializer(source);
	}
	
	
}
