package edu.bsu.cs222.fp.repertoireList.userInterface;

import java.util.Optional;

import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.RepertoireToDocument;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLWriter;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class SaveButton {
	private Repertoire repertoireObject;
	
	public SaveButton (Repertoire repertoireObject) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Save List");
		alert.setHeaderText("Are you sure you would like to save the changes to your Repertoire List?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			this.repertoireObject = repertoireObject;
			saveChanges();
			affirmationDialog();
		} 
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