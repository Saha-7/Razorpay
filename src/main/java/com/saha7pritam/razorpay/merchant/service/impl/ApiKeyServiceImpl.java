package com.saha7pritam.razorpay.merchant.service.impl;

import com.saha7pritam.razorpay.common.exception.ResourceNotFoundException;
import com.saha7pritam.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.saha7pritam.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.saha7pritam.razorpay.merchant.entity.ApiKey;
import com.saha7pritam.razorpay.merchant.entity.Merchant;
import com.saha7pritam.razorpay.merchant.repository.ApiKeyRepository;
import com.saha7pritam.razorpay.merchant.repository.MerchantRepository;
import com.saha7pritam.razorpay.merchant.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;


    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));

        String KeyId = "rzp_"+ request.environment().name().toUpperCase() + "Big String"; //ToDo:

        String rawSecret = "xyz"; // ToDo:

        ApiKey apiKey = ApiKey.builder()
                .keyId(KeyId)
                .merchant(merchant)
                .keySecretHash(rawSecret) // ToDo: Encrypt using Bcrypt
                .environment(request.environment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(
                apiKey.getId(),
                KeyId,
                rawSecret,
                request.environment()
        );
    }

}
