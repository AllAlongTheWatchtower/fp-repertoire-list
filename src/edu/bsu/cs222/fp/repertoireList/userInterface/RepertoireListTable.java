package edu.bsu.cs222.fp.repertoireList.userInterface;

import edu.bsu.cs222.fp.repertoireList.dataHandling.Composition;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;


public class RepertoireListTable extends Table {
	private TableView<Composition> repertoireTable = new TableView<Composition>();
	private ObservableList<Composition> observableRepertoireListOfCompositions;
	
	public TableCell<Composition, Boolean> createButtonClass(){		
		return new RemovePieceButtonCell(repertoireTable);		
	}
	
	
	public RepertoireListTable(ObservableList<Composition> list){
		observableRepertoireListOfCompositions = list;
		repertoireTable = createTable(observableRepertoireListOfCompositions);
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
}