package com.saha7pritam.razorpay.payment.entity;

import com.saha7pritam.razorpay.common.entity.Money;
import com.saha7pritam.razorpay.common.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.SQLType;
import java.util.Map;
import java.util.UUID;


@Entity
@Table(name = "order_record")
public class OrderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // no foreign (for cross service boundary) key relationship to Merchant entity, just store the merchantId. Because of the Logical 4 layer separation
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private Integer attempts=0;

    @JdbcTypeCode((SqlTypes.JSON))       // This annotation specifies that the field should be treated as a JSON type in the database. It is used to map the Java Map<String, Object> to a JSON column in the database. This is particularly useful when you want to store structured data that doesn't fit neatly into a relational schema.
    @Column(columnDefinition = "jsonb")  // PostgreSQL specific column type for storing JSON data
    private Map<String, Object> notes;

    @Column(nullable = false)
    private java.time.LocalDateTime expiresAt;


}
