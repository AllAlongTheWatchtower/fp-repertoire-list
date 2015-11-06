package edu.bsu.cs222.fp.repertoireList.userInterface;

import edu.bsu.cs222.fp.repertoireList.dataHandling.Composition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.util.Callback;

public class SearchResultsTable extends Table {
	private ObservableList<Composition> observableListOfCompositions;
	private TableView<Composition> searchTable = new TableView<Composition>();

	public SearchResultsTable(ObservableList<Composition> list) {
		observableListOfCompositions = list;
		setItemsInSearchResultTableView();
	}

	public TableView<Composition> getSearchTable() {
		return searchTable;
	}

	// Creates warning when inserting any column because of it creating varargs
	// with what is sent though the parameter, yet this is the way the Oracle
	// says to insert them (Website: http://docs.oracle.com/javafx/2/ui_controls/table-view.htm).
	@SuppressWarnings("unchecked")
	private void setItemsInSearchResultTableView() {
		TableColumn<Composition, String> composerColumn = createComposerColumn();
		TableColumn<Composition, String> titleColumn = createTitleColumn();
		TableColumn<Composition, Boolean> actionColumn = createActionColumn();
		searchTable.setItems(observableListOfCompositions);
		searchTable.getColumns().addAll(composerColumn, titleColumn, actionColumn);
		searchTable.getColumns().addListener(new ListChangeListener<Object>() {
			public boolean suspended;

			@Override
			public void onChanged(Change<?> change) {
				change.next();
				if (change.wasReplaced() && !suspended) {
					this.suspended = true;
					searchTable.getColumns().setAll(composerColumn, titleColumn, actionColumn);
					this.suspended = false;
				}
			}
		});
	}


	private TableColumn<Composition, Boolean> createActionColumn() {
		TableColumn<Composition, Boolean> actionColumn = new TableColumn<>("Action");
		actionColumn.setSortable(false);
		createButtonBooleanProperty(actionColumn);
		createButtonClass(actionColumn);
		return actionColumn;
	}

	private void createButtonClass(TableColumn<Composition, Boolean> actionColumn) {
		actionColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Composition, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Composition, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});
	}

	private void createButtonBooleanProperty(TableColumn<Composition, Boolean> actionColumn) {
		actionColumn.setCellFactory(new Callback<TableColumn<Composition, Boolean>, TableCell<Composition, Boolean>>() {
			@Override
			public TableCell<Composition, Boolean> call(TableColumn<Composition, Boolean> p) {
				return new AddPieceButtonCell(searchTable);
			}
		});
	}
}