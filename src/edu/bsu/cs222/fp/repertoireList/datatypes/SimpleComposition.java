package edu.bsu.cs222.fp.repertoireList.datatypes;

public class SimpleComposition implements Composition {
	public static Builder byComposer(String composer){
		return new Builder(composer);
	}

	public static final class Builder {
		private String composer;
		private String titleToAdd;

		public Builder(String composer) {
			this.composer = composer;
		}
	
		public SimpleComposition withTitle(String titleToAdd) {
			this.titleToAdd = titleToAdd;
			return new SimpleComposition(this);
		}
	}	

	private final String composer;
	private final String title;

	public SimpleComposition(Builder builder) {
		this.composer = builder.composer;
		this.title = builder.titleToAdd;
	}

	public String getComposer() {
		return this.composer;
	}

	public String getTitle() {
		return this.title;
	}

	@Override
	public boolean equals(Object other){
		return (other instanceof Composition && 
			composer.equals(((SimpleComposition) other).getComposer()) &&
			title.equals(((SimpleComposition) other).getTitle()));
	}
}