package com.storecheckout.utils;

import org.joda.time.DateTime;

public final class IdGenerator {

    public static String generateId() {
        DateTime dateTime = new DateTime();

        return String.format("%s%s%s%s%s%s",dateTime.getMonthOfYear(), dateTime.getDayOfMonth(),
                dateTime.getYear(), dateTime.getHourOfDay(), dateTime.getMinuteOfHour(),
                dateTime.getSecondOfMinute());
    }
}
