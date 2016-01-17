package edu.bsu.cs222.fp.repertoireList;

import org.junit.Test;

import edu.bsu.cs222.fp.repertoireList.datahandling.XmlDeserializer;

public class XmlDeserializationTest {
	private XmlDeserializer XmlReader;
	
	@Test (expected = RuntimeException.class)
	public void testFileDoesNotExist() {
		XmlReader = new XmlDeserializer("thisFileDoesNotExist.xml");
	}
}
