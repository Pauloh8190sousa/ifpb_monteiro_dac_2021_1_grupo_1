package com.library.services;

import com.library.models.Address;
import com.library.models.Author;
import com.library.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public void deleteById(Long idAddress) {
        Address address = addressRepository.findById(idAddress).orElseThrow();
        addressRepository.delete(address);
    }

    public Address findById(Long idAddress) {
        return addressRepository.findById(idAddress).orElseThrow();
    }

}
