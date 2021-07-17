package com.alevel.telegram.bot.cache;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FindDrugInTimeRangeData {

    Long chatId;
    FindDrugInTimeRangeStatus status;
    Date from;
    Date to;

}
