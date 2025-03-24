package com.prowings.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.order_service.entity.Order;
import com.prowings.order_service.entity.OrderItem;
import com.prowings.order_service.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

	@Autowired
	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
		log.info("Placing a new order: {}", order);
//        return ResponseEntity.ok(orderService.placeOrder(order));
		try {
			// Ensure each OrderItem has a reference to the Order
			if (order.getOrderItems() != null) {
				for (OrderItem item : order.getOrderItems()) {
					item.setOrder(order); // Set the relationship
				}
			}

			orderService.placeOrder(order);
			return ResponseEntity.ok(orderService.placeOrder(order));
		} catch (Exception e) {
			log.error("Error while placing order: " + e.getMessage());
		}
		return null;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		log.info("Fetching order with ID: {}", id);
		return ResponseEntity.ok(orderService.getOrderById(id));
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
		log.info("Fetching orders for user ID: {}", userId);
		return ResponseEntity.ok(orderService.getOrdersByUser(userId));
	}

	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Order>> getOrdersByRestaurant(@PathVariable Long restaurantId) {
		log.info("Fetching orders for restaurant ID: {}", restaurantId);
		return ResponseEntity.ok(orderService.getOrdersByRestaurant(restaurantId));
	}
}