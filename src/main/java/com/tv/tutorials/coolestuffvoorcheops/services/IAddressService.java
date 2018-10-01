package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.List;

import com.tv.tutorials.coolestuffvoorcheops.model.Address;

public interface IAddressService {

	List<Address> getAllAdresses();

	Address getAddressById(int addressId);

	Address addAddress(Address address);

	void updateAddress(Address address);

	void deleteAdress(int addressId);
}
