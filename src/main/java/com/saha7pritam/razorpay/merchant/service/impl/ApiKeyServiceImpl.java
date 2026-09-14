package com.saha7pritam.razorpay.merchant.service.impl;

import com.saha7pritam.razorpay.common.exception.ResourceNotFoundException;
import com.saha7pritam.razorpay.common.util.RandomizerUtil;
import com.saha7pritam.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.saha7pritam.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.saha7pritam.razorpay.merchant.dto.response.ApiKeyResponse;
import com.saha7pritam.razorpay.merchant.entity.ApiKey;
import com.saha7pritam.razorpay.merchant.entity.Merchant;
import com.saha7pritam.razorpay.merchant.repository.ApiKeyRepository;
import com.saha7pritam.razorpay.merchant.repository.MerchantRepository;
import com.saha7pritam.razorpay.merchant.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;


    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));

        String KeyId = "rzp_"+ request.environment().name().toLowerCase() + "_" + RandomizerUtil.randonBase64(24);

        String rawSecret = RandomizerUtil.randonBase64(40);

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

    @Override
    @Transactional
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        return apiKeyRepository.findByMerchant_Id(merchantId).stream()
                .map(apiKey -> new ApiKeyResponse(
                        apiKey.getId(),
                        apiKey.getKeyId(),
                        apiKey.getEnvironment(),
                        apiKey.isEnabled(),
                        apiKey.getLastUsedAt(), null
                ))
                .toList();
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {
        ApiKey key = apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", keyId));

        key.setEnabled(false);
    }

    @Override
    public ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey apiKey = apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", keyId));

        String newKeySecret = RandomizerUtil.randonBase64(40);
        apiKey.setPreviouskeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newKeySecret);
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodsExpiresAt(LocalDateTime.now().plusHours(24)); // 1 day grace period

        apiKey =  apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(
                apiKey.getId(),
                apiKey.getKeyId(),
                newKeySecret,
                apiKey.getEnvironment()
        );
    }
}
