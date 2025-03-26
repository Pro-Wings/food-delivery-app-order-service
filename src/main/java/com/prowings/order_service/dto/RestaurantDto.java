package com.prowings.order_service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class RestaurantDto {

    private Long id;
    private String name;
    private String address;
    private double rating;

    @JsonManagedReference
    private List<MenuDto> menu;
}
