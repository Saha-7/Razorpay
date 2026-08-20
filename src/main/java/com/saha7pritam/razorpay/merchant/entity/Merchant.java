package com.saha7pritam.razorpay.merchant.entity;


import com.saha7pritam.razorpay.common.enums.BusinessType;
import com.saha7pritam.razorpay.common.enums.MerchantStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(length = 20)
    private  String contactNumber;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Column(length = 100)
    private String businessName;

    @Column(length = 255)
    private String websiteUrl;

    @Column(length = 200, nullable = false)
    @Enumerated(EnumType.STRING)
    private MerchantStatus status = MerchantStatus.PENDING_KYC;

    @Column(length = 20)
    private String gstId;

    @Column(length = 20)
    private String panId;

    @Column(length = 50)
    private String settlementBankAccount;

    @Column(length = 50)
    private String settlementBankIfsc;

    @Column(length = 200)
    private String settlementBankAccountHolderName;

}
