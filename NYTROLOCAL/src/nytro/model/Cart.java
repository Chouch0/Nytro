package nytro.model;

import java.util.ArrayList;
import java.util.List;


public class Cart {
	private List<VideogiocoPagamentoBean> items;
	
	public Cart() {
		items = new ArrayList<VideogiocoPagamentoBean>();
	}

	public void addItem(VideogiocoPagamentoBean item) {
		items.add(item);									//Non verifico se il prodotto inserito esiste già all'interno del carrello
	}
	
	public void deleteItem(VideogiocoPagamentoBean item) {
		items.remove(item);
	}
	
	public List<VideogiocoPagamentoBean> getItems(){
		return items;
	}
	
	public void deleteAll() {
		items.clear();
	}
	
	public boolean contains(VideogiocoPagamentoBean item) {
		for(VideogiocoPagamentoBean x:items) 
			if(x.getCodice()==item.getCodice())
				return true;
		return false;
	}
	
}
