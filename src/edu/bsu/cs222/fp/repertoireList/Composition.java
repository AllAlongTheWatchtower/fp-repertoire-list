package edu.bsu.cs222.fp.repertoireList;

public class Composition {
	private String composer;
	private String title;

	public Composition(Builder builder) {
		this.composer = builder.composerToAdd;
		this.title = builder.titleToAdd;
	}
	
	public String getComposer() {
		return this.composer;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public static final class Builder {
		private String composerToAdd;
		private String titleToAdd;

		public Builder() {
			
		}
		
		public Builder byComposer(String composerToAdd){
			this.composerToAdd = composerToAdd;
			return this;
		}

		public Composition withTitle(String titleToAdd) {
			this.titleToAdd = titleToAdd;
			return new Composition(this);
		}
	}
}