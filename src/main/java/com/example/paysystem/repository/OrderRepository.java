package com.example.paysystem.repository;

import com.example.paysystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByTrackNumber(Long TrackNumber);

    @Modifying
    @Query(value = "DELETE FROM orders t WHERE t.id = :id",nativeQuery = true)
    @Transactional
    void deleteOrderWithIdByNative(@Param("id") Long id);
}
