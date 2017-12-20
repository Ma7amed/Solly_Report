package sample.model;

import java.util.Date;
import java.util.Scanner;

/**
 * Created by m80028770 on 8/13/2017.
 */
public class Test1 {


    public static void main(String[] args) {

        Date now = new Date();

        System.out.println("Test1.main: " + now.getTime()/1000);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        now = new Date();

        System.out.println("Test1.main: " + now.getTime()/1000);


    }


    /*
    public static void main(String[] args) {

        long startTime, endTime;

        int nu = 1;
        while (nu > 0) {

            Scanner s = new Scanner(System.in);

            System.out.print("Enter number: ");
            nu = s.nextInt();

            //getLargestPrime(453333216);
            startTime = System.currentTimeMillis();

            getLargestPrime2(nu);
            endTime = System.currentTimeMillis();


            System.out.println("Run Time: " + (endTime - startTime) + " ms");
            System.out.println("");
        }
    }
*/

    public static boolean isPrime(int number) {

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;

    }

    public static void getLargestPrime(int number) {
        System.out.println("Test1.getLargestPrime ... getting largest number for " + number);
        for (int i = number - 1; i >= 2; i--) {
            //System.out.println("Test1.getLargestPrime: " + i);

            if (number % i == 0 && isPrime(i)) {
                System.out.println("Test1.getLargestPrime: " + i);
                break;
            }
        }
    }


    public static void getLargestPrime2(int number) {


        int i = 2;
        while (i < number) {
            if (number % i == 0) {
               // System.out.println("Removing " + i + " from the number,, remaining: " + number / 2);
                number = number / i;
                i = 2;
            } else {
                i++;
            }
        }

        System.out.println("Final Result: " + number);


    }


}
