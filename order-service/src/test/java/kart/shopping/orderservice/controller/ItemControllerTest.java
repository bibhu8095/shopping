package kart.shopping.orderservice.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kart.shopping.orderservice.dto.ItemDto;
import kart.shopping.orderservice.implservice.ItemServiceImpl;
import kart.shopping.orderservice.model.Item;
import kart.shopping.orderservice.util.EntityUtil;

@SpringBootTest
class ItemControllerTest {

	private MockMvc mockMvc;
	@MockBean
	private ItemServiceImpl itemService;
	@Autowired
	private WebApplicationContext webAppContest;


	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContest).build();
	}

	@Test
	void testGetAllItem() throws Exception {
		
		List<Item> itemList = EntityUtil.getItemList();

		Mockito.when(itemService.getAllItem()).thenReturn(itemList);
		String URI = "/item/all";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = this.mapToJson(itemList);
		String atual = result.getResponse().getContentAsString();
		assertEquals(expected, atual);

	}
	
	@Test
	void testCreateItem() throws Exception {
		
		Item item = new Item("BOOK", "Notebook", 55.0, 100.0);
		String inputJson = this.mapToJson(item);
		Mockito.when(itemService.createItem(Mockito.any(ItemDto.class))).thenReturn(item);
		String URI = "/item/create";	
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputJson = response.getContentAsString();
		assertEquals(outputJson, inputJson);

	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
