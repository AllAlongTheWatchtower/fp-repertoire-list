package edu.bsu.cs222.fp.repertoireList.userInterface;

import java.util.List;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

public class RepertoireListTable extends Table {
	private TableView<Composition> repertoireTable = new TableView<Composition>();
	private ObservableList<Composition> observableRepertoireListOfCompositions;
	private Repertoire repertoireObject;
	
	public TableCell<Composition, Boolean> createButtonClass() {
		return RemoveButtonCell.inTable(repertoireTable).withRepertoire(repertoireObject);
	}

	public RepertoireListTable(Repertoire repertoireObject) {
		this.repertoireObject = repertoireObject;
		observableRepertoireListOfCompositions = createObservableList(repertoireObject);
		repertoireTable = createTable(observableRepertoireListOfCompositions);
	}

	private ObservableList<Composition> createObservableList(Repertoire repertoireObject) {
		List<Composition> listOfCompositions = repertoireObject.getRepertoireList();
		ObservableList<Composition> observableListOfCompositions = FXCollections
				.observableArrayList(listOfCompositions);
		return observableListOfCompositions;
	}

	public TableView<Composition> getRepertoireTable() {
		return repertoireTable;
	}

	public void addItemToRepertoireList(Composition selectedRecord) {
		observableRepertoireListOfCompositions.add(selectedRecord);
	}

	public ObservableList<Composition> getObservableRepertoireListOfCompositions() {
		return observableRepertoireListOfCompositions;

	}
}