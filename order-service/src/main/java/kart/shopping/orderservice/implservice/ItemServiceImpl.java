package kart.shopping.orderservice.implservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kart.shopping.orderservice.Repository.ItemRepository;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepo;
	

	@Override
	public Item createItem(Item item) {
		
		return itemRepo.save(item);
	}
	@Override
	public List<Item> getAllItem() {
		return itemRepo.findAll();
	}

}
