package com.saha7pritam.razorpay.merchant.service;


import com.saha7pritam.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.saha7pritam.razorpay.merchant.dto.response.ApiKeyCreateResponse;

import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);
}
