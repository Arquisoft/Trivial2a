package model;

/**
 * Clase "Score" del modelo.
 * 
 * @author Gonz�lez Fernandez Cristian
 * @author Vel�zquez Vico �lvaro
 * @version 1 - Last changes: -
 */
public class Score {

	// Attributes to save the game�s state
	// If the attribute is false, the current user hasnt got that piece
	// If the attribute is true, the current user has got that piece
	private boolean sports;
	private boolean showsAndEntertainment;
	private boolean scienceAndTechnology;
	private boolean artAndLiterature;
	private boolean geography;
	private boolean history;

	public boolean isSports() {
		return sports;
	}

	public void setSports(boolean sports) {
		this.sports = sports;
	}

	public boolean isShowsAndEntertainment() {
		return showsAndEntertainment;
	}

	public void setShowsAndEntertainment(boolean showsAndEntertainment) {
		this.showsAndEntertainment = showsAndEntertainment;
	}

	public boolean isScienceAndTechnology() {
		return scienceAndTechnology;
	}

	public void setScienceAndTechnology(boolean scienceAndTechnology) {
		this.scienceAndTechnology = scienceAndTechnology;
	}

	public boolean isArtAndLiterature() {
		return artAndLiterature;
	}

	public void setArtAndLiterature(boolean artAndLiterature) {
		this.artAndLiterature = artAndLiterature;
	}

	public boolean isGeography() {
		return geography;
	}

	public void setGeography(boolean geography) {
		this.geography = geography;
	}

	public boolean isHistory() {
		return history;
	}

	public void setHistory(boolean history) {
		this.history = history;
	}

	public boolean hasAllThePieces() {
		if (!sports || !showsAndEntertainment || !scienceAndTechnology
				|| !artAndLiterature || !geography || !history)
			return false;
		return true;
	}
}
