package edu.bsu.cs222.fp.repertoireList;

import java.util.ArrayList;
import org.w3c.dom.Document;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class UserInterface extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private Tab searchTab = new Tab("Search");
	private Tab resultsTab = new Tab("Search Results");
	private Tab listTab = new Tab("Repertoire List");
	private Label space = new Label("");
	private Label welcomeText = new Label("Welcome to your repertoire list maker!");
	private Label directionText = new Label("Please enter the composer whose work you would like to search for:");
	private TextField inputField = new TextField("Search Field");
	private Button searchButton = new Button("Search");
    private Image logo = new Image("echoNestLogo.png");
    private Label logoLabel = new Label();
    
	@Override
	public void start(Stage primaryStage) {
		welcomeText.setFont(new Font("Arial", 20));
		TabPane tabPane = new TabPane();
		searchTab.setClosable(false);
		resultsTab.setClosable(false);
		listTab.setClosable(false);
		tabPane.getTabs().addAll(searchTab, resultsTab, listTab);
		VBox searchVBox = new VBox();
		searchVBox.setSpacing(15);
	    logoLabel.setGraphic(new ImageView(logo));
		searchVBox.getChildren().addAll(space, welcomeText, directionText, inputField, searchButton, logoLabel);
		searchVBox.setAlignment(Pos.CENTER);
		searchTab.setContent(searchVBox);
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				tabPane.getSelectionModel().select(1);
				pressGo();
			}
		});

		inputField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent x) {
				if (x.getCode() == KeyCode.ENTER) {
					tabPane.getSelectionModel().select(1);
					pressGo();
				}
			}
		});

		Scene scene = new Scene(tabPane, 680, 530);
		primaryStage.setTitle("Repertoire List Maker");
		primaryStage.setScene(scene);
		primaryStage.show();
		setRepertoireListTable();
	}

	public void pressGo() {
		setSearchListTable();
	}

	public void setSearchListTable() {
		String composer = inputField.getText();
		URLFactory urlMaker = new URLFactory(composer);
		String url = urlMaker.getURL();
		DatabaseConnector connection = new DatabaseConnector(url);
		Document searchResults = connection.getListOfCompositions();
		TableOfCompositions searchTable = makeTable(searchResults);
		resultsTab.setContent(createNewVBoxWithTable(searchTable.getTable()));
	}

	private void setRepertoireListTable() {
		Document repertoireListResults = getRepertoireListDocument();
		TableOfCompositions repTable = makeTable(repertoireListResults);
		listTab.setContent(createNewVBoxWithTable(repTable.getTable()));
	}

	private Document getRepertoireListDocument() {
		XMLToDocumentConverter converter = new XMLToDocumentConverter("RepertoireList.xml");
		Document convertedDocument = converter.getDocument();
		return convertedDocument;
	}

	private TableOfCompositions makeTable(Document results) {
		Parser parser = new Parser(results);
		ArrayList<Composition> arrayListOfCompositions = parser.getListOfCompositions();
		ObservableList<Composition> observableListOfCompositions = FXCollections
				.observableArrayList(arrayListOfCompositions);
		TableOfCompositions tableOfCompositions = new TableOfCompositions(observableListOfCompositions);
		return tableOfCompositions;
	}

	private VBox createNewVBoxWithTable(TableView<Composition> table) {
		VBox vBox = new VBox();
		vBox.getChildren().add(table);
		return vBox;
	}
}