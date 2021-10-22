package kart.shopping.orderservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kart.shopping.orderservice.dto.ItemDto;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.service.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping(value = "/create")
	public Item createItem(@Valid @RequestBody ItemDto item) {
		return itemService.createItem(item);
	}
	
	@GetMapping(value = "/all")
	public List<Item> getAllItem() {
		return itemService.getAllItem();
	}
}
