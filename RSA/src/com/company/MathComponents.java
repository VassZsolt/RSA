package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MathComponents {

    /**
     * Generate a random number between 1 and bound.
     * @param bound-1 is the max value of random number
     * @return a random integer number
     */

    public int generateRandomNumber(int bound){
        return ThreadLocalRandom.current().nextInt(1,bound);
    }

    /**
     * Decides whether the specified number is a prime number.
     * @param number to be examined
     * @return true if number is a prime number, otherwise it's false
     */

    public boolean isPrime(int number){
        boolean isPrime=true;
        int sqrtOfNumber = (int) Math.ceil((float) (Math.sqrt(number)));
        int numberOfDividers=0;

        if(number==1){
            isPrime=false; //1 is not a prime number
        }
        else if(number==2){ //is a prime number
            return isPrime;
        }
        else {
            for(int i=1;i<=sqrtOfNumber;i++){
                if(number%i==0){
                    numberOfDividers++;
                }
                if(numberOfDividers==2){ //shouldn't continue the iteration if it's not prime
                    isPrime=false;
                    return isPrime;
                }
            }
        }
        return isPrime;
    }

    /**
     * Create a List with n prime number.
     * @param n is te count of primes
     * @return a list with primes
     */

    public List<Integer> getNPrimes(int n){
        List<Integer> myPrimes=new ArrayList<>();
        for(int i=0; i<2;i++){ //We need 2 Prime for RSA
            int number = 1;
            while (!isPrime(number)) {
                number = generateRandomNumber(100);
            }
            myPrimes.add(number);
        }
        return myPrimes;
    }

    /**
     * Get a number and returns with the dividers of number
     * @param number to be examined
     * @return a list including the dividers
     */

    public List<Integer> getTheDividersOfNumber(int number){
        List<Integer> dividers=new ArrayList<>();
        for (int i=1; i<number;i++){
            if(number%i==0){
                dividers.add(i);
            }
        }
        dividers.add(number);
        return dividers;
    }

    /**
     * Compare two number divisors and decides whether they are relative primes.
     * @param number1 and
     * @param number2 to be examined
     * @return if {@param number1} and {@param number2} are relative primes true,
     *         else false
     */

    public boolean areTheseRelativePrimes(int number1, int number2) {
        boolean areTheseRelativePrimes=true;
        List<Integer> dividersOfNumber1=getTheDividersOfNumber(number1);
        List<Integer> dividersOfNumber2=getTheDividersOfNumber(number2);
        int countOfSameDividers=0;
        for (Integer divider1 : dividersOfNumber1) {
            for (Integer divider2 : dividersOfNumber2) {
                if (divider1.equals(divider2)) {
                    countOfSameDividers++;
                }
            }
        }

        if(countOfSameDividers!=1){
            areTheseRelativePrimes=false;
        }

        return areTheseRelativePrimes;
    }

    /**
     * Get a number and return the smallest relative prime for it.
     * @param number to be examined
     * @return a number what is a relative prime for {@param number}
     */

    public int getARelativePrimeForNumber(int number){
        List<Integer> relativePrimesForNumber=new ArrayList<>();
        for(int i=2; i<number;i++){
            if(areTheseRelativePrimes(number,i)){
                relativePrimesForNumber.add(i);
            }
        }
        if(relativePrimesForNumber.size()>1) {
            int index = generateRandomNumber(relativePrimesForNumber.size() - 1);
            return relativePrimesForNumber.get(index);
        }
        return relativePrimesForNumber.get(0);
    }
}