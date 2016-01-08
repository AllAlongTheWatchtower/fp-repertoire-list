package edu.bsu.cs222.fp.repertoireList.userinterface;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;
import javafx.scene.control.Label;

public class NonButtonPopUp extends NotesPopup {
	public Repertoire repertoireObject;
	
	public static Builder withComposition(Composition selectedRecord) {
		return new Builder(selectedRecord);
	}

	public static final class Builder {
		private Composition selectedRecord;
		private Repertoire repertoireObject;

		public Builder(Composition selectedRecord) {
			this.selectedRecord = selectedRecord;
		}

		public NonButtonPopUp withReferenceTo(Repertoire repertoireObject) {
			this.repertoireObject = repertoireObject;
			return new NonButtonPopUp(this);
		}
	}

	public NonButtonPopUp(Builder builder) {
		this.repertoireObject = builder.repertoireObject;
		this.selectedRecord = builder.selectedRecord;
		addToDocument();
	}

	private void addToDocument() {
		composer = new Label("Composer: " + selectedRecord.getComposer());
		title = new Label("Title: " + selectedRecord.getTitle());
		createFilledStage();
	}

	public void setSelectedComposition() {
		int selectdIndex = getTableRow().getIndex();
		Composition selected = (Composition) getTableView().getItems().get(selectdIndex);
		this.selectedRecord = Composition.byComposer(selected.getComposer()).withTitle(selected.getTitle());
	}

	@Override
	public void addItemsToVbox() {
		vBox.getChildren().addAll(directionText, composer, title, yearLearnedLabel, inputYear, ensembleLabel,
				inputEnsemble, ensembleTypeLabel, ensembleComboBox, memorizedCheckBox, performedCheckBox, addButton,
				cancelButton);
	}
	
	@Override
	public void performOperationBeforeClosing() {
		repertoireObject.addComposition(selectedRecord);
		addNotesToComposition();
	}
}