package com.mobileapp.testcases;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mobileapp.exception.EmptyCartException;
import com.mobileapp.exception.MobileNotFoundException;
import com.mobileapp.model.Mobile;
import com.mobileapp.service.CartDetails;
import com.mobileapp.service.ICartService;
import com.mobileapp.service.OrderDetails;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class CartDeatilsTest {

	@Mock
	ICartService cartService;

	@InjectMocks
	CartDetails cartDetails;
	Mobile mobile1, mobile2, mobile3, mobile4, mobile5, mobile6;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		mobile1 = new Mobile(1, "Samsung", "A53", 26000);
		mobile2 = new Mobile(2, "Xiaomi", "A3", 16000);
		mobile3 = new Mobile(3, "Realme", "Neo2", 32000);
		mobile4 = new Mobile(4, "Xiaomi", "3s", 10000);
		mobile5 = new Mobile(5, "Xiaomi", "4a", 10000);
		mobile6 = new Mobile(6, "Samsung", "S21", 53000);

		List<Mobile> mobilesList = Arrays.asList(mobile1, mobile2, mobile3, mobile4, mobile5, mobile6);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("adding a mobile from cart")
	void testAddCart() throws MobileNotFoundException {
		doNothing().when(cartService).addtoCart(mobile1);
		String actual = cartDetails.addtoCart(mobile1);
		String expected = "added successfully";
		assertEquals(expected, actual, "Invalid");
	}

	@Test
	@DisplayName("exception showing when trying to add mobile to cart")
	void testAddCartException() throws MobileNotFoundException {
		doThrow(new MobileNotFoundException("invalid")).when(cartService).addtoCart(mobile1);

		assertThrows(MobileNotFoundException.class, () -> cartDetails.addtoCart(mobile1));
	}

	@Test
	@DisplayName("showing the cart")
	void testShowCart() throws EmptyCartException {
		List<Mobile> mobileList = Arrays.asList(mobile3, mobile1, mobile2);
		doReturn(Arrays.asList(mobile1, mobile2, mobile3)).when(cartService).showCart();
		List<Mobile> actualMobileList = cartDetails.showCart();
		assertEquals(mobileList, actualMobileList, "not equal");

	}

	@Test
	@DisplayName("cart is empty")
	void testShowCartEmpty() throws EmptyCartException {
		doThrow(new EmptyCartException()).when(cartService).showCart();
		assertThrows(EmptyCartException.class, () -> cartDetails.showCart());
	}

	@Test
	@DisplayName("cart is null ")
	void testShowCartNull() throws EmptyCartException {
		doReturn(null).when(cartService).showCart();
		assertNull(cartDetails.showCart());
	}

	@Test
	@DisplayName("removing a mobile from cart")
	void testRemoveFromCart() throws MobileNotFoundException {
		doReturn(true).when(cartService).removeFromCart(mobile1);
		assertEquals(true, cartDetails.removeFromCart(mobile1));
	}

	@Test
	@DisplayName("trying to remove a mobile from empty cart")
	void testRemoveFromCartEmpty() throws MobileNotFoundException {
		doThrow(new MobileNotFoundException()).when(cartService).removeFromCart(mobile3);
		assertThrows(MobileNotFoundException.class, () -> cartDetails.removeFromCart(mobile3));
	}

}
