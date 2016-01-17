package edu.bsu.cs222.fp.repertoireList;

import edu.bsu.cs222.fp.repertoireList.datahandling.XmlDeserializer;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class XmlSerializationTest {
	private XmlDeserializer XmlReader;
	private Repertoire repertoire;
	
	public void setup() {
		XmlReader = new XmlDeserializer("test-assets/sampleRepertoireList.xml");
		repertoire = XmlReader.getRepertoireList();
	}
}