package com.mhsenpc.hiddifybot.bot.repository;

import com.mhsenpc.hiddifybot.bot.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}