package edu.bsu.cs222.fp.repertoireList.userinterface;

import java.util.Optional;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

public class  RemoveCompositionButtonCell extends TableCell<Composition, Boolean> {
	public Button cellButton = new Button("Remove");
	public Composition selectedRecord;
	public Repertoire repertoireObject;

	public RemoveCompositionButtonCell(Repertoire repertoireObject) {
		this.repertoireObject = repertoireObject;
		cellButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Remove from List");
				alert.setHeaderText("Are you sure you would like to remove that piece from your Repertoire List?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					setSelectedComposition();
				    repertoireObject.removeComposition(selectedRecord);
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
}