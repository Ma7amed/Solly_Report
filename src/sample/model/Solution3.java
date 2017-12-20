package sample.model;

import java.util.Scanner;
//import java.util.ArrayList;

public class Solution3 {
    private static Scanner read;

    public static long Solving(long num) {
        long answer = 0;
        //ArrayList<Integer> primeno = new ArrayList<Integer>();
        //for (long i=2; i<(num/2+1) ;i++)
        // {
        //   if (isPrime(i))
        // { primeno.add((int) i); answer=i; System.out.println(i);}
        //}
        for (long i = num / 2 + 1; i > 0; i--) {
            if ( num % i == 0 && isPrime(i)) {
                System.out.println(i);
                answer = i;
                break;
            }
        }
        return answer;
    }

    private static boolean isPrime(long num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        read = new Scanner(System.in);
        long num, answer;
        num = read.nextLong();
        System.out.println(num);
        answer = Solving(num);
        System.out.printf("answer= " + answer);

    }
}

//690369582