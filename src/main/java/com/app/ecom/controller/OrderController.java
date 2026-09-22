package com.app.ecom.controller;

import com.app.ecom.dto.OrderResponseDTO;
import com.app.ecom.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
            @RequestHeader("X-User-ID") String userId
           ) {

        return  orderService.createOrder(userId).map(orderResponseDTO -> new ResponseEntity<>(orderResponseDTO, HttpStatus.CREATED) )
                .orElseGet(()->ResponseEntity.badRequest().build()); }
}
