package edu.uptc.swii.cqrscontroller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.uptc.swii.cqrscontroller.model.Customer;
import edu.uptc.swii.cqrscontroller.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository CustomerRepository;
    private final CustomerEventProducer customerEventProducer;
    private final String ADD_CUSTOMER_TOPIC = "add-customer-topic";
    private final String UPDATE_CUSTOMER_TOPIC = "update-customer-topic";
    private final String DELETE_CUSTOMER_TOPIC = "delete-customer-topic";
    
    public CustomerService(edu.uptc.swii.cqrscontroller.repository.CustomerRepository customerRepository,
            CustomerEventProducer customerEventProducer) {
        CustomerRepository = customerRepository;
        this.customerEventProducer = customerEventProducer;
    }

    public Customer addCustomer(Customer customer){
        Customer savedCustomer = CustomerRepository.save(customer);
        customerEventProducer.sendMessage(ADD_CUSTOMER_TOPIC,customer);
        return savedCustomer;
    }

    public Customer updateCustomer(Customer customer){
        Customer savedCustomer = CustomerRepository.save(customer);
        customerEventProducer.sendMessage(UPDATE_CUSTOMER_TOPIC,customer);
        return savedCustomer;
    }

    public String deleteCustomer(Customer customer){
        String message=new String();
        try{
            CustomerRepository.delete(customer);
            customerEventProducer.sendMessage(DELETE_CUSTOMER_TOPIC,customer);
            message = "Customer deleted: "+customer.toString();
        }catch(Exception e){
            message = "Customer can't be deleted!";
            System.err.println(e.getMessage());
        }
        return message;
    }

    public List<Customer> findAll() {
        return CustomerRepository.findAll();
    }
}

