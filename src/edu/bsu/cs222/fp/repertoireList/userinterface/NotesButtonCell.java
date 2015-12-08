package edu.bsu.cs222.fp.repertoireList.userinterface;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NotesButtonCell extends NotesPopup {
	public Button cellButton = new Button("Notes");

	public NotesButtonCell() {
		cellButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				setSelectedComposition();
				prepopulatePopup(); 
				createFilledStage();

			}

			private void prepopulatePopup() {
				composer = new Label("Composer: " + selectedRecord.getComposer());
				title = new Label("Title: " + selectedRecord.getTitle());
				ensembleComboBox.getSelectionModel().select(selectedRecord.getEnsembleType());
				performedCheckBox.setSelected(selectedRecord.wasPerformed());
				memorizedCheckBox.setSelected(selectedRecord.wasMemorized());
				if (selectedRecord.yearSet()) {
					inputYear = new TextField(selectedRecord.yearLearned());
				}
				if (selectedRecord.ensembleSet()) {
					inputEnsemble = new TextField(selectedRecord.getEnsemble());
				}
			}
		});
	}

	public void setSelectedComposition() {
		int selectdIndex = getTableRow().getIndex();
		this.selectedRecord = (Composition) getTableView().getItems().get(selectdIndex);
	}

	@Override
	protected void updateItem(Boolean t, boolean empty) {
		super.updateItem(t, empty);
		if (!empty) {
			setGraphic(cellButton);
		}
	}

	@Override
	public void addItemsToVbox() {
		vBox.getChildren().addAll(directionText, composer, title, yearLearnedLabel, inputYear, ensembleLabel, inputEnsemble,
				ensembleTypeLabel, ensembleComboBox, memorizedCheckBox, performedCheckBox, addButton, cancelButton);

	}

	@Override
	public void performBeforeAdding() {

	}
}