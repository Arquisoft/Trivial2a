package models;

/**
 * Model class to store and move around the different boards setups available.
 * 
 * @author Montero Hernández, José Antonio
 *
 */
public class BoardOption {
	private int id;
	
	private String name;
	
	private String miniatureImageUrl;
	private String boardImageUrl;
	private String boardTextFileUrl;
	
	private int minPlayers;
	private int maxPlayers;
	
	private int missingRequiredFields = 6;
	
	public BoardOption (int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (this.name == null && name != null)
			missingRequiredFields--;
		this.name = name;
	}
	
	public String getMiniatureImageUrl() {
		return miniatureImageUrl;
	}
	
	public void setMiniatureImageUrl(String miniatureImageUrl) {
		if (this.miniatureImageUrl == null && miniatureImageUrl != null)
			missingRequiredFields--;
		this.miniatureImageUrl = miniatureImageUrl;
	}
	
	public String getBoardImageUrl() {
		return boardImageUrl;
	}
	
	public void setBoardImageUrl(String boardImageUrl) {
		if (this.boardImageUrl == null && boardImageUrl != null)
			missingRequiredFields--;
		this.boardImageUrl = boardImageUrl;
	}
	
	public String getBoardTextFileUrl() {
		return boardTextFileUrl;
	}
	
	public void setBoardTextFileUrl(String boardTextFileUrl) {
		if (this.boardTextFileUrl == null && boardTextFileUrl != null)
			missingRequiredFields--;
		this.boardTextFileUrl = boardTextFileUrl;
	}
	
	public int getMinPlayers() {
		return minPlayers;
	}
	
	public void setMinPlayers(int minPlayers) {
		if (this.minPlayers == 0 && minPlayers != 0)
			missingRequiredFields--;
		this.minPlayers = minPlayers;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public void setMaxPlayers(int maxPlayers) {
		if (this.maxPlayers == 0 && maxPlayers != 0)
			missingRequiredFields--;
		this.maxPlayers = maxPlayers;
	}
	
	public boolean hasMissingRequiredFields() {
		return (missingRequiredFields == 0)?false:true;
	}
}
