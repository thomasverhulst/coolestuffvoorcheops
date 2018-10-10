package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tv.tutorials.coolestuffvoorcheops.models.Address;
import com.tv.tutorials.coolestuffvoorcheops.repositories.AddressRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddressServiceImplTest {

	private final static int testId = 1;
	@Autowired
	private AddressService addressService;
	@Autowired
	private AddressRepository addressRepository;

	@Before
	public void fillDb() {
		Address ad1 = new Address("Staatsbaan", "2", "e", "3290", "België", "Vl-Brabant");
		Address ad2 = new Address("Provinciebaan", "2", "e", "3290", "België", "Vl-Brabant");
		Address ad3 = new Address("Tiensebaan", "2", "e", "3290", "België", "Vl-Brabant");

		ad1.setId(1);
		ad2.setId(2);
		ad3.setId(3);

		addressService.addAddress(ad1);
		addressService.addAddress(ad2);
		addressService.addAddress(ad3);
	}

	@After
	public void flushDb() {
		addressRepository.deleteAll();
	}

	@Test
	public void getAllAddressesTest() {
		List<Address> listAddresses = addressService.getAllAdresses();
		assertThat(listAddresses).isNotEmpty();
	}

	@Test
	public void getAddressByIdTest() {
		Address e = addressRepository.findById(addressService.getAllAdresses().get(0).getId()).get();
		assertThat(e.getStreetName()).isNotEmpty();
	}

	@Test
	public void deleteAdressTest() {
		Integer count = addressService.getAllAdresses().size();
		Address address = new Address("vvvvv", "2", "e", "3290", "België", "Vl-Brabant");
		address = addressService.addAddress(address);
		addressService.deleteAdress(address.getId());
		assertThat(addressService.getAllAdresses().size()).isEqualTo(count);
	}

	@Test
	public void addAddress() {
		Integer count = addressService.getAllAdresses().size();
		Address address = new Address("fdfsdfdsqsd", "2", "e", "3290", "België", "Vl-Brabant");
		addressService.addAddress(address);
		assertThat(addressService.getAllAdresses().size()).isEqualTo(++count);
	}

	@Test
	public void updateAddress() {
		Address adress = addressService.getAddressById(addressService.getAllAdresses().get(0).getId());
		adress.setStreetName("ddddddd");
		addressService.updateAddress(adress);
		assertThat(addressService.getAddressById(addressService.getAllAdresses().get(0).getId()).getStreetName())
				.isEqualToIgnoringCase("ddddddd");
	}

}
