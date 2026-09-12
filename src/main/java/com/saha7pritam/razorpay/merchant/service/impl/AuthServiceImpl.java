package com.saha7pritam.razorpay.merchant.service.impl;

import com.saha7pritam.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.saha7pritam.razorpay.merchant.dto.response.MerchantResponse;
import com.saha7pritam.razorpay.merchant.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Override
    public MerchantResponse signup(MerchantSignupRequest request) {
        return null;
    }
}
