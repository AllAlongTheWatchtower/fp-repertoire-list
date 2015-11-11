package edu.bsu.cs222.fp.repertoireList.userInterface;

import java.util.Optional;

import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.Composition;
import edu.bsu.cs222.fp.repertoireList.dataHandling.RemoveFromDocument;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class RemovePieceButtonCell extends TableCell<Composition, Boolean> {
	private TableView<Composition> resultsTable;
	private Composition selectedRecord;

	Button cellButton = new Button("Remove from List");

	RemovePieceButtonCell(TableView<Composition> resultsTable) {
		this.resultsTable = resultsTable;
		cellButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Remove from List");
				alert.setHeaderText("Are you sure you would like to delete that piece from your Repertoire List?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
				    System.out.println("You clicked 'Ok'");
				} 
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

	private void updateDocument(Composition selectedRecord) {
		RemoveFromDocument updater;
		Document updatedDocument;
		try {
			updater = new RemoveFromDocument("RepertoireList.xml");
			updater.removeComposition(selectedRecord);
			updatedDocument = updater.getDocument();
			new XMLWriter(updatedDocument);
		} catch (Exception e) {
			new WarningDialog("System error!  Cannot add this piece to your repertoire list.  Try again.");
		}
	}

	@Override
	protected void updateItem(Boolean t, boolean empty) {
		super.updateItem(t, empty);
		if (!empty) {
			setGraphic(cellButton);
		}
	}
}
