package edu.bsu.cs222.fp.repertoireList.datahandling;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;

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
	
	public List<Composition> createListOfCompositions() {
    	for(int i = 0; i < compositionsNodeList.getLength(); i++) {
    		addComposerAtIndex(i);
    	}
    	return compositionsList;
    }
	
	private void addComposerAtIndex(int i) {
		Node currentNode = compositionsNodeList.item(i).getLastChild();
		Composition current = createComposition(currentNode);
		compositionsList.add(current);
	}
    
	private Composition createComposition(Node currentNode) {			
		String composer = currentNode.getPreviousSibling().getTextContent();
		String title = currentNode.getTextContent();
		Composition composition = Composition.byComposer(composer).withTitle(title);
		return composition;
	}
}