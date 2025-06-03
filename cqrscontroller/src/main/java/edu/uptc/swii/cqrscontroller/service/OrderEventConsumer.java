package edu.uptc.swii.cqrscontroller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import edu.uptc.swii.cqrscontroller.model.Order;
import edu.uptc.swii.dddordermgmt.domain.utils.JsonUtils;


@Service
public class OrderEventConsumer {
   
    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "addorder_events", groupId = "order_group")
    public void handleAddOrderEvent(String order) {
        // Limpia el mensaje si viene con comillas extra y escapes
        if (order != null && order.startsWith("\"") && order.endsWith("\"")) {
            order = order.substring(1, order.length() - 1).replace("\\\"", "\"");
        }
        JsonUtils jsonUtils = new JsonUtils();
        Order receiveAddOrder = jsonUtils.fromJson(order, Order.class);
        orderService.save(receiveAddOrder);
    }

    @KafkaListener(topics = "editorder_events", groupId = "order_group")
    public void handleEditOrderEvent(String order) {
        if (order != null && order.startsWith("\"") && order.endsWith("\"")) {
            order = order.substring(1, order.length() - 1).replace("\\\"", "\"");
        }
        JsonUtils jsonUtils = new JsonUtils();
        Order receiveEditOrder = jsonUtils.fromJson(order, Order.class);
        orderService.save(receiveEditOrder);
    }

    @KafkaListener(topics = "findorderbyid_events", groupId = "order_group")
    public Order handleFindOrderByIDEvent(String order) {
        Order orderReceived = orderService.findById(order);
        return orderReceived;
    }

    @KafkaListener(topics = "findallorders_events", groupId = "order_group")
    public List<Order> handleFindAllOrders() {
        List<Order> ordersReceived = orderService.findAll();
        return ordersReceived;
    }
}
