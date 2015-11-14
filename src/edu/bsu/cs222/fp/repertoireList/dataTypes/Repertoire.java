package edu.bsu.cs222.fp.repertoireList.dataTypes;

import java.util.Iterator;
import java.util.List;

public class Repertoire implements Iterable<Composition> {
	private List<Composition> repertoireList;
	
	public Repertoire(List<Composition> repertoire) {
		this.repertoireList = repertoire;
	}
	
	public List<Composition> getRepertoire() {
		return repertoireList;
	}

	public int getLength() {
		return repertoireList.size();
	}

	@Override
	public Iterator<Composition> iterator() {
		return repertoireList.iterator();
	}
}
