package kart.shopping.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kart.shopping.orderservice.Repository.ItemRepository;
import kart.shopping.orderservice.model.Item;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepo;
	
	@PostMapping(value = "/create")
	public Item createItem(@RequestBody Item item) {
		return itemRepo.save(item);
	}
	
	@GetMapping(value = "/all")
	public List<Item> getAllItem() {
		return itemRepo.findAll();
	}

}
