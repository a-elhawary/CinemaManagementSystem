package helper;

import exceptions.InvalidMonthNameException;

import java.util.Calendar;

public class Months {
    public static final String[] names = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static final int[] numberOfDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static String[] getMonthNames(int from, int to){
        String[] namesSlice = new String[to - from + 1];
        for(int i = from; i <= to; i++){
           namesSlice[i - from] = names[i];
        }
        return namesSlice;
    }

    public static String[] generateDaysUntil(int startDay, int endDay){
        String[] result = new String[endDay - startDay + 1];
        for(int i = startDay; i <= endDay;i++){
            result[i - startDay]  = String.valueOf(i);
        }
        return result;
    }

    public static String[] getDaysArray(String monthName){
        int monthIndex = -1;
        for(int i = 0; i < names.length; i++){
            if(names[i].equals(monthName)){
                monthIndex = i;
            }
        }
        if(monthIndex == -1) throw new InvalidMonthNameException(monthName);
        String[] days = new String[numberOfDays[monthIndex]];
        for(int i = 0; i < days.length; i++){
            days[i] = String.valueOf(i+1);
        }
        return days;
    }
}
