package edu.bsu.cs222.fp.repertoireList.datatypes;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Repertoire extends Observable implements Iterable<Composition> {
	private List<Composition> repertoireList;

	public Repertoire(List<Composition> repertoire) {
		this.repertoireList = repertoire;
	}
	
	public List<Composition> getRepertoireList() {
		return repertoireList;
	}

	public int getLength() {
		return repertoireList.size();
	}

	public void addComposition(Composition newComposition) {
		if (!isDuplicate(newComposition)) {
			repertoireList.add(newComposition);
			setChanged();
			notifyObservers();
		} else if (isDuplicate(newComposition)) {
			repertoireList.remove(newComposition);
			repertoireList.add(newComposition);
			setChanged();
			notifyObservers();
		}
	}

	public void removeComposition(Composition newComposition) {
		repertoireList.remove(newComposition);
		setChanged();
		notifyObservers();
	}

	public boolean isDuplicate(Composition newComposition) {
		if (repertoireList.contains(newComposition)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Iterator<Composition> iterator() {
		return repertoireList.iterator();
	}
}