package edu.bsu.cs222.fp.repertoireList;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class TableOfCompositions {
	
	private TableView resultsTable;
	private ObservableList<Composition> observableCompositionList;
	
	public  TableOfCompositions(ObservableList<Composition> observableCompositionList) {		
		this.resultsTable = new TableView();
		this.observableCompositionList = observableCompositionList;
		this.resultsTable = setCorrectResultTableView();	
	}
	
	public TableView getTable(){
		return this.resultsTable;
	}

	private TableView<Composition> setCorrectResultTableView() {
		TableView<Composition> table = new TableView<Composition>();
		TableColumn<Composition, String> composerColumn = createComposerColumn();
		TableColumn<Composition, String> titleColumn = createTitleColumn();		
		TableColumn actionColumn = createActionColumn();
		table.setItems(observableCompositionList);
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
	
	private TableColumn createActionColumn(){
		TableColumn actionColumn = new TableColumn<>("Action");
        actionColumn.setSortable(false);
        actionColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Composition, Boolean>, 
                ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Composition, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        actionColumn.setCellFactory(
                new Callback<TableColumn<Composition, Boolean>, TableCell<Composition, Boolean>>() {
 
            @Override
            public TableCell<Composition, Boolean> call(TableColumn<Composition, Boolean> p) {
            	return new ButtonCell(resultsTable);
            }
         
        });
		return actionColumn;        
	}
	
	
	private TableColumn<Composition, String> createComposerColumn() {
		TableColumn<Composition, String> composerColumn = new TableColumn<>("Composer");
		composerColumn.setCellValueFactory(new PropertyValueFactory<Composition, String>("composer"));
		composerColumn.setMinWidth(150);
		return composerColumn;
	}

	private TableColumn<Composition, String> createTitleColumn() {
		TableColumn<Composition, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<Composition, String>("title"));
		titleColumn.setMinWidth(400);
		return titleColumn;
	}
}