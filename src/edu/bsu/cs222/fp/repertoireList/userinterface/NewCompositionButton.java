
package edu.bsu.cs222.fp.repertoireList.userinterface;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewCompositionButton extends NotesPopup {
	private TextField composerInput = new TextField();
	private TextField titleInput = new TextField();
	//private Repertoire repertoireObject;

	public NewCompositionButton(Repertoire repertoireObject) {
		//this.repertoireObject = repertoireObject;
		composer = new Label("Composer:");
		title = new Label("Title:");
		selectedRecord = Composition.byComposer(composerInput.getText()).withTitle(titleInput.getText());
		createFilledStage();
		selectedRecord = Composition.byComposer(composerInput.getText()).withTitle(titleInput.getText());
		repertoireObject.addComposition(selectedRecord);
	}

	@Override
	public void addItemsToVbox() {
		vBox.getChildren().addAll(directionText, composer, composerInput, title, titleInput, yearLearnedLabel,
				inputYear, ensembleLabel, inputEnsemble, ensembleTypeLabel, ensembleComboBox, memorizedCheckBox,
				performedCheckBox, addButton, cancelButton);
	}

	@Override 
	public void performBeforeAdding() {
		

	}
}