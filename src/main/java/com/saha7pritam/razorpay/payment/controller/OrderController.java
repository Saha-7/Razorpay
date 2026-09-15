package com.saha7pritam.razorpay.payment.controller;


import com.saha7pritam.razorpay.payment.dto.request.CreateOrderRequest;
import com.saha7pritam.razorpay.payment.dto.response.OrderResponse;
import com.saha7pritam.razorpay.payment.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    UUID merchantId = UUID.fromString("772d097e-dd6c-4025-ae44-7bf7495e359f");

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid CreateOrderRequest request){
        return ResponseEntity.ok(orderService.create(merchantId, request));
    }

}
