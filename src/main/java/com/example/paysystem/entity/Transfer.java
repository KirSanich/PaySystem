package com.example.paysystem.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transfer")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "transfer_money")
    private BigDecimal transferMoney;

    @Column(name = "date_transfer")
    private OffsetDateTime dateTransfer;

    @ManyToOne
    @JoinColumn(name="account_details_id")
    private AccountDetails accountDetails;

}
