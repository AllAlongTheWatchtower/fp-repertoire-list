package edu.bsu.cs222.fp.repertoireList.userinterface;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class  NotesButtonCell extends NotesPopup {
	public Button cellButton = new Button("Notes");
	public Composition selectedRecord;

	public NotesButtonCell() {
		cellButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				setSelectedComposition(); 
				composer = new Label("Composer: " + selectedRecord.getComposer());
				title = new Label("Title: " + selectedRecord.getTitle());
				createFilledStage();

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
		vBox.getChildren().addAll(directionText, composer, title, yearLearnedLabel, inputYear, ensembleLabel,
				inputEnsemble, memorizedCheckBox, performedCheckBox, cancelButton, addButton);
		//memorizedCheckBox.setSelected(true);
		
	}

	@Override
	public void performBeforeClosing() {
		//Add to composition
		
	}
}