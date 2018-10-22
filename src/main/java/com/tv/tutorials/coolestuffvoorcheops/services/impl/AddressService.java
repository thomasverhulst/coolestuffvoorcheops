package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.tutorials.coolestuffvoorcheops.models.Address;
import com.tv.tutorials.coolestuffvoorcheops.repositories.AddressRepository;
import com.tv.tutorials.coolestuffvoorcheops.services.IAddressService;

@Service
public class AddressService implements IAddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> getAllAdresses() {
		List<Address> list = new ArrayList<>();
		addressRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Address getAddressById(int addressId) {
		Address e = addressRepository.findById(addressId).get();
		return e;
	}

	@Override
	public Address addAddress(Address address) {
		addressRepository.save(address);
		return address;
	}

	@Override
	public void updateAddress(Address address) {
		addressRepository.save(address);
	}

	@Override
	public void deleteAdress(int addressId) {
		addressRepository.delete(getAddressById(addressId));
	}

}
