package metier;

import java.io.Serializable;

public class Film extends StockItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1819580590201584431L;
	private String acteur;

	public String getActeur() {
		return acteur;
	}

	public void setActeur(String acteur) {
		this.acteur = acteur;
	} 

}
