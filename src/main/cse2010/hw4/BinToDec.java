package cse2010.hw4;
/*
 * CSE2010 Homework #4: BinToDec.java
 *
 * Complete the code below.
 */

public class BinToDec {

    public static int binToDec(String number) {
        if (number.length() == 1) {
            return Integer.parseInt(number);
        } else {
            int lastBit = Integer.parseInt(number.substring(number.length() - 1));
            String restBits = number.substring(0, number.length() - 1);
            return lastBit + 2 * binToDec(restBits);
        }
    }

    // Tail-recursion
    public static int binToDecTR(String number, int result) {
        if (number.length() == 1) {
            return result * 2 + Integer.parseInt(number);
        } else {
            int firstBit = Integer.parseInt(number.substring(0, 1));
            String restBits = number.substring(1);
            return binToDecTR(restBits, result * 2 + firstBit);
        }
    }
}