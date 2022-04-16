package com.example.paysystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

}
