package edu.bsu.cs222.fp.repertoireList.userInterface;

import edu.bsu.cs222.fp.repertoireList.dataHandling.Composition;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RepertoireListTable extends Table {
	private TableView<Composition> repertoireTable = new TableView<Composition>();
	private ObservableList<Composition> observableRepertoireListOfCompositions;

	
	
	public RepertoireListTable(ObservableList<Composition> list){
		observableRepertoireListOfCompositions = list;
		setItemsInRepertoireTableView();
	}
	
	public TableView<Composition> getRepertoireTable(){
		return repertoireTable;
	}
	
	public void addItemToRepertoireList(Composition selectedRecord){
		observableRepertoireListOfCompositions.add(selectedRecord);
	}
	
	public ObservableList<Composition> getObservableRepertoireListOfCompositions(){
		return observableRepertoireListOfCompositions;
		
	}
	
	
	// Creates warning when inserting any column because of it creating varargs
	// with what is sent though the parameter, yet this is the way the Oracle
	// says to insert them (Website: http://docs.oracle.com/javafx/2/ui_controls/table-view.htm).
	@SuppressWarnings("unchecked")
	public void setItemsInRepertoireTableView() {
		TableColumn<Composition, String> composerColumn = createComposerColumn();
		TableColumn<Composition, String> titleColumn = createTitleColumn();
		repertoireTable.setItems(observableRepertoireListOfCompositions);
		repertoireTable.getColumns().addAll(composerColumn, titleColumn);
		repertoireTable.getColumns().addListener(new ListChangeListener<Object>() {
			public boolean suspended;

			@Override
			public void onChanged(Change<?> change) {
				change.next();
				if (change.wasReplaced() && !suspended) {
					this.suspended = true;
					repertoireTable.getColumns().setAll(composerColumn, titleColumn);
					this.suspended = false;
				}
			}
		});
	}
	
}