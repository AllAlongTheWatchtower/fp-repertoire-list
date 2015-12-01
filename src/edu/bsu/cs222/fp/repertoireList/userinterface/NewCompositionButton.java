
package edu.bsu.cs222.fp.repertoireList.userinterface;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewCompositionButton extends NotesPopup {
	private TextField composerInput = new TextField();
	private TextField titleInput = new TextField(); 
	

	public NewCompositionButton() {
		composer = new Label("Composer:");
		title = new Label("Title:");
		createFilledStage();
	}
	public void addStuff() {
		vBox.getChildren().addAll(directionText, composer,composerInput, title,titleInput, yearLearnedLabel, inputYear, ensembleLabel,
				inputEnsemble, memorizedCheckBox, performedCheckBox, searchButton);
	}
}
