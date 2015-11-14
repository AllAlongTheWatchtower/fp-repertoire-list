package edu.bsu.cs222.fp.repertoireList.dataTypes;

import java.util.List;

public class Repertoire {
	private List<Composition> repertoireList;
	
	public Repertoire(List<Composition> repertoire) {
		this.repertoireList = repertoire;
	}
	
	public List<Composition> getRepertoire() {
		return repertoireList;
	}
}
