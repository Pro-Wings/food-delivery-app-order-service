package com.prowings.order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prowings.order_service.dto.RestaurantDto;
import com.prowings.order_service.dto.UserDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ValidationService {

	@Autowired
	private final RestTemplate restTemplate;

	@Value("${user.service.url}")
	private String userServiceUrl;

	@Value("${restaurant.service.url}")
	private String restaurantServiceUrl;

	public ValidationService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

//	@CircuitBreaker(name = "userService", fallbackMethod = "userServiceFallback")
	@Retry(name = "userService", fallbackMethod = "userServiceFallback")
	public UserDto validateUser(Long userId) {
		String url = userServiceUrl + userId;
		log.info(">>>>>>>>> Validating user with URL: {} and userid : {}", url, userId);
		return restTemplate.getForObject(url, UserDto.class);
	}

//	@CircuitBreaker(name = "restaurantService", fallbackMethod = "restaurantServiceFallback")
	public RestaurantDto validateRestaurant(Long restaurantId) {
		String url = restaurantServiceUrl + restaurantId;
		log.info("Validating restaurant with URL: {} and restaurantId : {}", url, restaurantId);
		return restTemplate.getForObject(url, RestaurantDto.class);
	}

	// Fallback Methods
	public UserDto userServiceFallback(Long userId, Throwable throwable) {
		
		log.info("User Service is currently unavailable. Please try again later failure Reason : {}", throwable.getMessage());
	
		return null;
		
	}

	public RestaurantDto restaurantServiceFallback(Long restaurantId, Throwable throwable) {
		
		log.info("Restaurant Service is currently down. Please try again later.");
		
		return null;
	}
}
