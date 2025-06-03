package edu.uptc.swii.cqrscontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import edu.uptc.swii.cqrscontroller.model.Customer;
import edu.uptc.swii.cqrscontroller.utils.JsonUtils;

@Service
public class CustomerEventProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private String type;
    private Customer customer;
    
    public String getType() {
        return type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void sendMessage(String topic, Customer customer) {
        String message = new String();
        JsonUtils jsonUtils = new JsonUtils();
        message = jsonUtils.toJson(customer);
        System.out.println("CustomerEventProducer sendMessage: "+message);
        kafkaTemplate.send(topic, message);
    }
}
