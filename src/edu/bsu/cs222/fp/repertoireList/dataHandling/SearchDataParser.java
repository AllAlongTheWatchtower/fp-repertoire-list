package edu.bsu.cs222.fp.repertoireList.dataHandling;

import java.util.List;

import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Composition;

public class SearchDataParser extends Parser {

	public SearchDataParser(Document searchResults) {
		this.searchResults = searchResults;
		this.compositionsNodeList = getNodeListOfCompositions();
		this.compositionsList = createListOfCompositions();
	}

	public List<Composition> getListOfCompositions() {
		return compositionsList;
	}

	public Document getSearchResults() {
		return searchResults;
	}

}