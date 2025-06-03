package edu.uptc.swii.cqrscontroller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uptc.swii.cqrscontroller.model.Order;
import edu.uptc.swii.cqrscontroller.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean save(Order order){
        boolean flag = false;
        Order processOrder = orderRepository.saveAndFlush(order);
        if (processOrder!=null){
            
            flag=true;
        }
        return flag;
    }

    public boolean delete(Order order){
        boolean flag = false;
        try{
            orderRepository.delete(order);
            flag=true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public Order findById(String id){
        Order order=null;
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent())
            order = optionalOrder.get();
        return order;
    }
    
    public List<Order> findAll(){
        List<Order> listOrder = new ArrayList<Order>();
        Iterable<Order> orders = orderRepository.findAll();
        orders.forEach((o) ->{
            listOrder.add(o);
        });
        return listOrder;
    }

    public void update(Order order) {
        orderRepository.saveAndFlush(order);
    }
    
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

}
