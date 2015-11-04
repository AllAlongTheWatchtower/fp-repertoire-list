package edu.bsu.cs222.fp.repertoireList.userInterface;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WarningDialog {
	public WarningDialog(String message){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Error!");
		alert.setHeaderText("Error!");
		alert.setContentText(message);
		alert.showAndWait();
	}
}
