package kart.shopping.orderservice.service;

import java.util.List;

import kart.shopping.orderservice.model.Item;

public interface ItemService {

	public Item createItem(Item item);
	public List<Item> getAllItem();
}
