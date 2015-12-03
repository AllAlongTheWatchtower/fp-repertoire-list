package edu.bsu.cs222.fp.repertoireList.datatypes;

public class Composition {

	public static Builder byComposer(String composer){
		return new Builder(composer);
	}

	public static final class Builder {
		private String composer;
		private String titleToAdd;

		public Builder(String composer) {
			this.composer = composer;
		}
	
		public Composition withTitle(String titleToAdd) {
			this.titleToAdd = titleToAdd;
			return new Composition(this);
		}
	}	

	private String composer;
	private String title;
	private boolean wasPerformed;
	private boolean wasMemorized;
	private String yearLearned;
	private String withEnsemble;
	private boolean yearSet;
	private boolean performedSet;
	private boolean memorizedSet;
	private boolean ensembleSet;
	private boolean ensembleTypeSet;
	private enum EnsembleType {SOLO, ENSEMBLE, OPERA, CHAMBER, ORCHESTRA, OTHER}
	private EnsembleType ensembleType;
	
	public Composition(Builder builder) {
		this.composer = builder.composer;
		this.title = builder.titleToAdd;
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
	
	public String getComposer() {
		return composer;
	}
	
	public String getTitle() {
		return title;
	}

	public String getEnsembleType() {
		if (this.ensembleTypeSet) {
			switch (this.ensembleType) {
				case SOLO:			return "solo";
				case ENSEMBLE:		return "ensemble";
				case CHAMBER:		return "chamber";
				case ORCHESTRA:		return "orchestra";
				case OPERA:			return "opera";
				case OTHER:			return "other";
				default:			return null;
			}
		} else {
			return null;
		}
	}
	
	public void setEnsembleType(String type) {
		if (type.equals("other")) {
			this.ensembleType = EnsembleType.OTHER;
		} else if (type.equals("solo")) {
			this.ensembleType = EnsembleType.SOLO;
		} else if (type.equals("ensemble")) {
			this.ensembleType = EnsembleType.ENSEMBLE;
		} else if (type.equals("chamber")) {
			this.ensembleType = EnsembleType.CHAMBER;
		} else if (type.equals("orchestra")) {
			this.ensembleType = EnsembleType.ORCHESTRA; 
		} else if (type.equals("opera")) {
			this.ensembleType = EnsembleType.OPERA;
		}
		
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

	public String yearLearned() {
		return yearLearned;
	}

	public void setYearLearned(String yearLearned) {
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
		return (other instanceof Composition && 
			composer.equals(((Composition) other).getComposer()) &&
			title.equals(((Composition) other).getTitle()));
	}
}