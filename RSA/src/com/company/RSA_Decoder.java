package com.company;

import java.util.ArrayList;
import java.util.List;

public class RSA_Decoder {
    public int decodeNumber(int number, int d, int N){
        int solution=0;
        List<Integer> exponents = getExponentAsPowersOf2(d);
        List<Integer> remnants=getCongruentValues(number, exponents, N);
        List<Integer> filteredRemnants=filterCongruentValues(remnants,exponents);
        solution=getCongruentOfNumbersModuloN(filteredRemnants,N);
        return solution;
    }

    /**
     * Get a number and returns with a list that contains the addition components (or factors).
     * @param number to be examined
     * @return a list with the addition components values in descending order
     */

    public List<Integer>  getExponentAsPowersOf2(int number){
        List<Integer> sumFactors=new ArrayList<>();
        int i=1;
        while(number>0){
            i=i*2;
            if(i>number){
                i /= 2;
                sumFactors.add(i);
                number -= i;
                i=1;
            }
        }
        return sumFactors;
    }

    /**
     * Get a number and some exponents of it, returns with the congruent values.
     * @param number is the base of powering
     * @param exponents is some exponents of powering
     * @param modulo is the base of congruence
     * @return an Integer list with the congruent values
     */

    public List<Integer> getCongruentValues(int number,List<Integer> exponents, int modulo){
        List<Integer> congruentValues=new ArrayList<>();
        int i=1;
        int remnant=0;
        while(i<=exponents.get(0)){
            if(remnant==0) {
                remnant = (int) (Math.pow(number,i) % modulo);
            }
            else {
                remnant=remnant*remnant;
                remnant=remnant%modulo;
            }
            i=i*2;
            congruentValues.add(remnant);
        }
        return congruentValues;
    }

    /**
     * It's a filter for the congruent values.
     * If exponents list contains a power value then {@code filteredCongruentValues} will contain the remnant pair of them.
     *
     * @param remnants contains the congruent values for all the possible exponents (like 2 pow 2,4,8,16,32)
     * @param exponents contains some exponent values (like 2 pow 2,16,128)
     * @return a list with the filtered {@param remnants} values
     */

    public List<Integer> filterCongruentValues(List<Integer> remnants, List<Integer> exponents){
        List<Integer> filteredCongruentValues=new ArrayList<>();
        int i=1;
        int countOfExponent=0; //This value show the number of exponent like 2^3 = 3
        while(i<=exponents.get(0)){
            for(int j=0; j< exponents.size();j++){
                if(exponents.get(j)==i){
                    filteredCongruentValues.add(remnants.get(countOfExponent));
                }
            }
            i=i*2;
            countOfExponent++;
        }
        return filteredCongruentValues;
    }

    /**
     * Get a list numbers that are multiplication factors and calculate the congruent value of them.
     * @param numbers is a list contains the multiplication components
     * @param modulo is the base of congruence
     * @return the congruence value.
     */

    public int getCongruentOfNumbersModuloN(List<Integer> numbers, int modulo){
        int remnant=1;
        for (Integer number : numbers) {
            remnant = (remnant * number) % modulo;
        }
        return remnant;
    }
}