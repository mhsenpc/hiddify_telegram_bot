package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.config.ConfigurationManager;
import com.mhsenpc.hiddifybot.bot.entity.Client;
import com.mhsenpc.hiddifybot.bot.entity.Order;
import com.mhsenpc.hiddifybot.bot.enums.ConfigName;
import com.mhsenpc.hiddifybot.bot.enums.OrderStatus;
import com.mhsenpc.hiddifybot.bot.repository.ClientRepository;
import com.mhsenpc.hiddifybot.bot.repository.OrderRepository;
import com.mhsenpc.hiddifybot.hiddify.dto.CreateUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
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

        CreateUserResponseDTO createUserResponseDTO = clientDirector.build(order);
        if(createUserResponseDTO.getUuid() != null) {
            String profileUrl = buildProfileUrl(createUserResponseDTO);
            setOrderStatusToConfirmed(order);
            sendAccountDetailsToUser(order, profileUrl);
            storeClient(order, profileUrl, createUserResponseDTO);
        }
        else{
            sendFailedMessage(order);
        }
    }

    private String buildProfileUrl(CreateUserResponseDTO createUserResponseDTO) {

        String profilePattern = configurationManager.getConfig(ConfigName.PROFILE_LINK);
        return String.format(
                profilePattern,
                createUserResponseDTO.getUuid(),
                createUserResponseDTO.getName()
        );
    }

    private void storeClient(Order order, String profileUrl, CreateUserResponseDTO createUserResponseDTO) {

        Client client = new Client();
        client.setName(createUserResponseDTO.getName());
        client.setUrl(profileUrl);
        client.setOrder(order);
        client.setUser(order.getUser());
        client.setCreatedAt(new Date());
        client.setUuid(createUserResponseDTO.getUuid());

        int packageDay = createUserResponseDTO.getPackage_days();
        LocalDate expireDateLocal = LocalDate.now().plusDays(packageDay);
        Date expireDate = Date.from(expireDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        client.setValidUntil(expireDate);
        clientRepository.save(client);
    }

    private void setOrderStatusToConfirmed(Order order){

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
    }

    private void sendAccountDetailsToUser(Order order, String profileUrl){

        String message = "برای اتصال به وی پی ان باید این کانفیگ را کپی کنید" + System.lineSeparator() ;
        String receiverChatId = order.getUser().getChatId();

        String qrCodeUrl = qrCodeService.getQRCodeUrl(profileUrl);
        messageService.send(receiverChatId, message);

        messageService.sendPhoto(receiverChatId, qrCodeUrl, profileUrl);
    }

    private void sendFailedMessage(Order order){

        String message = "متاسفانه اکانت درخواستی ساخته نشد. مشکل فنی وجود دارد" + System.lineSeparator() ;
        String receiverChatId = order.getUser().getChatId();

        messageService.send(receiverChatId, message);
    }
}
