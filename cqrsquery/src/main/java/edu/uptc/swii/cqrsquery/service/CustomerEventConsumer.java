package edu.uptc.swii.cqrsquery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import edu.uptc.swii.cqrsquery.model.Customer;
import edu.uptc.swii.cqrsquery.utils.JsonUtils;

@Service
public class CustomerEventConsumer {
    @Autowired
    private CustomerService customerService;

    @KafkaListener(topics = "add-customer-topic", groupId = "customer-group")
    public void addCustomerConsume(String message) {
        message=message.replace("\\","");
        message=message.substring(1, message.length()-1);
        System.out.println("Add Customer - Received Message: " + message);
        JsonUtils jsonUtils = new JsonUtils();
        Customer savedCustomer = jsonUtils.fromJson(message, Customer.class);
        customerService.addCustomer(savedCustomer);

    }

    @KafkaListener(topics = "update-customer-topic", groupId = "customer-group")
    public void updateCustomerConsume(String message) {
        System.out.println("Update Customer - Received Message: " + message);
        JsonUtils jsonUtils = new JsonUtils();
        Customer savedCustomer = jsonUtils.fromJson(message, Customer.class);
        customerService.updateCustomer(savedCustomer);
    }

    @KafkaListener(topics = "delete-customer-topic", groupId = "customer-group")
    public void deleteCustomerConsume(String message) {
        System.out.println("Delete Customer - Received Message: " + message);
        JsonUtils jsonUtils = new JsonUtils();
        Customer savedCustomer = jsonUtils.fromJson(message, Customer.class);
        customerService.deleteCustomer(savedCustomer);
    }
}
