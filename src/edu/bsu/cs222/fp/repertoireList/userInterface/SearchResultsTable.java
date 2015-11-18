package edu.bsu.cs222.fp.repertoireList.userInterface;

import java.util.List;

import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.SearchDataParser;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;
import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;
import edu.bsu.cs222.fp.repertoireList.network.DatabaseConnector;
import edu.bsu.cs222.fp.repertoireList.network.URLFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class SearchResultsTable extends Table {
	public TableView<Composition> resultsTable;
	public Repertoire repertoireObject;
	private String searchedComposer;
	private ObservableList<Composition> observableListOfCompositions;
	private TableView<Composition> searchTable = new TableView<Composition>();
	private final String apiKey = "NDVFILMAVOOY8ITWS";

	public static Builder withSearchedComposer(String searched) {
		return new Builder(searched);
	}

	public static final class Builder {
		private String searched;
		private Repertoire repertoireObject;

		public Builder(String searched) {
			this.searched = searched;
		}

		public SearchResultsTable withReferenceToRepertoire(Repertoire repertoireObject) {
			this.repertoireObject = repertoireObject;
			return new SearchResultsTable(this);
		}
	}

	public SearchResultsTable(Builder builder) {
		this.searchedComposer = builder.searched;
		this.repertoireObject = builder.repertoireObject;
		observableListOfCompositions = makeObservableSearchResultsList(getSearchResults(searchedComposer));
		searchTable = createTable(observableListOfCompositions);
	}

	private Document getSearchResults(String composer) {
		DatabaseConnector connection = null;
		try {
			URLFactory urlMaker = new URLFactory(apiKey);
			String url = urlMaker.createURLForSearchTerm(composer);
			connection = new DatabaseConnector(url);
		} catch (RuntimeException e) {
			new WarningDialog("Sorry!  Could not connect with music database.  Try again!");
		}
		Document searchResults = connection.getListOfCompositions();
		return searchResults;
	}

	private ObservableList<Composition> makeObservableSearchResultsList(Document results) {
		SearchDataParser parser = new SearchDataParser(results);
		List<Composition> listOfCompositions = parser.getListOfCompositions();
		informUserIfThereAreNoSearchResults(listOfCompositions);
		ObservableList<Composition> observableListOfCompositions = FXCollections
				.observableArrayList(listOfCompositions);
		return observableListOfCompositions;
	}

	private void informUserIfThereAreNoSearchResults(List<Composition> listOfCompositions) {
		if (listOfCompositions.isEmpty()) {
			messageDialog();
		}
	}

	private void messageDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sorry!");
		alert.setHeaderText("Sorry!");
		alert.setContentText("That composer is not in our system!");
		alert.showAndWait();
	}

	public TableView<Composition> getSearchTable() {
		return searchTable;
	}

	public TableCell<Composition, Boolean> editRepertoireButton() {
		return AddPieceButtonCell.inTable(searchTable).withRepertoire(repertoireObject);
	}

}