package edu.bsu.cs222.fp.repertoireList.userinterface;

import edu.bsu.cs222.fp.repertoireList.datahandling.XmlSerializer;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class SaveButton {
	private Repertoire repertoireObject;

	public SaveButton(Repertoire repertoireObject) {
		this.repertoireObject = repertoireObject;
		saveChanges();
		new InformationDialog("Your changes have been saved.");
	}

	public void saveChanges() {
		XmlSerializer XmlWriter = new XmlSerializer(repertoireObject);
		try {
			XmlWriter.writeToFile("RepertoireListData/RepertoireList.xml");
		} catch (RuntimeException e) {
			new WarningDialog("Could not save list.  Try again!");
		}
	}
}