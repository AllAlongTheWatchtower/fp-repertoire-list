package edu.bsu.cs222.fp.repertoireList.datahandling;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class XmlSerializer {
	private final Repertoire repertoireList;
		
	public XmlSerializer(Repertoire repertoire) {
		this.repertoireList = repertoire;
	}
	
	public void writeToFile(String file) {
		try {
			Serializer serializer = new Persister();
			File target = new File(file);
			serializer.write(repertoireList, target);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}