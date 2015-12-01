package edu.bsu.cs222.fp.repertoireList.userinterface;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import javafx.scene.control.Label;

public class TheAddNotesPopup extends NotesPopup {
	public void addStuff(){
		
		vBox.getChildren().addAll(directionText, composer, title, yearLearnedLabel, inputYear, ensembleLabel,
				inputEnsemble, memorizedCheckBox, performedCheckBox, searchButton);
	}
	public TheAddNotesPopup(Composition selectedRecord) {
		composer = new Label("Composer: " + selectedRecord.getComposer());
		title = new Label("Title: " + selectedRecord.getTitle());
		createFilledStage();
	}
}