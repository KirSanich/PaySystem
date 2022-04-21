package com.example.paysystem.entity;

import lombok.*;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "flats")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flats_id_seq")
    @SequenceGenerator(name = "flats_id_seq", sequenceName = "flats_id_seq",allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount_rooms")
    private int amountRooms;

    @Column(name = "metric_area")
    private Double metricArea;

    @Column(name = "is_booked")
    private boolean isBooked;

    @Column(name = "price_for_one_day")
    private BigDecimal priceForDay;

    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "owner_id")
    private User user;

    @OneToMany(mappedBy = "flat",fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Order> orders;


}
