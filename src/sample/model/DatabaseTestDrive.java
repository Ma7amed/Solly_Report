package sample.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by m80028770 on 4/22/2017.
 */
public class DatabaseTestDrive {

    public static void main(String[] args) {

        for(int i=0;i<1000;i++) {
            System.out.println("DatabaseTestDrive.main: " + (int) (Math.random() * 10));
        }

    }
}
