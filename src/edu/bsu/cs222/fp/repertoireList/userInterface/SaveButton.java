package edu.bsu.cs222.fp.repertoireList.userInterface;

import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.RepertoireToDocument;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLWriter;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;
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
		RepertoireToDocument converter = new RepertoireToDocument(repertoireObject);
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