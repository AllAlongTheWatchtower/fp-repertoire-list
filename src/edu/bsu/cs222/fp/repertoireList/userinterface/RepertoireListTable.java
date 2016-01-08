package edu.bsu.cs222.fp.repertoireList.userinterface;

import java.util.List;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class RepertoireListTable extends Table {
	private TableView<Composition> repertoireTable = new TableView<Composition>();
	private ObservableList<Composition> observableRepertoireListOfCompositions;
	private Repertoire repertoireObject;

	public RepertoireListTable(Repertoire repertoireObject) {
		this.repertoireObject = repertoireObject;
		observableRepertoireListOfCompositions = createObservableList(repertoireObject);
		repertoireTable = createTable(observableRepertoireListOfCompositions);
		repertoireTable.getColumns().add(createNotesColumn());
		setDoubleClickAction();
	}
	
	private void setDoubleClickAction() {
		repertoireTable.setRowFactory( tv -> {
		    TableRow<Composition> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	Composition selectedIndex = row.getItem();
		        	NonButtonPopUp.withComposition(selectedIndex).withReferenceTo(repertoireObject);
		        }
		    });
		    return row ;
		});
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

	public ObservableList<Composition> getObservableRepertoireListOfCompositions() {
		return observableRepertoireListOfCompositions;
	}

	public TableCell<Composition, Boolean> editRepertoireButton() {
		return new RemoveCompositionButtonCell(repertoireObject);
	}
}