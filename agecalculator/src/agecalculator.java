// Task 5 - Age Calculator
//Project code - MOLAP672
//List down all the possible times [minutes, hours, days, moths, years] from the given two input date and time
// LinkedIn profile - https://www.linkedin.com/in/aditi-bengani/
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class agecalculator {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first date and time(YYYY-MM-DDTHH:mm:ss):");
        String d1 = sc.nextLine();
        System.out.println("Enter the second date and time(YYYY-MM-DDTHH:mm:ss):");
        String d2 = sc.nextLine();

        LocalDateTime dt1 = LocalDateTime.parse(d1, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime dt2 = LocalDateTime.parse(d2, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        Duration duration = Duration.between(dt1,dt2);
        long minutes  = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();

        Period per = Period.between(dt1.toLocalDate(),dt2.toLocalDate());
        int months = per.getMonths();
        int years = per.getYears();

        System.out.println("Minutes: "+minutes+"\nHours: "+hours+"\nDay: "+days+"\nMonths: "+months+"\nYears: "+years);
    }
}
