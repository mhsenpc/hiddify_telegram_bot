package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.config.ConfigurationManager;
import com.mhsenpc.hiddifybot.bot.entity.Client;
import com.mhsenpc.hiddifybot.bot.entity.Order;
import com.mhsenpc.hiddifybot.bot.enums.OrderStatus;
import com.mhsenpc.hiddifybot.bot.repository.ClientRepository;
import com.mhsenpc.hiddifybot.bot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class ConfirmOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ClientDirector clientDirector;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ConfigurationManager configurationManager;

    @Autowired
    private QRCodeService qrCodeService;

    public void confirm(Order order) {

        XUIClient xuiClient = clientDirector.build(order);
        String profileUrl = "";
        setOrderStatusToConfirmed(order);
        sendAccountDetailsToUser(order, profileUrl);
        storeClient(order, xuiClient, profileUrl);
    }

    private void storeClient(Order order, XUIClient xuiClient, String profileUrl) {

        Client client = new Client();
        client.setName(xuiClient.getEmail());
        client.setUrl(profileUrl);
        client.setOrder(order);
        client.setUser(order.getUser());
        client.setCreatedAt(new Date());
        client.setUuid(xuiClient.getId());
        client.setValidUntil(new Date(xuiClient.getExpiryTime()));
        clientRepository.save(client);
    }

    private void setOrderStatusToConfirmed(Order order){

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
    }

    private void sendAccountDetailsToUser(Order order, String vpnConfig){

        String message = "برای اتصال به وی پی ان باید این کانفیگ را کپی کنید" + System.lineSeparator() ;
        String receiverChatId = order.getUser().getChatId();

        String qrCodeUrl = qrCodeService.getQRCodeUrl(vpnConfig);
        messageService.send(receiverChatId, message);

        messageService.sendPhoto(receiverChatId, qrCodeUrl, vpnConfig);
    }
}
