package edu.uptc.swii.cqrsquery.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.uptc.swii.cqrsquery.model.Customer;
import edu.uptc.swii.cqrsquery.repository.CustomerRepository;

@RestController
public class CustomerQueryController {
    private final CustomerRepository customerRepository;

    public CustomerQueryController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/allcustomers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(obj -> (Customer) obj)
                .toList();
    }

    @GetMapping("/findcustomerbyid")
    public Customer getCustomerById(@RequestParam String id) {
        return (Customer) customerRepository.findById(id).get();
    }
    
    
}
