package edu.bsu.cs222.fp.repertoireList.userInterface;

import java.util.ArrayList;

import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataHandling.Composition;
import edu.bsu.cs222.fp.repertoireList.dataHandling.DocumentUpdater;
import edu.bsu.cs222.fp.repertoireList.dataHandling.Parser;
import edu.bsu.cs222.fp.repertoireList.dataHandling.WarningDialog;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLToDocumentConverter;
import edu.bsu.cs222.fp.repertoireList.dataHandling.XMLWriter;
import edu.bsu.cs222.fp.repertoireList.network.DatabaseConnector;
import edu.bsu.cs222.fp.repertoireList.network.URLFactory;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

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
	private TableView<Composition> repertoireTable = new TableView<Composition>();
	private TableView<Composition> searchTable = new TableView<Composition>();
	private ObservableList<Composition> observableRepertoireListOfCompositions = makeObservableList(
			getRepertoireListDocument());

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
			new WarningDialog("Sorry, that composer is not in our system!");
		}
	}

	private Document getRepertoireListDocument() {
		XMLToDocumentConverter converter = new XMLToDocumentConverter("RepertoireList.xml");
		Document convertedDocument = converter.getDocument();
		return convertedDocument;
	}

	private void setTabsClosable() {
		searchTab.setClosable(false);
		resultsTab.setClosable(false);
		listTab.setClosable(false);
	}

	private void setRepertoireListTable() {
		setItemsInRepertoireTableView();
		listTab.setContent(createNewVBoxWithTable(repertoireTable));
	}

	// Creates warning when inserting any column because of it creating varargs
	// with what is sent though the parameter, yet this is the way the Oracle
	// says to insert them.
	@SuppressWarnings("unchecked")
	private void setItemsInRepertoireTableView() {
		TableColumn<Composition, String> composerColumn = createComposerColumn();
		TableColumn<Composition, String> titleColumn = createTitleColumn();
		repertoireTable.setItems(observableRepertoireListOfCompositions);
		repertoireTable.getColumns().addAll(composerColumn, titleColumn);
		repertoireTable.getColumns().addListener(new ListChangeListener<Object>() {
			public boolean suspended;

			@Override
			public void onChanged(Change<?> change) {
				change.next();
				if (change.wasReplaced() && !suspended) {
					this.suspended = true;
					repertoireTable.getColumns().setAll(composerColumn, titleColumn);
					this.suspended = false;
				}
			}
		});
	}

	private TableColumn<Composition, String> createComposerColumn() {
		TableColumn<Composition, String> composerColumn = new TableColumn<>("Composer");
		composerColumn.setCellValueFactory(new PropertyValueFactory<Composition, String>("composer"));
		composerColumn.setMinWidth(150);
		return composerColumn;
	}

	private TableColumn<Composition, String> createTitleColumn() {
		TableColumn<Composition, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<Composition, String>("title"));
		titleColumn.setMinWidth(400);
		return titleColumn;
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
		searchTable = new TableView<Composition>();
		ObservableList<Composition> observableListOfCompositions = makeObservableList(getSearchResults());
		setItemsInSearchResultTableView(observableListOfCompositions);
		resultsTab.setContent(createNewVBoxWithTable(searchTable));
	}

	private Document getSearchResults() {
		String composer = inputField.getText();
		DatabaseConnector connection;
		Document searchResults = null;
		try {
			URLFactory urlMaker = new URLFactory(apiKey);
			String url = urlMaker.createURLForSearchTerm(composer);
			connection = new DatabaseConnector(url);
			searchResults = connection.getListOfCompositions();
		} catch (RuntimeException e) {
			new WarningDialog("Could not connect to the database.  Please check your network connect!");
		}
		return searchResults;
	}
	
	// Creates warning when inserting any column because of it creating varargs
	// with what is sent though the parameter, yet this is the way the Oracle
	// says to insert them.
	@SuppressWarnings("unchecked")
	private void setItemsInSearchResultTableView(ObservableList<Composition> observableCompositionList) {
		TableColumn<Composition, String> composerColumn = createComposerColumn();
		TableColumn<Composition, String> titleColumn = createTitleColumn();
		TableColumn<Composition, Boolean> actionColumn = createActionColumn();
		searchTable.setItems(observableCompositionList);
		searchTable.getColumns().addAll(composerColumn, titleColumn, actionColumn);
		searchTable.getColumns().addListener(new ListChangeListener<Object>() {
			public boolean suspended;

			@Override
			public void onChanged(Change<?> change) {
				change.next();
				if (change.wasReplaced() && !suspended) {
					this.suspended = true;
					searchTable.getColumns().setAll(composerColumn, titleColumn, actionColumn);
					this.suspended = false;
				}
			}
		});
	}

	private TableColumn<Composition, Boolean> createActionColumn() {
		TableColumn<Composition, Boolean> actionColumn = new TableColumn<>("Action");
		actionColumn.setSortable(false);
		createButtonBooleanProperty(actionColumn);
		createButtonClass(actionColumn);
		return actionColumn;
	}

	private void createButtonClass(TableColumn<Composition, Boolean> actionColumn) {
		actionColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Composition, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Composition, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});
	}

	private void createButtonBooleanProperty(TableColumn<Composition, Boolean> actionColumn) {
		actionColumn.setCellFactory(new Callback<TableColumn<Composition, Boolean>, TableCell<Composition, Boolean>>() {
			@Override
			public TableCell<Composition, Boolean> call(TableColumn<Composition, Boolean> p) {
				return new ButtonCell(searchTable);
			}
		});
	}

	private class ButtonCell extends TableCell<Composition, Boolean> {
		private TableView<Composition> resultsTable;
		private Composition selectedRecord;

		Button cellButton = new Button("Add to List");

		ButtonCell(TableView<Composition> resultsTable) {
			this.resultsTable = resultsTable;
			cellButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent t) {
					setSelectedComposition();
					Composition selectedComposition = getSelectedComposition();
					updateDocument(selectedComposition);
					refreshRepertoireTable(selectedComposition);
					showPopInformingUserThatTheCompositionIsAdded(selectedComposition);
				}
			});
		}

		public Composition getSelectedComposition() {
			return selectedRecord;
		}

		private void refreshRepertoireTable(Composition selectedRecord) {
			observableRepertoireListOfCompositions.add(selectedRecord);
			repertoireTable.setItems(null);
			repertoireTable.layout();
			repertoireTable.setItems(observableRepertoireListOfCompositions);
		}

		private void setSelectedComposition() {
			int selectdIndex = getTableRow().getIndex();
			this.selectedRecord = (Composition) resultsTable.getItems().get(selectdIndex);
		}

		private void updateDocument(Composition selectedRecord) {
			DocumentUpdater updater = new DocumentUpdater(selectedRecord);
			Document updatedDocument = updater.getDocument();
			new XMLWriter(updatedDocument);
		}

		private void showPopInformingUserThatTheCompositionIsAdded(Composition selectedRecord) {
			Stage stage = new Stage();
			stage.setScene(new Scene(new Group(
					new Text(25, 25, selectedRecord.getTitle() + " has been added to your Repertoire List!"))));
			stage.show();
		}

		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if (!empty) {
				setGraphic(cellButton);
			}
		}
	}
	
	
}