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
	
	public void addComposition(Composition newComposition) {
		if (!isDuplicate(newComposition)) {
			repertoireList.add(newComposition);
		}
	}
	
	public void removeComposition(Composition newComposition) {
		repertoireList.remove(newComposition);
	}
	
	public boolean isDuplicate(Composition newComposition) {
		for (Composition current : repertoireList) {
			if (newComposition.equals(current)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<Composition> iterator() {
		return repertoireList.iterator();
	}
}
