package edu.bsu.cs222.fp.repertoireList.userinterface;

import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.datahandling.RepertoireToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.datahandling.XMLWriter;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SaveButton {
	private Repertoire repertoireObject;
	
	public SaveButton(Repertoire repertoireObject) {
		this.repertoireObject = repertoireObject;
		saveChanges();
		affirmationDialog();
	}

	public void saveChanges() {
		RepertoireToDocumentConverter converter = null;
		try {
			converter = new RepertoireToDocumentConverter(repertoireObject);
		} catch (RuntimeException e) {
			new WarningDialog ("System error!  Please try again.");
		}
		Document repertoireAsDocument = converter.getDocument();
		new XMLWriter(repertoireAsDocument);
	}

	public void affirmationDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert!");
		alert.setHeaderText("Your changes have been saved.");
		alert.showAndWait();

	}
}