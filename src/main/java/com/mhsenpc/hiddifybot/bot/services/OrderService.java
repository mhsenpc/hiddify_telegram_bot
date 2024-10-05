package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.entity.Order;
import com.mhsenpc.hiddifybot.bot.enums.OrderStatus;
import com.mhsenpc.hiddifybot.bot.repository.OrderRepository;
import com.mhsenpc.hiddifybot.xui.exceptions.InboundNotRetrievedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ConfirmOrderService confirmOrderService;

    @Autowired
    private RejectOrderService rejectOrderService;

    public String getReport(){

        long allOrdersCount = orderRepository.count();
        long confirmedOrdersCount = orderRepository.countByStatus(OrderStatus.CONFIRMED.getValue());
        long rejectedOrdersCount = orderRepository.countByStatus(OrderStatus.REJECTED.getValue());
        long pendingOrdersWithReceipt = orderRepository.countByStatusAndPhotosIsNotEmpty(OrderStatus.PENDING.getValue());
        return String.format(
                "کل سفارشات %s " + "\n" +
                        "تایید شده ها %s " + "\n" +
                        "رد شده ها %s " + "\n" +
                        "منتظر تایید %s " + "\n",
                allOrdersCount, confirmedOrdersCount, rejectedOrdersCount, pendingOrdersWithReceipt);
    }

    public void acceptOrder(int orderId) throws InboundNotRetrievedException, IOException {

        Order order = orderRepository.findById(orderId).get();
        confirmOrderService.confirm(order);
    }

    public void rejectOrder(int orderId){

        Order order = orderRepository.findById(orderId).get();
        rejectOrderService.reject(order);
    }
}
