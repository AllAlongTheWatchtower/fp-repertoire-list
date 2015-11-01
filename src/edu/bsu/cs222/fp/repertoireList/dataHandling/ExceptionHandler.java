package edu.bsu.cs222.fp.repertoireList.dataHandling;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExceptionHandler {
	public ExceptionHandler(String message){
		Stage newStage = new Stage();
		VBox comp = new VBox();
		Label errorMessage = new Label(message);
		comp.getChildren().add(errorMessage);

		Scene stageScene = new Scene(comp, 200, 100);
		newStage.setScene(stageScene);
		newStage.show();
	}
}
