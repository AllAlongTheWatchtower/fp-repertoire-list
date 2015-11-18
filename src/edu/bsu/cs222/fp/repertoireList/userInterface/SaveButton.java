package edu.bsu.cs222.fp.repertoireList.userInterface;

import java.util.Optional;

import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.RepertoireToDocument;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLWriter;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SaveButton {
	
	public Button cellButton = new Button("Save");

	public SaveButton (Repertoire repertoireObject) {
		cellButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				RepertoireToDocument converter = new RepertoireToDocument(repertoireObject);
				Document repertoireAsDocument = converter.getDocument();
			}
		});
	}
}
