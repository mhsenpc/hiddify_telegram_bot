package com.mhsenpc.hiddifybot.bot.controllers.telegram.admin.Plans;

import com.mhsenpc.hiddifybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.hiddifybot.bot.dto.PlanItemButtonCallback;
import com.mhsenpc.hiddifybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.hiddifybot.bot.entity.Plan;
import com.mhsenpc.hiddifybot.bot.enums.PlanCommandType;
import com.mhsenpc.hiddifybot.bot.enums.UserStep;
import com.mhsenpc.hiddifybot.bot.repository.PlanRepository;
import com.mhsenpc.hiddifybot.bot.services.PlanItemButtonCallbackSerializer;
import com.mhsenpc.hiddifybot.bot.services.UserStepService;
import com.mhsenpc.hiddifybot.telegram.methods.DeleteMessageMethod;
import com.mhsenpc.hiddifybot.telegram.types.Message;
import com.mhsenpc.hiddifybot.telegram.types.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@Slf4j
public class HandlePlansController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private PlanRepository planRepository;

    @Override
    public void invoke(Update update) {

        String callbackQueryData = "";
        if (update.getCallbackQuery() != null) {
            try {
                callbackQueryData = update.getCallbackQuery().getData();

                PlanItemButtonCallback response = PlanItemButtonCallbackSerializer.deserialize(callbackQueryData);
                int messageId = update.getCallbackQuery().getMessage().getMessageId();
                this.handleButtonCallback(response, messageId);

            } catch (Exception e) {
                this.sendMessage("متاسفانه مشکلی در دریافت فرمان شما پیش آمده است");
                log.error(e.getMessage());
            }

            userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_VIEW_PLANS));

        }

    }

    private void handleButtonCallback(PlanItemButtonCallback planItemButtonCallback, int messageId) {

        if(planItemButtonCallback.getCommandType() == PlanCommandType.REMOVE) {

            Optional<Plan> plan = planRepository.findById(planItemButtonCallback.getPlanId());
            if(plan.isEmpty()){
                sendMessage("این تعرفه وجود ندارد");
                return;
            }

            Plan planToDelete = plan.get();
            planToDelete.setDeletedAt(new Date());

            planRepository.save(planToDelete);

            DeleteMessageMethod deleteMessageMethod = new DeleteMessageMethod();
            deleteMessageMethod.setChatId(chatId);
            deleteMessageMethod.setMessageId(messageId);
            this.requestHandler.send(deleteMessageMethod, Message.class);
            sendMessage("تعرفه با موفقیت حذف شد");
        }
    }
}
