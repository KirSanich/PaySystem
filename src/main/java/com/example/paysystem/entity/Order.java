package com.example.paysystem.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id_seq")
    @SequenceGenerator(name = "orders_id_seq", sequenceName = "orders_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "prepay")
    private BigDecimal prepay;

    @Column(name = "order_date_from")
    private OffsetDateTime fromUtc;

    @Column(name = "order_date_to")
    private OffsetDateTime toUtc;

    @Column(name = "track_number")
    private Long trackNumber;

    @Column(name = "order_expire_date")
    private OffsetDateTime expireDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "consumer_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_details_id")
    private AccountDetails accountDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flat_id")
    private Flat flat;

}
