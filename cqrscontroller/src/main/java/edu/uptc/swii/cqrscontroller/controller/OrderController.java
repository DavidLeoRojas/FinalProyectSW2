package edu.uptc.swii.cqrscontroller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uptc.swii.cqrscontroller.model.Order;
import edu.uptc.swii.cqrscontroller.service.OrderEventProducer;
import edu.uptc.swii.cqrscontroller.service.OrderService;
import edu.uptc.swii.dddordermgmt.domain.utils.JsonUtils;


@RestController
public class OrderController {
    @Autowired
    private OrderEventProducer orderEventProducer;

    @Autowired
    private OrderService orderService;

    private static JsonUtils jsonUtils= new JsonUtils();

    @PostMapping("/addorder")
    public String sendMessageAddOrder(@RequestBody Order order) {
        orderService.save(order); // Guarda en la base relacional
        orderEventProducer.sendAddOrderEvent(order); // Envía evento a Kafka
        return "Orden guardada en base relacional y enviada a Kafka";
    }

    @PostMapping("/editorder")
    public String sendMessageEditOrder(@RequestBody Order order) {
        orderService.update(order); // Actualiza en la base relacional
        orderEventProducer.sendEditOrderEvent(order); // Envía evento a Kafka
        return "Orden actualizada en base relacional y enviada a Kafka";
    }
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable String id) {
        return orderService.findById(id);
    }
    @DeleteMapping("/deleteorder/{id}")
    public String deleteOrder(@PathVariable String id) {
        orderService.deleteById(id); // Elimina en la base relacional
        orderEventProducer.sendDeleteOrderEvent(id); // Envía evento a Kafka para MongoDB
        return "Orden eliminada en base relacional y enviada a Kafka";
    }
}