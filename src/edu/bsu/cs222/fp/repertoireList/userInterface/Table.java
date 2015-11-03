package edu.bsu.cs222.fp.repertoireList.userInterface;

import edu.bsu.cs222.fp.repertoireList.dataHandling.Composition;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public abstract class Table {
	
	public TableColumn<Composition, String> createComposerColumn() {
		TableColumn<Composition, String> composerColumn = new TableColumn<>("Composer");
		composerColumn.setCellValueFactory(new PropertyValueFactory<Composition, String>("composer"));
		composerColumn.setMinWidth(150);
		return composerColumn;
	}

	public TableColumn<Composition, String> createTitleColumn() {
		TableColumn<Composition, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<Composition, String>("title"));
		titleColumn.setMinWidth(400);
		return titleColumn;
	}
}