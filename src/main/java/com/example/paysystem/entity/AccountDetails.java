package com.example.paysystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "account_details")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_details_id_seq")
    @SequenceGenerator(name = "account_details_id_seq", sequenceName = "account_details_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "type",unique = true)
    private String type;

    @OneToMany(mappedBy = "accountDetails",fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    private List<Transfer> transferList;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
}
