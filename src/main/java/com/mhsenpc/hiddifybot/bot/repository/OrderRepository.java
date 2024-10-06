package com.mhsenpc.hiddifybot.bot.repository;

import com.mhsenpc.hiddifybot.bot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}