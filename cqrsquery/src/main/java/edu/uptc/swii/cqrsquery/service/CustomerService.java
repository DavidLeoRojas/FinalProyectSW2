package edu.uptc.swii.cqrsquery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uptc.swii.cqrsquery.model.Customer;
import edu.uptc.swii.cqrsquery.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(Customer customer){
        customerRepository.insert(customer);        
    }

    public void updateCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }

    public List<Customer> getAllCustomers(){

        return (List<Customer>) (Customer) customerRepository.findAll();
    }

    public Customer getCustomerById(String id){
        Optional<Object> optCustomer = Optional.ofNullable(customerRepository.findById(id));
        return (Customer) optCustomer.get();
    }
    
}
