package edu.bsu.cs222.fp.repertoireList.datatypes;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root (name = "Composition")
public class Composition {
	
	public static Builder byComposer(String composer){
		return new Builder(composer);
	}

	public static final class Builder {
		@Element
		private String composer;
		@Element
		private String titleToAdd;

		public Builder(String composer) {
			this.composer = composer;
		}
	
		public Composition withTitle(String titleToAdd) {
			this.titleToAdd = titleToAdd;
			return new Composition(this);
		}
	}	
	
	@Element
	private String title;
	
	@Element
	private String composer;
	
	@Attribute (required = false)
	private boolean wasPerformed;
	
	@Attribute (required = false)
	private boolean wasMemorized;
	
	@Attribute (required = false)
	private String yearLearned;
	
	@Attribute (required = false)
	private String ensemble;
	
	private enum EnsembleType {SOLO, ENSEMBLE, OPERA, CHAMBER, ORCHESTRA, OTHER, NONE}
	
	@Attribute (required = false)
	private EnsembleType ensembleType;
	
	public Composition() {};
	
	public Composition(Builder builder) {
		this.composer = builder.composer;
		this.title = builder.titleToAdd;
		this.ensembleType = EnsembleType.NONE;
	}
	
	public String getComposer() {
		return composer;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getEnsemble() {
		return ensemble;
	}
	
	public void setEnsemble(String ensemble) {
		this.ensemble = ensemble;
	}
	
	public String getYearLearned() {
		return yearLearned;
	}
	
	public void setYearLearned(String year) {
		this.yearLearned = year;
	}
	
	public boolean getWasMemorized() {
		return wasMemorized;
	}
	
	public void setWasMemorized() {
		this.wasMemorized = true;
	}
	
	public void setWasNotMemorized() {
		this.wasMemorized = false;
	}
	
	public boolean getWasPerformed() {
		return wasPerformed;
	}
	
	public void setWasPerformed() {
		this.wasPerformed = true;
	}
	
	public void setWasNotPerformed() {
		this.wasPerformed = false;
	}
	
	public String getEnsembleType() {
		switch (this.ensembleType) {
			case SOLO:			return "solo";
			case ENSEMBLE:		return "ensemble";
			case CHAMBER:		return "chamber";
			case ORCHESTRA:		return "orchestra";
			case OPERA:			return "opera";
			case OTHER:			return "other";
			case NONE:			return "none";
			default:			return null;
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
	}
	
	@Override
	public boolean equals(Object other){
		return (other instanceof Composition && 
			composer.equals(((Composition) other).getComposer()) &&
			title.equals(((Composition) other).getTitle()));
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * title.hashCode() * composer.hashCode();
		return hash;
	}
	
	public String toString() {
		return composer + " wrote " + title;
	}
}