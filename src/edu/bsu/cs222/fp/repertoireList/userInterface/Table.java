package edu.bsu.cs222.fp.repertoireList.userInterface;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public abstract class Table {

	public abstract TableCell<Composition, Boolean> createButtonClass();

	public TableColumn<Composition, String> createComposerColumn() {
		TableColumn<Composition, String> composerColumn = new TableColumn<>("Composer");
		composerColumn.setCellValueFactory(new PropertyValueFactory<Composition, String>("composer"));
		composerColumn.setMinWidth(150);
		return composerColumn;
	}

	public TableColumn<Composition, String> createTitleColumn() {
		TableColumn<Composition, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<Composition, String>("title"));
		titleColumn.setMinWidth(400);
		return titleColumn;
	}

	// Creates warning when inserting any column because of it creating varargs
	// with what is sent though the parameter, yet this is the way the Oracle
	// says to insert them (Website:
	// http://docs.oracle.com/javafx/2/ui_controls/table-view.htm).
	@SuppressWarnings("unchecked")
	public TableView<Composition> createTable(ObservableList<Composition> observableListOfCompositions) {
		TableView<Composition> table = new TableView<Composition>();
		TableColumn<Composition, String> composerColumn = createComposerColumn();
		TableColumn<Composition, String> titleColumn = createTitleColumn();
		TableColumn<Composition, Boolean> actionColumn = createActionColumn();
		table.setItems(observableListOfCompositions);
		table.getColumns().addAll(composerColumn, titleColumn, actionColumn);
		table.getColumns().addListener(new ListChangeListener<Object>() {
			public boolean suspended;

			@Override
			public void onChanged(Change<?> change) {
				change.next();
				if (change.wasReplaced() && !suspended) {
					this.suspended = true;
					table.getColumns().setAll(composerColumn, titleColumn, actionColumn);
					this.suspended = false;
				}
			}
		});
		return table;
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
				return createButtonClass();
			}
		});
	}
}