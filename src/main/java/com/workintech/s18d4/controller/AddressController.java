package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/workintech/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<Address> getAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address get(@PathVariable Long id) {
        Address address = addressService.find(id);
        if (address == null) {
            throw new RuntimeException("Address not found with id: " + id);
        }
        return address;
    }

    @PostMapping
    public Address save(@RequestBody Address address) {
        return addressService.save(address);
    }

    @PutMapping("/{id}")
    public Address update(@RequestBody Address address, @PathVariable Long id) {
        Address existingAddress = addressService.find(id);
        if (existingAddress != null) {
            address.setId(id);
            return addressService.save(address);
        } else {
            throw new RuntimeException("Address not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public Address remove(@PathVariable Long id) {
        Address address = addressService.find(id);
        if (address != null) {
            addressService.delete(id);
            return address;
        } else {
            throw new RuntimeException("Address not found with id: " + id);
        }
    }
}