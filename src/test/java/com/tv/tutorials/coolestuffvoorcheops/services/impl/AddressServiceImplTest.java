package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tv.tutorials.coolestuffvoorcheops.models.Address;
import com.tv.tutorials.coolestuffvoorcheops.models.CandidateSearchResolver;
import com.tv.tutorials.coolestuffvoorcheops.repositories.AddressRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddressServiceImplTest {

	private final static int testId =1;
	@Autowired
	private AddressService addressService;
	
	@Autowired 
	private AddressRepository addressRepository;
	@Test
	public void getAllAddressesTest() {
		List<Address> listAddresses = addressService.getAllAdresses();
		
		assertThat(!listAddresses.isEmpty());
	}
	
	@Test
	public void getAddressByIdTest() {
		Address e = addressRepository.findById(testId).get();
		assertThat(!e.getStreetName().isEmpty());
	}
	
	@Test
	public void deleteAdressTest() {
		//addressRepository.delete(getAddressById(addressId));
		//(!e.getStreetName().isEmpty());
	}
	
}
