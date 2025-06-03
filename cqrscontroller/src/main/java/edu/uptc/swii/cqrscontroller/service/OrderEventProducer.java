package edu.uptc.swii.cqrscontroller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import edu.uptc.swii.cqrscontroller.model.Order;
import edu.uptc.swii.dddordermgmt.domain.utils.JsonUtils;


@Service
public class OrderEventProducer {
    private static final String TOPIC_ADD = "addorder_events";
    private static final String TOPIC_EDIT = "editorder_events";
    private static final String TOPIC_FINDBYID = "findorderrbyid_events";
    private static final String TOPIC_FINDALLORDERS = "findallorders_events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateAdd;
    public void sendAddOrderEvent(Order order){
        String json = null;
        JsonUtils jsonUtils = new JsonUtils();
        json=jsonUtils.toJson(order);   
        kafkaTemplateAdd.send(TOPIC_ADD, json);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateEdit;
    public void sendEditOrderEvent(Order order){
        String json = null;
        JsonUtils jsonUtils = new JsonUtils();
        json=jsonUtils.toJson(order);   
        kafkaTemplateEdit.send(TOPIC_EDIT, json);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateFindById;
    public void sendFindByOrderIDEvent(String id){
        kafkaTemplateFindById.send(TOPIC_FINDBYID, id);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateFindAllOrders;
    public void sendFindAllOrdersEvent(String orders){
        kafkaTemplateFindAllOrders.send(TOPIC_FINDALLORDERS, orders);
    }
    public void sendDeleteOrderEvent(String id) {
        kafkaTemplateAdd.send("deleteorder_events", id);
    }
}
