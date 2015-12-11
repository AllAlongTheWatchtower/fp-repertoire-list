package edu.bsu.cs222.fp.repertoireList.datahandling;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.bsu.cs222.fp.repertoireList.datatypes.Composition;
import edu.bsu.cs222.fp.repertoireList.datatypes.Repertoire;

public class RepertoireDataParser extends Parser {

	private Repertoire repertoire;
	private List<Composition> learnedCompositionsList = new ArrayList<Composition>();

	public RepertoireDataParser(InputStream fileInputStream) {
		try {
			this.searchResults = readXMLDocumentFromFile(fileInputStream);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		this.compositionsNodeList = getNodeListOfCompositions();
		this.learnedCompositionsList = createListOfLearnedCompositions();
		repertoire = new Repertoire(learnedCompositionsList);
	}

	public Repertoire getRepertoireObject() {
		return repertoire;
	}

	public Document getSearchResults() {
		return searchResults;
	}

	private Document readXMLDocumentFromFile(InputStream fileInputStream)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document sampleXML = documentBuilder.parse(fileInputStream);
		return sampleXML;
	}

	private List<Composition> createListOfLearnedCompositions() {
		for (int i = 0; i < compositionsNodeList.getLength(); i++) {
			addCompositionAtIndex(i);
		}
		return learnedCompositionsList;
	}

	private void addCompositionAtIndex(int i) {
		Node currentNode = compositionsNodeList.item(i);
		LearnedCompositionCreator current = new LearnedCompositionCreator(currentNode);
		learnedCompositionsList.add(current.getLearnedComposition());
	}

	private class LearnedCompositionCreator {
		private NodeList compositionData;
		private Composition current;

		private LearnedCompositionCreator(Node currentNode) {
			this.compositionData = currentNode.getChildNodes();
			this.current = createLearnedComposition(compositionData);
			addAdditionalData();
		}

		private Composition getLearnedComposition() {
			return current;
		}

		private void addAdditionalData() {
			Node notes = compositionData.item(2);
			addPerformed(notes);
			addMemorized(notes);
			addYear(notes);
			addEnsemble(notes);
			addEnsembleType(notes);
		}

		private void addPerformed(Node notes) {
			if (notes.getAttributes().getNamedItem("performed") != null) {
				String performed = notes.getAttributes().getNamedItem("performed").getNodeValue();
				if (performed.equals("true")) {
					current.setWasPerformed();
				} else if (performed.equals("false")) {
					current.setWasNotPerformed();
				}
			}

		}

		private void addMemorized(Node notes) {
			if (notes.getAttributes().getNamedItem("memorized") != null) {
				String memorized = notes.getAttributes().getNamedItem("memorized").getNodeValue();
				if (memorized.equals("true")) {
					current.setWasMemorized();
				} else if (memorized.equals("false")) {
					current.setWasNotMemorized();
				}
			}
		}

		private void addYear(Node notes) {
			if (notes.getAttributes().getNamedItem("year") != null) {
				String year = notes.getAttributes().getNamedItem("year").getNodeValue();
				current.setYearLearned(year);
			}
		}

		private void addEnsemble(Node notes) {
			if (notes.getAttributes().getNamedItem("ensemble") != null) {
				String ensemble = notes.getAttributes().getNamedItem("ensemble").getNodeValue();
				current.setEnsemble(ensemble);
			}
		}

		private void addEnsembleType(Node notes) {
			if (notes.getAttributes().getNamedItem("ensembleType") != null) {
				String ensembleType = notes.getAttributes().getNamedItem("ensembleType").getNodeValue();
				current.setEnsembleType(ensembleType);
			}
		}
	}

	private Composition createLearnedComposition(NodeList compositionData) {
		String composer = compositionData.item(0).getTextContent();
		String title = compositionData.item(1).getTextContent();
		Composition composition = Composition.byComposer(composer).withTitle(title);
		return composition;
	}
}