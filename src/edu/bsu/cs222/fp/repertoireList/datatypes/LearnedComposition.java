package edu.bsu.cs222.fp.repertoireList.datatypes;

public class LearnedComposition {

	private Composition composition;
	private boolean wasPerformed;
	private boolean wasMemorized;
	private int yearLearned;
	private String withEnsemble;
	
	// This field contains the results of a drop-down box and should only
	// be set to "Solo", "Ensemble",  "Opera", "Orchestral", or "Other"
	private String ensembleType;
	
	public LearnedComposition(Composition composition) {
		this.composition = composition;
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
	}
	
	public boolean wasPerformed() {
		return this.wasPerformed;
	}
	
	public void setWasPerformed() {
		this.wasPerformed = true;
	}
	
	public void setWasNotPerformed() {
		this.wasPerformed = false;
	}

	public boolean wasMemorized() {
		return wasMemorized;
	}

	public void setWasMemorized() {
		this.wasMemorized = true;
	}
	
	public void setWasNotMemorized() {
		this.wasMemorized = false;
	}

	public int yearLearned() {
		return yearLearned;
	}

	public void setYearLearned(int yearLearned) {
		this.yearLearned = yearLearned;
	}

	public String getEnsemble() {
		return withEnsemble;
	}

	public void setEnsemble(String withEnsemble) {
		this.withEnsemble = withEnsemble;
	}
}