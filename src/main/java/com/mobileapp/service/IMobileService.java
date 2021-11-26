package com.mobileapp.service;

import java.util.List;

import com.mobileapp.exception.MobileNotFoundException;
import com.mobileapp.model.Mobile;

public interface IMobileService {
Mobile getById(int id) throws MobileNotFoundException;
List<Mobile> getByBrand(String brand) throws MobileNotFoundException;
}
