package com.company;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class RSA {

    /**
     * This is an RSA algorithm implementation.
     */

    public static void main(String[] args) {
        out.println("This is an RSA algorithm implementation.");
        MathComponents mathComponents =new MathComponents();
        List<Integer> myPrimes= mathComponents.getNPrimes(2); //We need 2 prime number for RSA

        int N=myPrimes.get(0)*myPrimes.get(1); //N=p*q where p and q is our primes.
        int fiN=(myPrimes.get(0)-1)*(myPrimes.get(1)-1); //fiN=(p-1)*(q-1)
        int e=mathComponents.getARelativePrimeForNumber(fiN); //e is a relative prime for fiN

        //Euripides algorithm -> e*d=k*fiN+1
        int k=getKParam(fiN, e);
        int d=(k*fiN+1)/e;

        out.println("p="+myPrimes.get(0));
        out.println("q="+myPrimes.get(1));
        out.println("N="+N);
        out.println("fiN="+fiN);
        out.println("e="+e);
        out.println("k="+k);
        out.println("d="+d);

        int input= getInput();
        int coded=encryptNumber(input,e, N); //encryption

        out.println("Public key:"+N+", "+e);
        out.println("Private key:"+N+", "+d);
        out.println("uncoded: "+input+"---> coded: "+coded);

        RSA_Decoder decoder=new RSA_Decoder();
        int decoded=decoder.decodeNumber(coded,d,N);
        out.println("coded: "+coded+"---> decoded: "+decoded);
    }

    /**
     * Read an input from the console.
     * @return the input word converted to int
     */

    public static int getInput(){
        Scanner read=new Scanner(in);
        out.println("Please write the input word that should be encrypted.");
        String input= read.nextLine();
        read.close();
        return Integer.parseInt(input);
    }

    /**
     * Get fiN and e and generate k value with a simplified way.
     * @param fiN and
     * @param e are two multiplication factor
     * @return the k value
     */

    public static int getKParam(int fiN,int e){
        /*
        Here we should use a long derivation and get this: 1=e*d-fiN*k
        but it's equal with using e*d=fiN*k+1, so this function using a simplified way to generate k value.
         */
        int d=1, k=1;
        while(e*d!=fiN*k+1){
            if(e*d>fiN*k+1) {
                k++;
            }
            else {
                d++;
            }
        }
        return k;
    }

    /**
     * This is he official way to encrypt a number with RSA.
     * @param number will be encrypted
     * @param e is the exponent
     * @param N is the basis of congruence
     * @return the encrypted value of number
     */

    public static int encryptNumber(int number, int e, int N){
        return (int)(Math.pow(number, e)%N);
    }
}