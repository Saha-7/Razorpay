package com.saha7pritam.razorpay.payment.service;

import com.saha7pritam.razorpay.payment.dto.request.CreateOrderRequest;
import com.saha7pritam.razorpay.payment.dto.response.OrderResponse;

import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, CreateOrderRequest request);
}
