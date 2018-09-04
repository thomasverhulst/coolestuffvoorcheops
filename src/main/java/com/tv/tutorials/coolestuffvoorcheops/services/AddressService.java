package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.tutorials.coolestuffvoorcheops.model.Address;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.AddressRepository;

@Service
public class AddressService implements IAddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> getAllAdresses() {
		// TODO Auto-generated method stub
		List<Address> list = new ArrayList<>();
		 addressRepository.findAll().forEach(e -> list.add(e));
		return list;

	}

	@Override
	public Address getAddressById(int addressId) {
		// TODO Auto-generated method stub
		// Address obj =addressRepository.findById(addressId).get();
		// return obj;
		Address e = addressRepository.findById(addressId).get();
		return e;
	}

	@Override
	public Address addAddress(Address address) {
		// TODO Auto-generated method stub
		addressRepository.save(address);

		// List<Address> list = addressRepository.findByStreetNameAndPostalCode
		// (address.getStreetName(), address.getPostalCode());
		// if (list.size() > 0) {
		// return false;
		// } else {
		// addressRepository.save(address);
		/// return true;
		// }
		System.out.println("hoihoi");
		return address;
		// return false;

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
//https://www.concretepage.com/spring-boot/spring-boot-crudrepository-example