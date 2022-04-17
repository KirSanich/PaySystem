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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transfer_id_seq")
    @SequenceGenerator(name = "transfer_id_seq", sequenceName = "transfer_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "transfer_money")
    private BigDecimal transferMoney;

    @Column(name = "date_transfer")
    private OffsetDateTime dateTransfer;

    @Column(name = "type_operation")
    @Enumerated(EnumType.STRING)
    private Operation operation;

    @ManyToOne
    @JoinColumn(name="account_details_id")
    private AccountDetails accountDetails;


}
