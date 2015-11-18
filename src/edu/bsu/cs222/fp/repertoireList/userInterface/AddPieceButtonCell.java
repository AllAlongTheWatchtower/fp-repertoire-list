package edu.bsu.cs222.fp.repertoireList.userInterface;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class AddPieceButtonCell extends TableCell<Composition, Boolean> {

	public Button cellButton = new Button("Add");
	private TableView<Composition> resultsTable;
	public Composition selectedRecord;
	public Repertoire repertoireObject;

	public static Builder inTable(TableView<Composition> resultsTable) {
		return new Builder(resultsTable);
	}

	public static final class Builder {
		private TableView<Composition> resultsTable;
		private Repertoire repertoireObject;

		public Builder(TableView<Composition> resultsTable) {
			this.resultsTable = resultsTable;
		}

		public TableCell<Composition, Boolean> withRepertoire(Repertoire repertoireObject) {
			this.repertoireObject = repertoireObject;
			return new AddPieceButtonCell(this);
		}
	}

	public AddPieceButtonCell(Builder builder) {
		this.resultsTable = builder.resultsTable;
		this.repertoireObject = builder.repertoireObject;
		cellButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				setSelectedComposition();
				addToDocument();
			}
		});
	}

	private void addToDocument() {
		try {
			if (repertoireObject.isDuplicate(selectedRecord)) {
				repertoireObject.addComposition(selectedRecord);
				messageDialog("\"" + selectedRecord.getTitle() + "\" has been added to your Repertoire List!");
			} else {
				messageDialog("\"" + selectedRecord.getTitle() + "\" is already in your Repertoire List!");
			}
		} catch (Exception e) {
			new WarningDialog("System error!  Cannot add this piece to your repertoire list.  Try again.");
		}

	}

	public Composition setSelectedComposition() {
		int selectdIndex = getTableRow().getIndex();
		return (Composition) resultsTable.getItems().get(selectdIndex);
	}

	public void messageDialog(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(message);
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