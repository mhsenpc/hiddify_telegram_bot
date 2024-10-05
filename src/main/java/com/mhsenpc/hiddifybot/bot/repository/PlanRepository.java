package com.mhsenpc.hiddifybot.bot.repository;

import com.mhsenpc.hiddifybot.bot.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

    @Query("SELECT p FROM Plan p WHERE p.deletedAt IS NULL")
    List<Plan> findAllNonDeletedPlans();
}