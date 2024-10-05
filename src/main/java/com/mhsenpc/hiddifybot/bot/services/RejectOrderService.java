package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.entity.Order;
import com.mhsenpc.hiddifybot.bot.enums.OrderStatus;
import com.mhsenpc.hiddifybot.bot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RejectOrderService {

    private Order order;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MessageService messageService;

    public void reject(Order order){

        setOrder(order);
        setOrderStatusToRejected();
        sendRejectedMessageToUser();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOrderStatusToRejected() {
        order.setStatus(OrderStatus.REJECTED);
        orderRepository.save(order);
    }

    public void sendRejectedMessageToUser() {

        String message = "متاسفانه سفارش شما به شماره " + order.getOrderId() + " مورد تایید مدیر سیستم قرار نگرفت";
        String receiver = order.getUser().getChatId();

        messageService.send(receiver, message);
    }
}
