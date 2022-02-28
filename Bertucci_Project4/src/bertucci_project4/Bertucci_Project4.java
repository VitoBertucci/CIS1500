/*
Classes and ArrayLists
    -Create a class "BigInt" to store very large int values
    -class should have private array or arraylist of integers that stores the 
     digits of BigInt
    -class should contain boolean for positive or negative integer
    -add methods for BigInt add(BigInt other), BigInt subtract(BigInt other) and String toString()
    -use unit tests for 100% code coverage

Rubric: 
    -BigInt class has private int[] or ArrayList<Integer> to store the digits
    -need a constructor to create them - and a boolean to indicate positive or negative
    -BigInt Add method accepts another BigInt to add and creates a NEW BigInt result
    -BigInt Subtract method accepts another BigInt to subtract and creates a NEW BigInt result
    -toString method prints out a nicely formatted value with thousands separator and negative indicator
    -Unit Tests - 100 % code coverage (add, subtract, and toString)
*/

/*
References:
https://stackoverflow.com/questions/2297347/splitting-a-string-at-every-n-th-character
*/
package Bertucci_Project4.src.bertucci_project4;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import Bertucci_Project4.src.BigInt.BigInt;
public class Bertucci_Project4 {


    public static void main(String[] args) {
        BigInt x = new BigInt("123456789123456789");
        BigInt y = new BigInt("123456789123456788");

        //BigInt z = x.add(y);

        int a = 9 / 2;
        System.out.println(a);

    } 
}
