package edu.bsu.cs222.fp.repertoireList.userInterface;

import java.util.Optional;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

public class  RemoveButtonCell extends TableCell<Composition, Boolean> {
	public Button cellButton = new Button("Remove");
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
			return new RemoveButtonCell(this);
		}
	}
	public RemoveButtonCell(Builder builder) {
		this.resultsTable = builder.resultsTable;
		this.repertoireObject = builder.repertoireObject;
		cellButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Remove from List");
				alert.setHeaderText("Are you sure you would like to remove that piece from your Repertoire List?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					setSelectedComposition();
				    removePiece(selectedRecord);
				} 
			}
		});
	}

	private void removePiece(Composition selectedRecord) {
		try {
			repertoireObject.removeComposition(selectedRecord);
		} catch (Exception e) {
			new WarningDialog("System error!  Try again.");
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
