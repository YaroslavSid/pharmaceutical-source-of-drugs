package com.alevel.telegram.bot.handlers;

import com.alevel.telegram.bot.cache.FindDrugFlowData;
import com.alevel.telegram.bot.cache.FindDrugInTimeRangeData;
import com.alevel.telegram.bot.cache.FindDrugInTimeRangeStatus;
import com.alevel.telegram.bot.cache.state.FindDrugFlowState;
import com.alevel.telegram.bot.cache.state.FindDrugInTimeRangeFlowState;
import com.alevel.telegram.bot.services.BotServices;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;


@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallBackQueryHandler implements Handler<CallbackQuery> {

    BotServices botServices;
    FindDrugFlowState findDrugFlowState;
    FindDrugInTimeRangeFlowState findDrugInTimeRangeFlowState;

    @Autowired
    public CallBackQueryHandler(BotServices botServices, FindDrugFlowState findDrugFlowState,
                                FindDrugInTimeRangeFlowState findDrugInTimeRangeFlowState) {
        this.botServices = botServices;
        this.findDrugFlowState = findDrugFlowState;
        this.findDrugInTimeRangeFlowState = findDrugInTimeRangeFlowState;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        switch (callbackQuery.getData()) {
            case "drugs":
                return SendMessage.builder()
                        .text(String.valueOf(botServices.st()))
                        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                        .build();
            case "companies":
                return SendMessage.builder()
                        .text("companies")
                        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                        .build();
            case "one_drug":
                findDrugFlowState.add(FindDrugFlowData.builder()
                        .chatId(callbackQuery.getMessage().getChatId())
                        .build());

                return SendMessage.builder()
                        .text("Input name a drug")
                        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                        .build();
            case "time":
                findDrugInTimeRangeFlowState.add(FindDrugInTimeRangeData.builder()
                        .chatId(callbackQuery.getMessage().getChatId())
                        .status(FindDrugInTimeRangeStatus.INPUT_FIRST_DATE)
                        .build());

                return SendMessage.builder()
                        .text("Input 'from' date (dd.mm.yyyy)")
                        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
                        .build();
        }

        log.warn("Unknown data in CallbackQuery received: " + callbackQuery.getData());
        throw new RuntimeException("Illegal callback data: " + callbackQuery.getData());
    }


}
