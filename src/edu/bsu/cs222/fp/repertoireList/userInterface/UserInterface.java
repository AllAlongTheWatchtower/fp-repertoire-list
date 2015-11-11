package edu.bsu.cs222.fp.repertoireList.userInterface;

import java.util.ArrayList;
import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.Composition;
import edu.bsu.cs222.fp.repertoireList.dataHandling.Parser;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.network.DatabaseConnector;
import edu.bsu.cs222.fp.repertoireList.network.URLFactory;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	
	private final String apiKey = "NDVFILMAVOOY8ITWS";
	private Tab searchTab = new Tab("Search");
	private Tab resultsTab = new Tab("Search Results");
	private Tab listTab = new Tab("Repertoire List");
	private Label space = new Label("");
	private Label welcomeText = new Label("Welcome to your Repertoire List Creator!");
	private Label directionText = new Label("Please enter the composer whose work you would like to search for:");
	private Image logo = new Image("echoNestLogo.png");
	private Label logoLabel = new Label();
	private Label legalText = new Label(
			"Data retrieved using The Echo Nest API\nWebsite: http://the.echonest.com/\nA special thanks to The Echo Nest!");
	private TextField inputField = new TextField("Search Field");
	private Button searchButton = new Button("Search");
	private TableView<Composition> repertoireTable;

	@Override
	public void start(Stage primaryStage) {
		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(searchTab, resultsTab, listTab);
		setWindow(tabPane);
		Scene scene = new Scene(tabPane, 680, 530);
		primaryStage.setTitle("Repertoire List Creator");
		primaryStage.setScene(scene);
		primaryStage.show();
	} 

	private void setWindow(TabPane tabPane) {
		setTabsClosable();
		welcomeText.setFont(new Font("Arial", 20));
		setRepertoireListTable();
		setSearchVBox();
		setActionForButtons(tabPane);
	}

	private ObservableList<Composition> makeObservableList(Document results) {
		Parser parser = new Parser(results);
		ArrayList<Composition> arrayListOfCompositions = parser.getListOfCompositions();
		informUserIfThereAreNoSearchResults(arrayListOfCompositions);
		ObservableList<Composition> observableListOfCompositions = FXCollections
				.observableArrayList(arrayListOfCompositions);
		return observableListOfCompositions;
	}

	private void informUserIfThereAreNoSearchResults(ArrayList<Composition> arrayListOfCompositions) {
		if (arrayListOfCompositions.isEmpty()) {
			messageDialog();
		}
	}
	
	private void messageDialog () {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sorry!");
		alert.setHeaderText("Sorry!");
		alert.setContentText("That composer is not in our system!");
		alert.showAndWait();
	}

	private Document getRepertoireListDocument() {
		XMLToDocumentConverter converter = null;
		try {
			converter = new XMLToDocumentConverter("RepertoireList.xml");
		} catch (RuntimeException e) {
			new WarningDialog("System error: try again!");
		}
		Document convertedDocument = converter.getDocument();
		return convertedDocument;
	}

	private void setTabsClosable() {
		searchTab.setClosable(false);
		resultsTab.setClosable(false);
		listTab.setClosable(false);
	}

	private void setRepertoireListTable() {
		RepertoireListTable r = new RepertoireListTable(makeObservableList(getRepertoireListDocument()));
		repertoireTable = new TableView<Composition>();
		repertoireTable = r.getRepertoireTable();
		listTab.setContent(createNewVBoxWithTable(repertoireTable));
	}

	private VBox createNewVBoxWithTable(TableView<Composition> table) {
		VBox vBox = new VBox();
		vBox.getChildren().add(table);
		return vBox;
	}

	private void setSearchVBox() {
		VBox searchVBox = new VBox();
		searchVBox.setSpacing(15);
		logoLabel.setGraphic(new ImageView(logo));
		searchVBox.getChildren().addAll(space, welcomeText, directionText, inputField, searchButton, logoLabel,
				legalText);
		searchVBox.setAlignment(Pos.CENTER);
		searchTab.setContent(searchVBox);
	}

	private void setActionForButtons(TabPane tabPane) {
		setSearchButtonAction(tabPane);
		setTheEnterKeyAction(tabPane);
		setTheRepertoireListButton(tabPane);
	}

	private void setSearchButtonAction(TabPane tabPane) {
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				pressGo(tabPane);
			}
		});
	}

	private void setTheEnterKeyAction(TabPane tabPane) {
		inputField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent x) {
				if (x.getCode() == KeyCode.ENTER) {
					pressGo(tabPane);
				}
			}
		});
	}

	private void pressGo(TabPane tabPane) {
		tabPane.getSelectionModel().select(1);
		setSearchListTable();
	}

	private void setSearchListTable() {
		new TableView<Composition>();
		ObservableList<Composition> observableListOfCompositions = makeObservableList(getSearchResults());
		SearchResultsTable srt = new SearchResultsTable(observableListOfCompositions);
		resultsTab.setContent(createNewVBoxWithTable(srt.getSearchTable()));
	}

	private Document getSearchResults() {
		String composer  = inputField.getText();
		DatabaseConnector connection = null;
		try {
			URLFactory urlMaker = new URLFactory(apiKey);
			String url = urlMaker.createURLForSearchTerm(composer);
			connection = new DatabaseConnector(url);
		} catch (RuntimeException e) {
			new WarningDialog("Sorry!  Could not connect with music database.  Try again!");
		}
		Document searchResults = connection.getListOfCompositions();
		return searchResults;
	}

	private void setTheRepertoireListButton(TabPane tabPane) {
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

			@Override
			public void changed(ObservableValue<? extends Tab> arg0, Tab arg1, Tab arg2) {
				if (arg2 == listTab) {
					refreshRepertoireTable();
				}
			}
		});
	}

	private void refreshRepertoireTable() {
		repertoireTable.setItems(null);
		setRepertoireListTable();
	}
}