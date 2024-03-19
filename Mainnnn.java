import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Mainnnn {

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите дату в формате dd.MM.yyyy:");
        String inputDate1 = scanner.nextLine();
        Date date1 = null;
        try {
            date1 = dateFormat.parse(inputDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date newDate1 = new Date(date1.getTime() + 45L * 24 * 60 * 60 * 1000);
        System.out.println("Дата после увеличения на 45 дней: " + dateFormat.format(newDate1));

        Date startOfYear = new Date(date1.getYear(), 0, 1);
        System.out.println("Дата после сдвига на начало года: " + dateFormat.format(startOfYear));

        Date newDate2 = calculateWorkingDays(date1, 10);
        System.out.println("Дата после увеличения на 10 рабочих дней: " + dateFormat.format(newDate2));

        System.out.println("Введите вторую дату в формате dd.MM.yyyy:");
        String inputDate2 = scanner.nextLine();
        Date date2 = null;
        try {
            date2 = dateFormat.parse(inputDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int workingDays = calculateWorkingDaysBetweenDates(date1, date2);
        System.out.println("Количество рабочих дней между введенными датами: " + workingDays);

    }

    public static Date calculateWorkingDays(Date date, int numDays) {
        for (int i = 0; i < numDays; i++) {
            date = new Date(date.getTime() + 24L * 60 * 60 * 1000);
            while (isWeekend(date)) {
                date = new Date(date.getTime() + 24L * 60 * 60 * 1000);
            }
        }
        return date;
    }

    public static int calculateWorkingDaysBetweenDates(Date date1, Date date2) {
        int workingDays = 0;
        Date currentDate = date1;
        while (currentDate.before(date2)) {
            if (!isWeekend(currentDate)) {
                workingDays++;
            }
            currentDate = new Date(currentDate.getTime() + 24L * 60 * 60 * 1000);
        }
        return workingDays;
    }

    public static boolean isWeekend(Date date) {
        int dayOfWeek = date.getDay();
        return dayOfWeek == 0 || dayOfWeek == 6;
    }
}