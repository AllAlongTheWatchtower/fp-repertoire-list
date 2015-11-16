package edu.bsu.cs222.fp.repertoireList.userInterface;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

public class SearchResultsTable extends Table {
	private ObservableList<Composition> observableListOfCompositions;
	private TableView<Composition> searchTable = new TableView<Composition>();

	public SearchResultsTable(ObservableList<Composition> list) {
		observableListOfCompositions = list;
		searchTable = createTable(observableListOfCompositions);
	}

	public TableView<Composition> getSearchTable() {
		return searchTable;
	}

	public TableCell<Composition, Boolean> createButtonClass() {
		return new AddPieceButtonCell(searchTable);
	}

}