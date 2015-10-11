package edu.bsu.cs222.fp.repertoireList;

import org.w3c.dom.Document;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserInterface extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	private Tab searchTab = new Tab("Search");
	private Tab resultsTab = new Tab("Search Results");
	private Label space = new Label("");
	private Label welcomeText = new Label("Welcome to your repertoire list maker!");
	private Label directionText = new Label("Please enter the composer whose work you would like to search for:");
	private TextField inputField = new TextField("Search Field");
	private Button searchButton = new Button("Search");
	
	public void start(Stage primaryStage) {
		welcomeText.setFont(new Font("Arial", 20));
		
		TabPane tabPane = new TabPane();
		searchTab.setClosable(false);
		resultsTab.setClosable(false);
		tabPane.getTabs().addAll(searchTab, resultsTab);
		
		VBox searchVBox = new VBox();
		searchVBox.setSpacing(15); 
		searchVBox.getChildren().addAll(space, welcomeText, directionText, inputField, searchButton);
		searchVBox.setAlignment(Pos.CENTER);
		searchTab.setContent(searchVBox);
		
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				pressGo();
			}
		});

		
		Scene scene = new Scene(tabPane, 680,530);
		primaryStage.setTitle("Repertoire List");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void pressGo() {
		String composer = inputField.getText();
		
		URLFactory urlMaker = new URLFactory(composer);
		String url = urlMaker.getURL();
		
		DatabaseConnector connection = new DatabaseConnector(url);
		Document results = connection.getListOfCompositions();
		
		// Parser parser = new parser(results);
	}
}