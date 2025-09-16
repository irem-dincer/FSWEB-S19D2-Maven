package com.workintech.s18d4.controller;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping({"/workintech/customers", "/customer"})
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerResponse> getAll(){
        List<Customer> customers = customerService.findAll();
        return customers.stream()
                .map(this::convertToCustomerResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CustomerResponse get(@PathVariable Long id){  // Long'a çevir
        Customer customer = customerService.find(id);
        if (customer == null) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        return convertToCustomerResponse(customer);
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer){
        Customer saved = customerService.save(customer);
        return convertToCustomerResponse(saved);
    }

    // Eksik PUT metodu
    @PutMapping("/{id}")
    public CustomerResponse update(@RequestBody Customer customer, @PathVariable Long id){
        Customer existingCustomer = customerService.find(id);
        if (existingCustomer != null) {
            customer.setId(id);
            Customer updated = customerService.save(customer);
            return convertToCustomerResponse(updated);
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }


    @DeleteMapping("/{id}")
    public CustomerResponse remove(@PathVariable Long id){
        Customer customer = customerService.find(id);
        if (customer != null) {
            customerService.delete(id);
            return convertToCustomerResponse(customer);
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }


    private CustomerResponse convertToCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getEmail(),
                customer.getSalary()
        );
    }
}