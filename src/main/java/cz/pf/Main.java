package cz.pf;

import cz.pf.array.PFArray;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<PFArray> arrayStorage = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        chooseArrayCreationType(scanner);
    }

    public static int[] generateRandomNumbers(int size) {
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    private static void chooseArrayCreationType(Scanner scanner) {
        System.out.println("Please write 'random' if you want to create array with random elements, or write 'manual' if you want to create array by yourself:");

        String chosenCreationType = scanner.nextLine();

        if (chosenCreationType.equals("random")) {
            chooseSizeOfArray(scanner);

        } else if (chosenCreationType.equals("manual")) {
            numbersInManualArray(scanner);

        } else {
            System.err.println("The entered value is incorrect.");
            chooseArrayCreationType(scanner);
        }


    }

    private static void chooseSizeOfArray(Scanner scanner) {
        System.out.println("Please provide size of array:");
        String chosenSize = scanner.nextLine();
        try {
            PFArray pfArray = new PFArray(generateRandomNumbers(Integer.valueOf(chosenSize)));
            chooseNameOfArray(scanner, pfArray);
        } catch (NumberFormatException e) {
            chooseSizeOfArray(scanner);
        }

    }

    private static void chooseNameOfArray(Scanner scanner, PFArray pfArray) {
        System.out.println("Please provide name of array:");
        String nameOfArray = scanner.nextLine();
        for(int i = 0; i < arrayStorage.size(); i++) {
            if(arrayStorage.get(i).getName().equals(nameOfArray)) {
                System.out.println("Array with this name already exists.");
                chooseNameOfArray(scanner,pfArray);
            }
        }
        pfArray.setName(nameOfArray);
        arrayStorage.add(pfArray);
        printCreatedArray(scanner, pfArray);
    }

    private static void printCreatedArray(Scanner scanner, PFArray pfArray) {

        System.out.println("Array is created. Do you want to print it? Y/N:");
        String yn = scanner.nextLine();
        if (yn.equals("Y")) {
            System.out.println(pfArray.toString());
            workWithArray(scanner,pfArray);
        } else if (yn.equals("N")) {
            workWithArray(scanner,pfArray);
        } else {
            System.err.println("The entered value is incorrect.");
            printCreatedArray(scanner, pfArray);
        }
    }

    private static void numbersInManualArray(Scanner scanner) {
        System.out.println("Please provide numbers to store in your array divided by space:");

        String manualNumbers = scanner.nextLine();
        String[] array = manualNumbers.split(" ");
        int[] manualArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            try {
                manualArray[i] = Integer.valueOf(array[i]);
            } catch (NumberFormatException e) {
                numbersInManualArray(scanner);
            }

        }
        PFArray pfArray = new PFArray(manualArray);

        chooseNameOfArray(scanner, pfArray);
    }

    private static void workWithArray(Scanner scanner, PFArray pfArray) {
        System.out.println("Choose and type option from following:\n'create' --to create a new array\n'select' --to select the array to work with\n'largest sum' --to find the array with the largest sum of elements\n'smallest sum' --to find the array with the smallest sum of elements\n'finish' --to finish the program");
        String chosenOption = scanner.nextLine();

        if (chosenOption.equals("create")) {
            chooseArrayCreationType(scanner);
        } else if (chosenOption.equals("select")) {
            selectArray(scanner,pfArray);
        } else if (chosenOption.equals("largest sum")) {
            largestSum();
            workWithArray(scanner, pfArray);
        } else if (chosenOption.equals("smallest sum")) {
            smallestSum();
            workWithArray(scanner,pfArray);
        } else if (chosenOption.equals("finish")) {
            System.out.println("Goodbye");
        } else {
            System.err.println("The entered value is incorrect.");
            workWithArray(scanner, pfArray);
        }

    }

    private static void selectArray(Scanner scanner, PFArray pfArray) {
        System.out.println("Existing arrays:");
        arrayStorage.forEach((e) -> System.out.println(e.getName()));
        System.out.println("Type name of array you want to work with:");
        String nameOfArray = scanner.nextLine();
        boolean ifFound = false;

        for (int i = 0; i < arrayStorage.size(); i++) {
            if (arrayStorage.get(i).getName().equals(nameOfArray)) {
                ifFound = true;
                actionsWithArray(scanner, arrayStorage.get(i));
                break;
            }
        }
        if (ifFound == false) {
            System.err.println("Typed element was not found.");
            selectArray(scanner, pfArray);
        }

    }

    private static void actionsWithArray(Scanner scanner, PFArray pfArray) {
        System.out.println("Choose and type option from following:\n'print' --to print selected array\n'add' --to add number to the selected array\n'remove' --to remove number from the selected array\n'max' --to find the largest number in selected array\n'min' --to find the smallest number in selected array\n'sum' --to find sum of numbers in the selected array\n'delete' --to delete the selected array\n'regenerate' --to regenerate numbers in the selected array\n'back' --to take a step back\n'finish' --to finish the program");
        String actionWithArray = scanner.nextLine();

        if(actionWithArray.equals("print")){
            System.out.println(pfArray.toString() + "\n");
            actionsWithArray(scanner,pfArray);
        } else if(actionWithArray.equals("add")) {
            System.out.println("Type number, that you want to add:" + "\n");
            String newNumber = scanner.nextLine();
            try {
                pfArray.addNumberToArray(Integer.valueOf(newNumber));
                System.out.println("Number is added. Updated array: " + pfArray.toString() + "\n");
                actionsWithArray(scanner,pfArray);
            } catch(NumberFormatException e) {
                actionsWithArray(scanner,pfArray);
            }
        } else if(actionWithArray.equals("remove")) {
            pfArray.removeNumberFromArray();
            System.out.println("Number is removed. Updated array: " + pfArray.toString() + "\n");
            actionsWithArray(scanner,pfArray);
        } else if(actionWithArray.equals("max")) {
            pfArray.getLargestNumber();
            System.out.println("The largest number of selected array is: " + pfArray.getLargestNumber() + "\n");
            actionsWithArray(scanner,pfArray);
        } else if(actionWithArray.equals("min")) {
            pfArray.getSmallestNumber();
            System.out.println("The smallest number of selected array is: " + pfArray.getSmallestNumber() + "\n");
            actionsWithArray(scanner,pfArray);
        } else if(actionWithArray.equals("sum")) {
            pfArray.sumNumbers();
            System.out.println("The sum of numbers of selected array is: " + pfArray.sumNumbers() + "\n");
            actionsWithArray(scanner,pfArray);
        } else if(actionWithArray.equals("delete")) {
            pfArray.deleteArray();
            System.out.println("The selected array is deleted" + "\n");
            actionsWithArray(scanner,pfArray);
        } else if(actionWithArray.equals("regenerate")) {
            pfArray.regenerateArray();
            System.out.println("Regenerated array: " + pfArray.toString() + "\n");
            actionsWithArray(scanner,pfArray);
        } else if(actionWithArray.equals("back")) {
            workWithArray(scanner, pfArray);
        } else if(actionWithArray.equals("finish")) {
            System.out.println("Goodbye");
        } else {
            System.err.println("The entered value is incorrect.");
            actionsWithArray(scanner, pfArray);
        }

    }

    private static int largestSum() {
        int largeSum = 0;
        String nameLargeSum = new String();
        for(int i = 0; i < arrayStorage.size(); i++) {
            if(arrayStorage.get(i).sumNumbers() > largeSum) {
                largeSum = arrayStorage.get(i).sumNumbers();
                nameLargeSum = arrayStorage.get(i).getName();
            }
        }
        System.out.println("Array with the largest sum of numbers is: " + nameLargeSum + ", sum = " + largeSum);
        System.out.println();

        return largeSum;

    }

    private static int smallestSum() {
        int smallSum = Integer.MAX_VALUE;
        String nameSmallSum = new String();
        for(int i = 0; i < arrayStorage.size(); i++) {
            if(arrayStorage.get(i).sumNumbers() < smallSum) {
                smallSum = arrayStorage.get(i).sumNumbers();
                nameSmallSum = arrayStorage.get(i).getName();
            }
        }
        System.out.println("Array with the smallest sum of numbers is: " + nameSmallSum + ", sum = " + smallSum);
        System.out.println();
        return smallSum;
    }


}