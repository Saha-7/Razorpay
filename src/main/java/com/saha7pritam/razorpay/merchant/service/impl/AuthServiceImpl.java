package com.saha7pritam.razorpay.merchant.service.impl;

import com.saha7pritam.razorpay.common.enums.MerchantStatus;
import com.saha7pritam.razorpay.common.enums.UserRole;
import com.saha7pritam.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.saha7pritam.razorpay.merchant.dto.response.MerchantResponse;
import com.saha7pritam.razorpay.merchant.entity.AppUser;
import com.saha7pritam.razorpay.merchant.entity.Merchant;
import com.saha7pritam.razorpay.merchant.repository.AppUserRepository;
import com.saha7pritam.razorpay.merchant.repository.MerchantRepository;
import com.saha7pritam.razorpay.merchant.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;

    private final MerchantRepository merchantRepository;

    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest request) {
        if(merchantRepository.existsByEmail(request.email())){
            throw new RuntimeException("Merchant with email already exists");
        }

        Merchant merchant = Merchant.builder()
                .name(request.name())
                .email(request.email())
                .businessName(request.businessName())
                .businessType(request.businessType())
                .status(MerchantStatus.PENDING_KYC)
                .build();
        
        merchantRepository.save(merchant);

        AppUser appUser = AppUser.builder()
                .email(request.email())
                .passwordHash(request.password())  // ToDo: Encrypt using Bcrypt
                .role(UserRole.OWNER)
                .merchant(merchant)
                .build();

        appUserRepository.save(appUser);

        return new MerchantResponse(
                merchant.getId(),
                merchant.getName(),
                merchant.getEmail(),
                merchant.getBusinessName(),
                merchant.getBusinessType(),
                merchant.getStatus()
        );
    }
}
