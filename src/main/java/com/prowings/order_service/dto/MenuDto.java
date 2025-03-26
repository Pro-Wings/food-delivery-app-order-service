package com.prowings.order_service.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuDto {

    private Long id;
    private String itemName;
    private double price;
    private boolean available;

    @JsonBackReference
    private RestaurantDto restaurant;
}
