package com.mhsenpc.hiddifybot.bot.repository;

import com.mhsenpc.hiddifybot.bot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByChatId(String chatId);


    @Query("SELECT u FROM User u WHERE u.role = 7")
    List<User> getAdmins();
}