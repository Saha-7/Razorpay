package com.saha7pritam.razorpay.merchant.dto.request;

import com.saha7pritam.razorpay.common.enums.Environment;

import java.net.ProtocolFamily;

public record CreateApiKeyRequest(
        Environment environment
) {

}
