package edu.bsu.cs222.fp.repertoireList.userInterface;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

public class SearchResultsTable extends Table {
	public TableView<Composition> resultsTable;
	public Button cellButton = new Button("Remove from List");
	public Repertoire repertoireObject;
	private ObservableList<Composition> observableListOfCompositions;
	private TableView<Composition> searchTable = new TableView<Composition>();

	public static Builder withSearchResults(ObservableList<Composition> observableListOfCompositions) {
		return new Builder(observableListOfCompositions);
	}

	public static final class Builder {
		private ObservableList<Composition> observableListOfCompositions;
		private Repertoire repertoireObject;

		public Builder(ObservableList<Composition> observableListOfCompositions) {
			this.observableListOfCompositions = observableListOfCompositions;
		}

		public SearchResultsTable withReferenceToRepertoire(Repertoire repertoireObject) {
			this.repertoireObject = repertoireObject;
			return new SearchResultsTable(this);
		}
	}

	public SearchResultsTable(Builder builder) {
		this.observableListOfCompositions = builder.observableListOfCompositions;
		this.repertoireObject = builder.repertoireObject;
		searchTable = createTable(observableListOfCompositions);
	}

	public TableView<Composition> getSearchTable() {
		return searchTable;
	}

	public TableCell<Composition, Boolean> editRepertoireButton() {
		return AddPieceButtonCell.inTable(searchTable).withRepertoire(repertoireObject);
	}

}