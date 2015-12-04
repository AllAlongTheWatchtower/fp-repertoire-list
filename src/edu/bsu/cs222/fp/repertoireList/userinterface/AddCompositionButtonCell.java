package edu.bsu.cs222.fp.repertoireList.userinterface;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AddCompositionButtonCell extends NotesPopup {

	public Button cellButton = new Button("Add");
	public Repertoire repertoireObject;

	public AddCompositionButtonCell(Repertoire repertoireObject) {
		this.repertoireObject = repertoireObject;
		
		cellButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				setSelectedComposition();
				addToDocument();
			}
		});
	}
	
	private void addToDocument() {
		if (repertoireObject.isDuplicate(selectedRecord)) {
			new InformationDialog("\"" + selectedRecord.getTitle() + "\" is already in your Repertoire List!");
		} else {
			composer = new Label("Composer: " + selectedRecord.getComposer());
			title = new Label("Title: " + selectedRecord.getTitle());
			createFilledStage();
		}
	}

	public void setSelectedComposition() {
		int selectdIndex = getTableRow().getIndex();
		Composition selected = (Composition) getTableView().getItems().get(selectdIndex);
		this.selectedRecord = Composition.byComposer(selected.getComposer()).withTitle(selected.getTitle());
	}
	
	@Override
	public void addItemsToVbox(){
		vBox.getChildren().addAll(directionText, composer, title, yearLearnedLabel, inputYear, ensembleLabel,
				ensembleComboBox, memorizedCheckBox, performedCheckBox, addButton, cancelButton);
	}

	@Override
	protected void updateItem(Boolean t, boolean empty) {
		super.updateItem(t, empty);
		if (!empty) {
			setGraphic(cellButton);
		}
	}

	@Override
	public void performBeforeClosing() {
		repertoireObject.addComposition(selectedRecord);
	}
	
	
}