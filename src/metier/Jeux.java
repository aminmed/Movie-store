package metier;
import java.io.Serializable;

import metier.StockItem;

public class Jeux extends StockItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1628401398023918973L;
	private String plateforme;

	public String getPlateforme() {
		return plateforme;
	}

	public void setPlateforme(String plateforme) {
		this.plateforme = plateforme;
	} 
}
