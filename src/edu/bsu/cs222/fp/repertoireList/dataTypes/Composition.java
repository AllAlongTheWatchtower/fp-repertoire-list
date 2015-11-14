package edu.bsu.cs222.fp.repertoireList.dataTypes;

public final class Composition {
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
	
	private final String composer;
	private final String title;

	public Composition(Builder builder) {
		this.composer = builder.composer;
		this.title = builder.titleToAdd;
	}
	
	public String getComposer() {
		return this.composer;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public boolean equals(Composition other){
		return (composer.equals(other.getComposer())&&title.equals(other.getTitle()));
	}
}