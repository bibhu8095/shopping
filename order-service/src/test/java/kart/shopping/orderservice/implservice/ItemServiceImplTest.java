package kart.shopping.orderservice.implservice;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import kart.shopping.orderservice.Repository.ItemRepository;
import kart.shopping.orderservice.model.Item;

@SpringBootTest
class ItemServiceImplTest {

	@Autowired
	private ItemServiceImpl itemServiceImpl;

	@MockBean
	private ItemRepository itemRepository;

	@Test
	void testCreateItem() {
		Item item = new Item(1L, "BOOK", "Notebook", 55.0, 100.0);
		Mockito.when(itemRepository.save(item)).thenReturn(item);
		assertEquals(item, itemServiceImpl.createItem(item));
	}

	@Test
	void testGetAllItem() {
		Mockito.when(itemRepository.findAll()).thenReturn(
				Stream.of(new Item(1L, "BOOK", "Notebook", 55.0, 100.0), new Item(1L, "BOOK", "Notebook", 55.0, 100.0))
						.collect(Collectors.toList()));
		assertEquals(2, itemServiceImpl.getAllItem().size());
	}

}
