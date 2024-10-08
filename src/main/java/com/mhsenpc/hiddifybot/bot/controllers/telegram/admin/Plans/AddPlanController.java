package com.mhsenpc.hiddifybot.bot.controllers.telegram.admin.Plans;

import com.mhsenpc.hiddifybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.hiddifybot.bot.dto.CreatePlanRequest;
import com.mhsenpc.hiddifybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.hiddifybot.bot.entity.Plan;
import com.mhsenpc.hiddifybot.bot.enums.UserStep;
import com.mhsenpc.hiddifybot.bot.pages.admin.ViewPlansPage;
import com.mhsenpc.hiddifybot.bot.repository.PlanRepository;
import com.mhsenpc.hiddifybot.bot.services.UserStepService;
import com.mhsenpc.hiddifybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddPlanController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private PlanRepository planRepository;

    @Override
    public void invoke(Update update) {

        if (message.equals(ViewPlansPage.BTN_ADD_PLAN)) {

            userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_CREATE_PLAN_WAIT_FOR_MONTH, new CreatePlanRequest()));
            sendMessage("تعرفه چند ماهه تعریف می کنید؟ لطفا تعداد ماه را به عدد وارد کنید");
            return;
        }

        CreatePlanRequest currentPayload = (CreatePlanRequest) currentStepWithPayload.getPayload();
        switch (currentStepWithPayload.getUserStep()) {

            case ADMIN_CREATE_PLAN_WAIT_FOR_MONTH -> {
                try {
                    int months = Integer.parseInt(message);

                    currentPayload.setMonths(months);

                    currentStepWithPayload.setUserStep(UserStep.ADMIN_CREATE_PLAN_WAIT_FOR_TRAFFIC);
                    userStepService.set(chatId, currentStepWithPayload);
                    sendMessage("میزان ترافیک این تعرفه (به واحد گیگابایت) چقدر باشید؟ لطفا فقط عدد وارد کنید");
                }
                catch (NumberFormatException exception){
                    sendMessage("لطفا تعداد ماه ها را بصورت صحیح وارد کنید");
                }

            }
            case ADMIN_CREATE_PLAN_WAIT_FOR_TRAFFIC -> {

                try {
                    int connections = Integer.parseInt(message);

                    currentPayload.setTrafficLimit(connections);

                    try{
                        Plan plan = new Plan();
                        plan.setMonths(currentPayload.getMonths());
                        plan.setTrafficLimit(currentPayload.getTrafficLimit());
                        planRepository.save(plan);

                        userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_VIEW_PLANS));
                        sendMessage("تعرفه با موفقیت ذخیره شد");
                    }
                    catch (NumberFormatException exception){
                        sendMessage("لطفا قیمت را بصورت صحیح وارد کنید");
                    }
                }
                catch (NumberFormatException exception){
                    sendMessage("لطفا میزان ترافیک مجاز را بصورت صحیح به گیگابایت بنویسید");
                }
            }
        }

    }
}
