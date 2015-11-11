package edu.bsu.cs222.fp.repertoireList.userInterface;

import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.AddToDocument;
import edu.bsu.cs222.fp.repertoireList.dataHandling.Composition;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class AddPieceButtonCell extends TableCell<Composition, Boolean> {
	private TableView<Composition> resultsTable;
	private Composition selectedRecord;

	Button cellButton = new Button("Add to List");

	AddPieceButtonCell(TableView<Composition> resultsTable) {
		this.resultsTable = resultsTable;
		cellButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				setSelectedComposition();
				Composition selectedComposition = getSelectedComposition();
				addToDocument(selectedComposition);
			}
		});
	}

	public Composition getSelectedComposition() {
		return selectedRecord;
	}
  
	private void setSelectedComposition() {
		int selectdIndex = getTableRow().getIndex();
		this.selectedRecord = (Composition) resultsTable.getItems().get(selectdIndex);
	}

	private void addToDocument(Composition selectedRecord) {
		AddToDocument updater = null;
		Document updatedDocument;
		try {
			updater = new AddToDocument("RepertoireList.xml");
			updater.addComposition(selectedRecord);
			 if (updater.compositionAdded()) {
				 messageDialog(selectedRecord.getTitle() + " has been added to your Repertoire List!");
			 }
			 else {
				 messageDialog(selectedRecord.getTitle() + " is already in your Repertoire List!");
			 }
			updatedDocument = updater.getDocument();
			new XMLWriter(updatedDocument);
		} catch (Exception e) {
			new WarningDialog("System error!  Cannot add this piece to your repertoire list.  Try again.");
		}
	}

	private void messageDialog(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert!");
		alert.setHeaderText("Alert!");
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	@Override
	protected void updateItem(Boolean t, boolean empty) {
		super.updateItem(t, empty);
		if (!empty) {
			setGraphic(cellButton);
		}
	}
}