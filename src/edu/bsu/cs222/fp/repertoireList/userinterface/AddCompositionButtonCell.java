package edu.bsu.cs222.fp.repertoireList.userinterface;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class AddCompositionButtonCell extends TableCell<Composition, Boolean> {

	public Button cellButton = new Button("Add");
	public Composition selectedRecord;
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
			repertoireObject.addComposition(selectedRecord);
			new InformationDialog("\"" + selectedRecord.getTitle() + "\" has been added to your Repertoire List!");
		}
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