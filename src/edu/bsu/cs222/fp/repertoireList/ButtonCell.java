package edu.bsu.cs222.fp.repertoireList;

import java.awt.TextField;

import org.w3c.dom.Document;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class ButtonCell extends TableCell<Composition, Boolean> {
    final Button cellButton = new Button("Add to List");
    ButtonCell(final TableView resultsTable){
         
        cellButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {        	
            	int selectdIndex = getTableRow().getIndex();
            	Composition selectedRecord = (Composition)resultsTable.getItems().get(selectdIndex);
            	DocumentUpdater updater = new DocumentUpdater(selectedRecord);
            	Document updatedDocument = updater.getDocument();
                XMLWriter writer = new XMLWriter(updatedDocument);                
                Stage stage = new Stage(); 
                stage.setScene(new Scene(new Group(new Text(25, 25, selectedRecord.getTitle() + " has been added to your Repertoire List!")))); 
                stage.show();
                
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