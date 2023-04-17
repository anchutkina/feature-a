package cz.pf.array;

import cz.pf.Main;

import java.util.Arrays;

public class PFArray {

    private int[] array;
    private String name;


    public PFArray(int[] array, String name) {
        this.array = array;
        this.name = name;
    }


    //Print array
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.array.length; i++) {
            result.append(this.array[i]).append(" ");
        }
        return this.name + ": " + result;
    }


    public void setArray(int[] array) {
        this.array = array;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Adding a number to an array
    public void addNumberToArray(int newNumber) {
        int[] newArray = new int[this.array.length + 1];
        for (int i = 0; i < this.array.length; i++) {
            newArray = Arrays.copyOf(this.array, this.array.length + 1);
        }
        newArray[newArray.length - 1] = newNumber;
        this.array = newArray;
    }

    //Removing the last number from an array
    public void removeNumberFromArray() {
        int[] newArray = new int[this.array.length - 1];
        for (int i = 0; i < this.array.length - 1; i++) {
                newArray = Arrays.copyOf(this.array, this.array.length - 1);
        }
        this.array=newArray;
    }


    //Finding the largest number in the array
    public int getLargestNumber(){
        int largeNum = 0;
        for(int i = 0; i < this.array.length; i++) {
            if (this.array[i] > largeNum) {
                largeNum = this.array[i];
            }
        }
        return largeNum;
    }

    //Finding the smallest number in the array
    public int getSmallestNumber (){
        int smallNum = array[0];
        for(int i = 0; i < this.array.length; i++) {
            if (this.array[i] < smallNum) {
                smallNum = this.array[i];
            }
        }
        return smallNum;
    }

    //The sum of all elements in the array
    public long sumNumbers(){
        return Arrays.stream(this.array).asLongStream().sum();
    }

    //Deleting the entire array
    public void deleteArray(){
        this.array = new int[0];
    }

    //Regenerating numbers in an array
    public int[] regenerateArray() {
        this.array = Main.generateArrayRandomly(this.array.length);
        return this.array;
    }




}

