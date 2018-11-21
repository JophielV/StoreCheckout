package com.storecheckout.utils;

import org.joda.time.DateTime;

public final class IdGenerator {

    private static int intId = 0;

    public static String generateId() {
        DateTime dateTime = new DateTime();

        return String.format("%s%s%s%s%s%s",dateTime.getMonthOfYear(), dateTime.getDayOfMonth(),
                dateTime.getYear(), dateTime.getHourOfDay(), dateTime.getMinuteOfHour(),
                dateTime.getSecondOfMinute());
    }

    public static int generateIntId() {
        return intId++;
    }
}
