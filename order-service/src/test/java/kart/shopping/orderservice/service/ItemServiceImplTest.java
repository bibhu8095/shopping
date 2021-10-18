package kart.shopping.orderservice.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import kart.shopping.orderservice.dto.ItemDto;
import kart.shopping.orderservice.implservice.ItemServiceImpl;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.repository.ItemRepository;
import kart.shopping.orderservice.util.EntityUtil;

@SpringBootTest
class ItemServiceImplTest {

	@InjectMocks
	private ItemServiceImpl itemServiceImpl = new ItemServiceImpl();

	@Mock
	private ItemRepository itemRepository;

	@Test
	void testCreateItem() throws Exception{
		
		Item item = new Item("BOOK", "Notebook", 55.0, 100.0);
		ItemDto itemDto = new ItemDto("Book", "NoteBook", 55.0, 100.0);
		Mockito.when(itemRepository.save(Mockito.any())).thenReturn(item);
		assertEquals(item, itemServiceImpl.createItem(itemDto));
	}

	@Test
	void testGetAllItem() throws Exception{
		
		List<Item> itemList = EntityUtil.getItemList();
		
		Mockito.when(itemRepository.findAll()).thenReturn(itemList);
		assertEquals(2, itemServiceImpl.getAllItem().size());
	}

}
