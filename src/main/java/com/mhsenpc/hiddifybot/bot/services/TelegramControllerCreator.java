package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.controllers.telegram.*;
import com.mhsenpc.hiddifybot.bot.controllers.telegram.admin.*;
import com.mhsenpc.hiddifybot.bot.controllers.telegram.admin.Plans.AddPlanController;
import com.mhsenpc.hiddifybot.bot.controllers.telegram.admin.Plans.HandlePlansController;
import com.mhsenpc.hiddifybot.bot.controllers.telegram.admin.Plans.ViewPlansController;
import com.mhsenpc.hiddifybot.bot.controllers.telegram.admin.Users.HandleUsersController;
import com.mhsenpc.hiddifybot.bot.controllers.telegram.admin.Users.ViewUsersController;
import com.mhsenpc.hiddifybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.hiddifybot.bot.pages.BasePage;
import com.mhsenpc.hiddifybot.bot.pages.UserHomePage;
import com.mhsenpc.hiddifybot.bot.pages.admin.AdminHomePage;
import com.mhsenpc.hiddifybot.bot.pages.admin.ViewPlansPage;
import com.mhsenpc.hiddifybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TelegramControllerCreator {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private MainMenuController mainMenuController;

    @Autowired
    private HandleUsersController handleUsersController;

    @Autowired
    private MyConfigsController myConfigsController;

    @Autowired
    private CreateConfigController createConfigController;

    @Autowired
    private ViewUsersController viewUsersController;

    @Autowired
    private ViewPlansController viewPlansController;

    @Autowired
    private HandlePlansController handlePlansController;

    @Autowired
    private AddPlanController addPlanController;

    protected String chatId;
    protected String message;
    protected UserStepWithPayload currentStepWithPayload;

    public TelegramController getController(Update update){

        if (update.getCallbackQuery() != null){
            message = Optional.ofNullable(update.getCallbackQuery().getMessage().getText()).orElse(message);
            chatId = update.getCallbackQuery().getFrom().getId();
        }
        else if(update.getMessage() != null){
            message = Optional.ofNullable(update.getMessage().getText()).orElse(message);
            chatId = update.getMessage().getFrom().getId();

        }
        currentStepWithPayload = userStepService.get(chatId);

        TelegramController telegramController = createController(update);
        telegramController.setMessage(message);
        telegramController.setChatId(chatId);
        telegramController.setCurrentStepWithPayload(currentStepWithPayload);
        return telegramController;
    }

    protected TelegramController createController(Update update){

        switch (message){
            case BasePage.BTN_BACK->{
                return mainMenuController;
            }
            case UserHomePage.BTN_MY_CONFIGS -> {
                return myConfigsController;
            }
            case AdminHomePage.BTN_CREATE_CONFIG -> {
                return createConfigController;
            }
            case AdminHomePage.BTN_VIEW_USERS -> {
                return viewUsersController;
            }
            case AdminHomePage.BTN_VIEW_PLANS -> {
                return viewPlansController;
            }
            case ViewPlansPage.BTN_ADD_PLAN -> {
                return addPlanController;
            }
        }

        if(currentStepWithPayload != null){
            return switch (currentStepWithPayload.getUserStep()) {
                case ADMIN_VIEW_USERS -> handleUsersController;
                case ADMIN_SELECT_PLAN -> createConfigController;
                case ADMIN_VIEW_PLANS -> viewPlansController;
                case ADMIN_WAITING_FOR_PLAN_COMMANDS -> handlePlansController;
                case ADMIN_CREATE_PLAN_WAIT_FOR_MONTH,  ADMIN_CREATE_PLAN_WAIT_FOR_TRAFFIC -> addPlanController;
                default -> mainMenuController;
            };
        }

        return mainMenuController;
    }
}
