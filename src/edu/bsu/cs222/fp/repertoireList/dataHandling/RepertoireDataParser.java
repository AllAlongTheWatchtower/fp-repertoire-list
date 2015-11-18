package edu.bsu.cs222.fp.repertoireList.dataHandling;

import org.w3c.dom.Document;

import edu.bsu.cs222.fp.repertoireList.dataTypes.Repertoire;

public class RepertoireDataParser extends Parser {

	private Repertoire repertoire;
	
	public RepertoireDataParser(Document searchResults) {
		this.searchResults = searchResults;
		this.compositionsNodeList = getNodeListOfCompositions();
		this.compositionsList = createListOfCompositions();
		repertoire = new Repertoire(compositionsList);
	}
    
	public Repertoire getRepertoireObject() {
		return repertoire;
	}
	
	public Document getSearchResults() {
		return searchResults;
	}
}