package com.mhsenpc.hiddifybot.bot.repository;

import com.mhsenpc.hiddifybot.bot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    long countByStatus(int status);

    long countByStatusAndPhotosIsNotEmpty(int orderStatus);

    List<Order> findByStatusAndPhotosIsNotEmpty(int status);
}