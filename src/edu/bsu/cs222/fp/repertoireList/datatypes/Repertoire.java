package edu.bsu.cs222.fp.repertoireList.datatypes;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Repertoire extends Observable implements Iterable<LearnedComposition> {
	private List<LearnedComposition> repertoireList;

	public Repertoire(List<LearnedComposition> repertoire) {
		this.repertoireList = repertoire;
	}
	
	public List<LearnedComposition> getRepertoireList() {
		return repertoireList;
	}

	public int getLength() {
		return repertoireList.size();
	}

	public void addComposition(LearnedComposition newComposition) {
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

	public void removeComposition(LearnedComposition newComposition) {
		repertoireList.remove(newComposition);
		setChanged();
		notifyObservers();
	}

	public boolean isDuplicate(LearnedComposition newComposition) {
		if (repertoireList.contains(newComposition)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Iterator<LearnedComposition> iterator() {
		return repertoireList.iterator();
	}
}