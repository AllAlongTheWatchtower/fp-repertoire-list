package edu.bsu.cs222.fp.repertoireList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

class ButtonCell extends TableCell<Composition, Boolean> {
    final Button cellButton = new Button("Add to List");
     
    ButtonCell(final TableView resultsTable){
         
        cellButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {        	
            	int selectdIndex = getTableRow().getIndex();
            	Composition selectedRecord = (Composition)resultsTable.getItems().get(selectdIndex);
                XMLWriter writer = new XMLWriter(selectedRecord);
            }
        });
    }
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellButton);
        }
    }
}