package edu.bsu.cs222.fp.repertoireList.userinterface;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InformationDialog {
	public InformationDialog(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
}