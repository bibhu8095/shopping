package kart.shopping.orderservice.implservice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kart.shopping.orderservice.Repository.ItemRepository;
import kart.shopping.orderservice.model.Item;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemServiceImplTest {
	
	@Autowired
	private ItemServiceImpl itemServiceImpl;
	
	@MockBean
	private ItemRepository itemRepository;

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testCreateItem() {
		
		Item item=new Item();
		
		item.setItemId(1L);
		item.setItemName("Book");
		item.setDescription("Best Book to read");
		item.setPrice(80.0);
		item.setStock(10.0);
		
		Mockito.when(itemRepository.save(item)).thenReturn(item);
		assertEquals(itemServiceImpl.createItem(item), item);
	}

	@Test
	public void testGetAllItem() {
		fail("Not yet implemented");
	}

}
