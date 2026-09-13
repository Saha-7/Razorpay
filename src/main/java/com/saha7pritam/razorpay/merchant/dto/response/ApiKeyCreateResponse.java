package com.saha7pritam.razorpay.merchant.dto.response;

import com.saha7pritam.razorpay.common.enums.Environment;

import java.util.UUID;

public record ApiKeyCreateResponse(
        UUID id,
        String KeyId,
        String KeySecret,
        Environment environment
) {

}
