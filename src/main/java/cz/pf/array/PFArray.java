package cz.pf.array;

import cz.pf.Main;

public class PFArray {

    private int[] array;
    private String name;

    public PFArray(int[] array) {
        this.array = array;
    }

    public PFArray(int[] array, String name) {
        this.array = array;
        this.name = name;
    }


    //Print array
    public String toString() {
        String result = new String();
        for (int i = 0; i < this.array.length; i++) {
            result += this.array[i] + " ";
        }
        return this.name + ": " + result;
    }

    //Return array
    public int[] getArray() {
        return this.array;
    }

    //Adding a number to an array
    public int[] addNumberToArray(int newNumber) {
        int[] newArray = new int[this.array.length + 1];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        newArray[newArray.length - 1] = newNumber;
        this.array = newArray;

        return this.array;

    }

    //Removing the last number from an array
    public int[] removeNumberFromArray() {
        int[] newArray = new int[this.array.length - 1];
        for (int i = 0; i < this.array.length-1; i++) {
                newArray[i] = this.array[i];
        }
        this.array=newArray;
        return this.array;
    }

    //Removing number with selected index from an array
    public int[] removeSelNumberFromArray(int rmIndex) {

        if (this.array == null || rmIndex < 0 || rmIndex >= this.array.length) {
            return this.array;
        }

        int[] newArray = new int[this.array.length - 1];
        for (int i = 0, k = 0; i < this.array.length; i++) {
            if(i == rmIndex) {
                continue;
            }
            newArray[k++]=this.array[i];
        }
        this.array=newArray;
        return this.array;
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
        int smallNum = Integer.MAX_VALUE;
        for(int i = 0; i < this.array.length; i++) {
            if (this.array[i] < smallNum) {
                smallNum = this.array[i];
            }
        }
        return smallNum;
    }

    //The sum of all elements in the array
    public int sumNumbers(){
        int sum = 0;
        for (int i=0; i < this.array.length; i++) {
           sum = sum + this.array[i];
        }
        return sum;
    }

    //Deleting the entire array
    public int[] deleteArray(){
        this.array = new int[0];
        return this.array;
    }

    //Regenerating numbers in an array
    public int[] regenerateArray() {
        this.array = Main.generateRandomNumbers(this.array.length);
        return this.array;
    }

    public String setName(String nameOfArray) {
        this.name = nameOfArray;
        return this.name;
    }

    public String getName() {
        return this.name;
    }


}

