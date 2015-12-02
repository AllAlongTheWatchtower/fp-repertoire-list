package edu.bsu.cs222.fp.repertoireList.datatypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Repertoire extends Observable implements Iterable<LearnedComposition> {
	private List<LearnedComposition> repertoireList;
	private List<Composition> simpleRepertoireList;

	public Repertoire(List<LearnedComposition> repertoire) {
		this.repertoireList = repertoire;
		this.simpleRepertoireList = simplifyList(repertoire);
	}
	
	public List<Composition> simplifyList(List<LearnedComposition> repertoire ) {
		List<Composition> simplifiedList = new ArrayList<Composition>();
		for (LearnedComposition c : repertoire) {
			simplifiedList.add(c.getComposition());
		}
		return simplifiedList;
	}
	
	public List<Composition> getSimpleRepertoireList() {
		return simpleRepertoireList;
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