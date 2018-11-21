package com.cheops.candidatemanager.services;

import java.util.List;

import com.cheops.candidatemanager.models.Address;

public interface IAddressService {

	List<Address> getAllAdresses();

	Address getAddressById(int addressId);

	Address addAddress(Address address);

	void updateAddress(Address address);

	void deleteAddress(int addressId);

}
