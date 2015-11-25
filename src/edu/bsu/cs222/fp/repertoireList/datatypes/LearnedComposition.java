package edu.bsu.cs222.fp.repertoireList.datatypes;

public class LearnedComposition {

	private Composition composition;
	private boolean wasPerformed;
	private boolean wasMemorized;
	private int yearLearned;
	private String withEnsemble;
	private boolean yearSet;
	private boolean performedSet;
	private boolean memorizedSet;
	private boolean ensembleSet;
	private boolean ensembleTypeSet;
	
	
	// This field contains the results of a drop-down box and should only
	// be set to "Solo", "Ensemble",  "Opera", "Orchestral", or "Other"
	private String ensembleType;
	
	public LearnedComposition(Composition composition) {
		this.composition = composition;
		this.yearSet = false;
		this.performedSet = false;
		this.memorizedSet = false;
		this.ensembleSet = false;
		this.ensembleTypeSet = false;
	}
	
	public boolean yearSet() {
		return yearSet;
	}
	
	public boolean performedSet() {
		return performedSet;
	}
	
	public boolean memorizedSet() {
		return memorizedSet;
	}
	
	public boolean ensembleSet() {
		return ensembleSet;
	}
	
	public boolean ensembleTypeSet() {
		return ensembleTypeSet;
	}
	
	public Composition getComposition() {
		return composition;
	}
	
	public String getComposer() {
		return composition.getComposer();
	}
	
	public String getTitle() {
		return composition.getTitle();
	}

	public String getEnsembleType() {
		return ensembleType;
	}
	
	public void setEnsembleType(String ensembleType) {
		this.ensembleType = ensembleType;
		this.ensembleTypeSet = true;
	}
	
	public boolean wasPerformed() {
		return this.wasPerformed;
	}
	
	public void setWasPerformed() {
		this.wasPerformed = true;
		this.performedSet = true;
	}
	
	public void setWasNotPerformed() {
		this.wasPerformed = false;
		this.performedSet = true;
	}

	public boolean wasMemorized() {
		return wasMemorized;
	}

	public void setWasMemorized() {
		this.wasMemorized = true;
		this.memorizedSet = true;
	}
	
	public void setWasNotMemorized() {
		this.wasMemorized = false;
		this.memorizedSet = true;
	}

	public int yearLearned() {
		return yearLearned;
	}

	public void setYearLearned(int yearLearned) {
		this.yearLearned = yearLearned;
		this.yearSet = true;
	}

	public String getEnsemble() {
		return withEnsemble;
	}

	public void setEnsemble(String withEnsemble) {
		this.withEnsemble = withEnsemble;
		this.ensembleSet = true;
	}
	
	@Override
	public boolean equals(Object other){
		return (other instanceof LearnedComposition && 
				this.getComposition().equals(((LearnedComposition) other).getComposition()));
	}
}