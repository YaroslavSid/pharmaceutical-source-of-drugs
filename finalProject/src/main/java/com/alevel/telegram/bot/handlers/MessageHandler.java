package com.alevel.telegram.bot.handlers;


import com.alevel.telegram.bot.cache.FindDrugFlowData;
import com.alevel.telegram.bot.cache.FindDrugInTimeRangeData;
import com.alevel.telegram.bot.cache.FindDrugInTimeRangeStatus;
import com.alevel.telegram.bot.cache.state.FindDrugFlowState;
import com.alevel.telegram.bot.cache.state.FindDrugInTimeRangeFlowState;
import com.alevel.telegram.bot.services.BotServices;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MessageHandler implements Handler<Message> {

    @Value("${answer.menuFirst}")
    String menuFirst;
    @Value("${answer.menuSecond}")
    String menuSecond;

    BotServices botServices;
    FindDrugFlowState findDrugFlowState;
    FindDrugInTimeRangeFlowState findDrugInTimeRangeFlowState;

    @Override
    public BotApiMethod<?> handle(Message message) {
        if (message.hasText()) {

            Optional<FindDrugFlowData> drugFlowData = findDrugFlowState.findBy(message.getChatId());
            if (drugFlowData.isPresent()) {
                // todo make flag bot returning drug details
                FlagBot flagBot = botServices.findrug(message.getText());
                SendMessage sendMessage = buttonThree(message, flagBot);

                findDrugFlowState.remove(drugFlowData.get());

                return sendMessage;
            }

            Optional<FindDrugInTimeRangeData> data = findDrugInTimeRangeFlowState.findBy(message.getChatId());
            if (data.isPresent()) {
                FindDrugInTimeRangeData findDrugInTimeRangeData = data.get();

                if (findDrugInTimeRangeData.getStatus() == FindDrugInTimeRangeStatus.INPUT_FIRST_DATE) {
                    Date fromDate;
                    try {
                        fromDate = new SimpleDateFormat("dd.MM.yyyy").parse(message.getText());
                    } catch (ParseException e) {
                        log.warn("Illegal date format.");
                        return SendMessage.builder()
                                .chatId(String.valueOf(message.getChatId()))
                                .text("Illegal date format. Try again please.")
                                .build();
                    }
                    findDrugInTimeRangeData.setFrom(fromDate);
                    findDrugInTimeRangeData.setStatus(FindDrugInTimeRangeStatus.INPUT_SECOND_DATE);

                    return SendMessage.builder()
                            .chatId(String.valueOf(message.getChatId()))
                            .text("Input 'to' date (dd.mm.yyyy)")
                            .build();
                } else if (findDrugInTimeRangeData.getStatus() == FindDrugInTimeRangeStatus.INPUT_SECOND_DATE) {
                    Date toDate;
                    try {
                        toDate = new SimpleDateFormat("dd.MM.yyyy").parse(message.getText());
                    } catch (ParseException e) {
                        log.warn("Illegal date format.");
                        return SendMessage.builder()
                                .chatId(String.valueOf(message.getChatId()))
                                .text("Illegal date format. Try again please.")
                                .build();
                    }
                    findDrugInTimeRangeData.setTo(toDate);

                    Date from = findDrugInTimeRangeData.getFrom();
                    Date to = findDrugInTimeRangeData.getTo();
                    if (from.after(to)) {
                        Date op = to;
                        to = from;
                        from = op;
                    }
                    String result = botServices.findByTimeRange(from, to);

                    findDrugInTimeRangeFlowState.remove(findDrugInTimeRangeData);

                    return SendMessage.builder()
                            .chatId(String.valueOf(message.getChatId()))
                            .text(result)
                            .build();
                }
            }

            if (message.getText().equals("/start")) {
                return menu(menuFirst, message);
            } else if (message.getText().equals("/menu")) {
                return menu(menuSecond, message);
            } else {
                log.debug("Unsupported text command retrieved.");
                return SendMessage.builder()
                        .chatId(String.valueOf(message.getChatId()))
                        .text("Select item from menu")
                        .build();
            }
        }

        log.debug("Unsupported message arrived.");
        return new SendMessage();
    }

    private SendMessage menu(String menu, Message message) {
        return SendMessage.builder()
                .text(menu)
                .parseMode("HTML")
                .chatId(String.valueOf(message.getChatId()))
                .replyMarkup(keyBoard())
                .build();
    }

    private SendMessage buttonThree(Message message, FlagBot flagBot) {
        SendMessage answerButtonThree;
        if (flagBot != null) {
            answerButtonThree = SendMessage.builder()
                    .text(flagBot.getFlag())
                    .chatId(String.valueOf(message.getChatId()))
                    .build();
        } else {
            answerButtonThree = SendMessage.builder()
                    .text("not found")
                    .chatId(String.valueOf(message.getChatId()))
                    .build();
        }
        return answerButtonThree;
    }

    private InlineKeyboardMarkup keyBoard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();


        InlineKeyboardButton button1 = InlineKeyboardButton.builder()
                .text("List of innovative drugs")
                .callbackData("drugs")
                .build();
        InlineKeyboardButton button2 = InlineKeyboardButton.builder()
                .text("List all companies")
                .callbackData("companies")
                .build();
        InlineKeyboardButton button3 = InlineKeyboardButton.builder()
                .text("Find a drug")
                .callbackData("one_drug")
                .build();
        InlineKeyboardButton button4 = InlineKeyboardButton.builder()
                .text("Find approved medications in a specific time frame")
                .callbackData("time")
                .build();

        List<InlineKeyboardButton> list1 = new ArrayList<>();
        list1.add(button1);

        List<InlineKeyboardButton> list2 = new ArrayList<>();
        list2.add(button2);

        List<InlineKeyboardButton> list3 = new ArrayList<>();
        list3.add(button3);

        List<InlineKeyboardButton> list4 = new ArrayList<>();
        list4.add(button4);


        List<List<InlineKeyboardButton>> keyBoards = new ArrayList<>();
        keyBoards.add(list1);
        keyBoards.add(list2);
        keyBoards.add(list3);
        keyBoards.add(list4);

        inlineKeyboardMarkup.setKeyboard(keyBoards);
        return inlineKeyboardMarkup;
    }
}
