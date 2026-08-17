package com.saha7pritam.razorpay.merchant.entity;

import com.saha7pritam.razorpay.common.enums.UserRole;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.UUID;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY)     // Many App users(current class) to one merchant  // FetchType.LAZY means that the associated AppUser data will be loaded lazily. But if you use Eager then it will load the associated Merchant data too.
    @JoinColumn(name="merchant_id")
    private Merchant merchant;

    @Column(unique = true, nullable = false, length = 200)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
