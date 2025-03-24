package com.prowings.order_service.service;

import java.util.List;

import com.prowings.order_service.entity.Order;

public interface OrderService {

	Order placeOrder(Order order);

	Order getOrderById(Long orderId);

	List<Order> getOrdersByUser(Long userId);

	List<Order> getOrdersByRestaurant(Long restaurantId);

}
