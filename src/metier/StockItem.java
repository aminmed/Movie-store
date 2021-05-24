package metier;

public class StockItem {
	private long itemID;
	private long rentalPrice;
	private String title;
	public long getItemID() {
		return itemID;
	}
	public void setItemID(long itemID) {
		this.itemID = itemID;
	}
	public long getRentalPrice() {
		return rentalPrice;
	}
	public void setRentalPrice(long rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	} 

}
