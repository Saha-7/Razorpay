package com.saha7pritam.razorpay.merchant.dto.request;

import com.saha7pritam.razorpay.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignupRequest(
        @NotNull(message = "Name is required")
        @Size(max = 50, message = "Name must not exceed 50 characters")
        String name,

        @Email
        @NotNull(message = "Email is required")
        String email,

        @NotNull(message = "Password is required")
        @Size(min = 8, message = "Password must be 8 characters long at least")
        String password,

        @Size(max=50, message = "Business name must not exceed 50 characters")
        String businessName,

        BusinessType businessType
) {
}
