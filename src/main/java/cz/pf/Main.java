package cz.pf;

import cz.pf.array.PFArray;

public class Main {
    public static void main(String[] args) {

//        int abc[] = {2,5,10,16};
//        PFArray pfArray = new PFArray(abc);
        PFArray pfArray = new PFArray(generateRandomNumbers());

        System.out.println(pfArray.toString());

        System.out.println("Suma: " + pfArray.sumNumbers());

        System.out.println("The largest number: " + pfArray.getLargestNumber());

        System.out.println("The smallest number: " + pfArray.getSmallestNumber());


        pfArray.addNumberToArray(5);
        System.out.println("Array with added number: " + pfArray.toString());

        pfArray.removeSelNumberFromArray(6);
        System.out.println("Array with removed number: " + pfArray.toString());

        pfArray.regenerateArray();
        System.out.println("Regenerated array: " + pfArray.toString());

    }

    public static int[] generateRandomNumbers(){
        int size = 10;
        int arr[] = new int[size];
        for(int i = 0; i < size; i++){
            arr[i]= (int)(Math.random()*10);
        }
        return arr;
    }



}