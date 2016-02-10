package edu.bsu.cs222.fp.repertoireList;

import java.io.File;

import edu.bsu.cs222.fp.repertoireList.datahandling.XmlDeserializer;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class XmlSerializationTest {
	private XmlDeserializer XmlReader;
	private Repertoire repertoire;
	//private Composition test = new Composition.byComposer("Bach").withTitle("Partita");
	
	public void setup() {
		File source = new File("test-assets/sampleRepertoireList.xml");
		XmlReader = new XmlDeserializer(source);
		repertoire = XmlReader.getRepertoireList();
	}
}