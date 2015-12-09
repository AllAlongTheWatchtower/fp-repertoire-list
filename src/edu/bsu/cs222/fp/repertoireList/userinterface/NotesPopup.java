package edu.bsu.cs222.fp.repertoireList.userinterface;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class NotesPopup extends TableCell<Composition, Boolean> {

	public Composition selectedRecord;
	public Button addButton = new Button("Add");
	public Button cancelButton = new Button("Cancel");
	public Label directionText = new Label("Please enter your notes");
	public Label composer;
	public Label title;
	public Label yearLearnedLabel = new Label("The year you learned the composition:");
	public Label ensembleTypeLabel = new Label(" The ensemble type: ");
	public Label ensembleLabel = new Label(" The ensemble in which you learned it:");
	public TextField inputEnsemble = new TextField();
	public CheckBox memorizedCheckBox = new CheckBox("Memorized");
	public CheckBox performedCheckBox = new CheckBox("Performed");
	public TextField inputYear = new TextField();
	public ComboBox<String> ensembleComboBox = createComboBox();
	public VBox vBox = new VBox();

	public abstract void addItemsToVbox();

	private ComboBox<String> createComboBox() {
		ComboBox<String> ensembleComboBox = new ComboBox<String>();
		ensembleComboBox.getItems().addAll("solo", "ensemble", "chamber", "orchestra", "opera", "other");
		return ensembleComboBox;
	}

	public void createFilledStage() {
		vBox = new VBox();
		Stage stage = new Stage();
		directionText.setFont(new Font("Arial", 20));
		vBox.setSpacing(15);
		addItemsToVbox();
		vBox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vBox);
		stage.setScene(scene);
		stage.setTitle("Notes");
		stage.show();
		setAddButtonAction(stage);
		setCancelButtonAction(stage);
	}

	private void setAddButtonAction(Stage stage) {
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				addNotesToComposition();
				stage.close();
			}
		});
	}

	public void addNotesToComposition() {
		selectedRecord.setYearLearned(inputYear.getText());
		selectedRecord.setEnsemble(inputEnsemble.getText());
		addEnsembleType();
		addMemorized();
		addPerformed();
	}

	private void addEnsembleType() {
		if (ensembleComboBox.getValue() != null) {
			selectedRecord.setEnsembleType(ensembleComboBox.getValue());
		}
	}

	private void addMemorized() {
		if (memorizedCheckBox.isSelected()) {
			selectedRecord.setWasMemorized();
		} else {
			selectedRecord.setWasNotMemorized();
		}
	}

	private void addPerformed() {
		if (performedCheckBox.isSelected()) {
			selectedRecord.setWasPerformed();
		} else {
			selectedRecord.setWasNotPerformed();
		}
	}

	private void setCancelButtonAction(Stage stage) {
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				stage.close();
			}
		});
	}
}