package edu.bsu.cs222.fp.repertoireList.userinterface;

import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.datahandling.XmlWriter;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class SaveButton {
	private Repertoire repertoireObject;

	public SaveButton(Repertoire repertoireObject) {
		this.repertoireObject = repertoireObject;
		saveChanges();
		new InformationDialog("Your changes have been saved.");
	}

	public void saveChanges() {
		RepertoireToDocumentConverter converter = null;
		try {
			converter = new RepertoireToDocumentConverter(repertoireObject);
		} catch (RuntimeException e) {
			new WarningDialog("System error!  Please try again.");
		}
		Document repertoireAsDocument = converter.getDocument();
		XmlWriter writer = new XmlWriter("RepertoireListData/RepertoireList.xml");
		writer.writeDocument(repertoireAsDocument);
	} 

}