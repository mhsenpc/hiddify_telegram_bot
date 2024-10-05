package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.entity.Order;
import com.mhsenpc.hiddifybot.bot.entity.User;
import com.mhsenpc.hiddifybot.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewOrderNotifier {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private OrderFormatter orderFormatter;

    @Autowired
    private MessageService messageService;

    public void notify(Order order){

        List<User> admins = userRepository.getAdmins();

        for(User user: admins){
            messageService.send(user.getChatId(), getMessage(order));
        }
    }

    private String getMessage(Order order){

        return "یک سفارش جدید ثبت شد" + System.lineSeparator()
                + orderFormatter.getFormattedOrder(order);
    }
}
