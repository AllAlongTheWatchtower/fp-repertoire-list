package edu.bsu.cs222.fp.repertoireList.datahandling;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class XmlDeserializer {
	private Repertoire repertoireList = null;
	
	public XmlDeserializer(String file) {
		File source = new File(file);
		Serializer serializer = new Persister();
		try {
			repertoireList = serializer.read(Repertoire.class, source);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public Repertoire getRepertoireList() {
		return repertoireList;
	}
}