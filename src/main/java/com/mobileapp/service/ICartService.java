package com.mobileapp.service;

import java.util.List;

import com.mobileapp.exception.EmptyCartException;
import com.mobileapp.exception.MobileNotFoundException;
import com.mobileapp.model.Mobile;

public interface ICartService {
	List<Mobile> showCart() throws EmptyCartException;
	void addtoCart(Mobile mobile) throws MobileNotFoundException;
	boolean removeFromCart(Mobile mobile) throws MobileNotFoundException;
	

}
