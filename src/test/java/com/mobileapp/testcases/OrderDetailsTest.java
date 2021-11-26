package com.mobileapp.testcases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mobileapp.exception.MobileNotFoundException;
import com.mobileapp.model.Mobile;
import com.mobileapp.service.CartDetails;
import com.mobileapp.service.ICartService;
import com.mobileapp.service.IMobileService;
import com.mobileapp.service.OrderDetails;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class OrderDetailsTest {

	@Mock
	IMobileService mobileService;

//	create an object of orderDetails
	@InjectMocks
	OrderDetails orderDetails;

	Mobile mobile1, mobile2, mobile3, mobile4, mobile5, mobile6;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		mobile1 = new Mobile(1, "Xiaomi", "Poco M2 pro", 26000);
		mobile2 = new Mobile(2, "Samsung", "Poco M2", 16000);
		mobile3 = new Mobile(3, "realme", "Galaxy M20", 32000);
		mobile4 = new Mobile(4, "Samsung", "OPPO A31", 10000);
		mobile5 = new Mobile(5, "Xiaomi", "Redmi 10 Prime", 10000);
		mobile6 = new Mobile(6, "Xiaomi", "Galaxy F12", 53000);
		orderDetails = new OrderDetails();
		orderDetails.setMobileService(mobileService);
		List<Mobile> mobileData = Arrays.asList(mobile1, mobile2, mobile3, mobile4, mobile5, mobile6);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("check with valid list")
	void testShowMobiles() throws MobileNotFoundException {
		String brand = "Samsung";
		List<Mobile> expectedMobiles = Arrays.asList(mobile2, mobile4);

		Mockito.when(mobileService.getByBrand("Samsung")).thenReturn(Arrays.asList(mobile2, mobile4));
		List<Mobile> actualMobileList = orderDetails.showMobiles(brand);
		assertEquals(expectedMobiles, actualMobileList, "List not equal");

	}

	@Test
	@DisplayName("check with not avilable mobiles")
	void testshowMobilesInvalid() throws MobileNotFoundException {
		Mockito.when(mobileService.getByBrand("vivo")).thenThrow(MobileNotFoundException.class);
		assertThrows(MobileNotFoundException.class, () -> orderDetails.showMobiles("vivo"));
	}

	@Test
	@DisplayName("check with null list")
	void testShowMobilesNull() throws MobileNotFoundException {
		String brand = "Samsung";

		Mockito.when(mobileService.getByBrand("Samsung")).thenReturn(null);

		List<Mobile> actualMobileList = orderDetails.showMobiles(brand);
		assertNull(actualMobileList);

	}

	@Test
	@DisplayName("check with empty mobile list")
	void testShowMobilesEmpty() throws MobileNotFoundException {
		String brand = "Moto";

		Mockito.when(mobileService.getByBrand("Moto")).thenReturn(new ArrayList<>());

		List<Mobile> actualMobileList = orderDetails.showMobiles(brand);
		assertEquals(0, actualMobileList.size(), "List should be empty");

	}

	@Test
	@DisplayName("check to have the mobiles in brand sort")
	void testShowMobilesSortByBrand() throws MobileNotFoundException {
		String brand = "Samsung";
		List<Mobile> expectedMobiles = Arrays.asList(mobile1, mobile6);

		Mockito.when(mobileService.getByBrand("Samsung")).thenReturn(Arrays.asList(mobile1, mobile6));
		List<Mobile> actualMobileList = orderDetails.showMobiles(brand);

		assertEquals(expectedMobiles, actualMobileList, "List not equal");

	}

	@Test
	@DisplayName("check with valid mobile id")
	void testOrderMobile() throws MobileNotFoundException {
		int id = 1;
		String expectedMobile = "mobile ordered";

		Mockito.when(mobileService.getById(1)).thenReturn(mobile1);
		String actualMobile = orderDetails.orderMobile(id);
		assertEquals(expectedMobile, actualMobile, "Mobile not same");

	}

	@Test

	@DisplayName("check with invalid id")
	void testOrderInvalid() throws MobileNotFoundException {

		String expectedMobile = "mobile not ordered";
		Mockito.when(mobileService.getById(15)).thenReturn(null);
		String actualMobile = orderDetails.orderMobile(15);
		assertEquals(expectedMobile, actualMobile, "Mobile not same");

	}

	@Test
	@DisplayName("check with exception")
	void testOrderException() throws MobileNotFoundException {

		String expected = "mobile not ordered";
		Mockito.when(mobileService.getById(15)).thenThrow(MobileNotFoundException.class);
		String actual = orderDetails.orderMobile(15);
		assertEquals(expected, actual, "Mobile not same");

	}

	@Test
	@DisplayName("check with empty object")
	void testOrderEmpty() throws MobileNotFoundException {

		String expected = "mobile not ordered";
		Mockito.when(mobileService.getById(15)).thenReturn(new Mobile());
		String actual = orderDetails.orderMobile(15);
		System.out.println(actual);
		assertEquals(expected, actual, "empty object expected");

	}

}
