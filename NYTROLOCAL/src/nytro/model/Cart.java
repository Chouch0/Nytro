package nytro.model;

import java.util.ArrayList;
import java.util.List;


public class Cart {
	private List<VideogiocoBean> items;
	
	public Cart() {
		items = new ArrayList<VideogiocoBean>();
	}

	public void addItem(VideogiocoBean item) {
		items.add(item);									//Non verifico se il prodotto inserito esiste già all'interno del carrello
	}
	
	public void deleteItem(VideogiocoBean item) {
		items.remove(item);
	}
	
	public List<VideogiocoBean> getItems(){
		return items;
	}
	
	public void deleteAll() {
		items.clear();
	}
	
}
