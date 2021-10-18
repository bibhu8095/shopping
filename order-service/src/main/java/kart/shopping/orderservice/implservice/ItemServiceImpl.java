package kart.shopping.orderservice.implservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kart.shopping.orderservice.dto.ItemDto;
import kart.shopping.orderservice.exception.OsDataNotFoundException;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.repository.ItemRepository;
import kart.shopping.orderservice.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger Logger = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item createItem(ItemDto item) {

		Logger.info("save Item start");
		if (item == null) {
			throw new OsDataNotFoundException("Item is null");
		}
		Logger.info("before Item done");
		Item itemOut = new Item(item.getItemName(), item.getDescription(), item.getPrice(), item.getStock());
		itemOut = itemRepository.save(itemOut);
		Logger.info("save Item done");
		return itemOut;
	}

	@Override
	public List<Item> getAllItem() {
		Logger.info("getting all items..");
		return itemRepository.findAll();
	}

}
