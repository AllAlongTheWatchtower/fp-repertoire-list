package edu.bsu.cs222.fp.repertoireList.userinterface;

import java.io.File;
import java.util.Observable;
import java.util.Optional;

import edu.bsu.cs222.fp.repertoireList.datahandling.XmlDeserializer;
import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class UserInterface extends Application {
	public static void main(String[] args) {
		launch(args);
	}

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
	private TextField inputField = new TextField();
	private Button newCompositionButton = new Button("New Composition");
	private Button searchButton = new Button("Search");
	private Button saveButton = new Button("Save List");
	private TableView<Composition> repertoireTable;
	private Repertoire repertoireObject;
	private VBox tableVBox = new VBox();
	private ToolBar toolBar = new ToolBar();
	private BorderPane borderPane = new BorderPane();

	@Override
	public void start(Stage primaryStage) {
		toolBar.getItems().addAll(saveButton, newCompositionButton);
		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(searchTab, resultsTab, listTab);
		borderPane.setTop(toolBar);
		borderPane.setCenter(tabPane);
		Scene scene = new Scene(borderPane, 680, 530);
		setTabPane(tabPane);
		primaryStage.setTitle("Repertoire List Creator");
		primaryStage.setScene(scene);
		primaryStage.show();
		askUserIfTheyWouldLikeToSaveUponExist(primaryStage);
	}

	private void setTabPane(TabPane tabPane) {
		setTabsClosable();
		welcomeText.setFont(new Font("Arial", 20));
		setRepertoireListTable();
		setSearchVBox();
		setActionForButtons(tabPane);
	}

	private void setTabsClosable() {
		searchTab.setClosable(false);
		resultsTab.setClosable(false);
		listTab.setClosable(false);
	}

	private void setRepertoireListTable() {
		File source = new File("RepertoireListData/RepertoireList.xml");
		XmlDeserializer XmlReader = null;
		try {
			XmlReader = new XmlDeserializer(source);
		} catch (RuntimeException e) {
			new WarningDialog("System error: try again!");
		}
		repertoireObject = XmlReader.getRepertoireList();
		RepertoireListTable table = new RepertoireListTable(repertoireObject);
		repertoireTable = new TableView<Composition>();
		repertoireTable = table.getRepertoireTable();
		listTab.setContent(createNewVBoxWithTable(repertoireTable));
		setRepertoireObserver();
	}

	private void setRepertoireObserver() {
		repertoireObject.addObserver((Observable obj, Object arg) -> {
			refreshRepertoireTable();
		});
	}

	private VBox createNewVBoxWithTable(TableView<Composition> table) {
		tableVBox = new VBox();
		tableVBox.getChildren().add(table);
		tableVBox.setVgrow(table, Priority.ALWAYS);
		return tableVBox;
	}

	private void setSearchVBox() {
		VBox searchVBox = new VBox();
		searchVBox.setSpacing(15);
		logoLabel.setGraphic(new ImageView(logo));
		inputField.setPromptText("Search Field");
		inputField.setMaxWidth(200);
		searchVBox.getChildren().addAll(space, welcomeText, directionText, inputField, searchButton, logoLabel,
				legalText);
		searchVBox.setAlignment(Pos.CENTER);
		searchTab.setContent(searchVBox);
	}

	private void setActionForButtons(TabPane tabPane) {
		setSearchButtonAction(tabPane);
		setTheEnterKeyAction(tabPane);
		setNewCompositionButton(tabPane);
		setSaveButtonAction(tabPane);
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

	private void pressGo(TabPane tabPane) {
		if (!inputField.getText().equals("")) {
			tabPane.getSelectionModel().select(1);
			setSearchListTable();
		} else {
			new InformationDialog("Sorry! Our system is not picking that you entered anything. Please try agian.");
		}

	}

	private void setSearchListTable() {
		new TableView<Composition>();
		SearchResultsTable searchResultsTable = SearchResultsTable.withSearchedComposer(inputField.getText())
				.withReferenceToRepertoire(repertoireObject);
		resultsTab.setContent(createNewVBoxWithTable(searchResultsTable.getSearchTable()));
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

	private void setNewCompositionButton(TabPane tabPane) {
		newCompositionButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				new NewCompositionButton(repertoireObject);
			}
		});
	}

	private void setSaveButtonAction(TabPane tabPane) {
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				new SaveButton(repertoireObject);
			}
		});
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
		RepertoireListTable table = new RepertoireListTable(repertoireObject);
		repertoireTable = new TableView<Composition>();
		repertoireTable = table.getRepertoireTable();
		listTab.setContent(createNewVBoxWithTable(repertoireTable));
	}

	private void askUserIfTheyWouldLikeToSaveUponExist(Stage primaryStage) {
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent we) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				ButtonType yesButton = new ButtonType("Yes");
				alert.setTitle("Repertoire List");
				alert.setHeaderText("Would you like to save your Repertoire List?");
				alert.getButtonTypes().setAll(yesButton, new ButtonType("No"));
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == yesButton) {
					new SaveButton(repertoireObject);
				}
			}
		});
	}
}