package cz.pf;

import cz.pf.array.PFArray;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static ArrayList<PFArray> arrayStorage = new ArrayList<>();
    private static PFArray pfArray = new PFArray();
    private final static String RANDOM = "random";
    private final static String MANUAL = "manual";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        chooseNameOfArray(scanner);
    }

    public static int[] generateRandomNumbers(int size) {
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt();
        }
        return arr;
    }

    private static void chooseNameOfArray(Scanner scanner) {
        System.out.println("Please provide name of array you want to create:");
        String nameOfArray = scanner.nextLine();

        boolean ifFound = findArrayByName(nameOfArray);
        if(ifFound == true) {
            chooseNameOfArray(scanner);
        } else {
            pfArray.setName(nameOfArray);
            arrayCreatingType(scanner);
        }
    }

    private static boolean findArrayByName(String nameOfArray) {

        for(PFArray i : arrayStorage) {
            if(i.getName().equals(nameOfArray)) {
                System.out.println("Array with this name already exists.");
                return true;
            }
        }
        return false;
    }
    private static void arrayCreatingType(Scanner scanner) {
        System.out.println("Please write 'random' if you want to create array with random elements, or write 'manual' if you want to create array by yourself:");

        String chosenCreationType = scanner.nextLine();

        if (chosenCreationType.equals(RANDOM)) {
            chooseSizeOfArray(scanner);

        } else if (chosenCreationType.equals(MANUAL)) {
            numbersInManualArray(scanner);

        } else {
            System.err.println("The entered value is incorrect.");
            arrayCreatingType(scanner);
        }


    }



    private static void chooseSizeOfArray(Scanner scanner) {
        System.out.println("Please provide size of array:");
        String chosenSize = scanner.nextLine();
        try {
            pfArray.setArray(generateRandomNumbers(Integer.valueOf(chosenSize)));
            arrayStorage.add(pfArray);
            System.out.println("Array is created:\n" + pfArray.toString());
            System.out.println();
            pfArray = new PFArray();
            workWithArray(scanner);

        } catch (NumberFormatException e) {
            chooseSizeOfArray(scanner);
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
        pfArray.setArray(manualArray);
        arrayStorage.add(pfArray);
        System.out.println("Array is created:\n" + pfArray.toString());
        System.out.println();
        pfArray = new PFArray();
        workWithArray(scanner);

    }

    private static void workWithArray(Scanner scanner) {
        System.out.println("Choose and type option from following:\n1 -to create a new array\n2 -to select the array to work with\n3 -to find the array with the largest sum of elements\n4 -to find the array with the smallest sum of elements\n5 -to finish the program");
        int chosenOption = scanner.nextInt();
        scanner.nextLine();

        if (chosenOption == 1) {
            chooseNameOfArray(scanner);
        } else if (chosenOption == 2) {
            selectArray(scanner);
        } else if (chosenOption == 3) {
            largestSum();
        } else if (chosenOption == 4) {
            smallestSum();
        } else if (chosenOption == 5) {
            System.out.println("Goodbye");
        } else {
            System.err.println("The entered value is incorrect.");
            workWithArray(scanner);
        }

    }

    private static void selectArray(Scanner scanner) {
        System.out.println("Existing arrays:");
        for (PFArray i : arrayStorage) {
            System.out.println(i.getName());
        }
        System.out.println("Type name of array you want to work with:");

        String nameOfArray = scanner.nextLine();
        boolean ifFound = false;

        for (PFArray i : arrayStorage) {
            if (i.getName().equals(nameOfArray)) {
                ifFound = true;
                actionsWithArray(scanner, i);
                break;
            }
        }
        if (ifFound == false) {
            System.err.println("Typed element was not found.");
            selectArray(scanner);
        }

    }

    private static void actionsWithArray(Scanner scanner, PFArray pfArray) {
        System.out.println("Choose and type option from following:\n1 -to print selected array\n2 -to add number to the selected array\n3 -to remove number from the selected array\n4 -to find the largest number in selected array\n5 -to find the smallest number in selected array\n6 -to find sum of numbers in the selected array\n7 -to delete the selected array\n8 -to regenerate numbers in the selected array\n9 -to take a step back\n10 -to finish the program");
        int actionWithArray = scanner.nextInt();
        scanner.nextLine();

        if(actionWithArray == 1){
            System.out.println(pfArray.toString() + "\n");
        } else if(actionWithArray == 2) {
            System.out.println("Type number, that you want to add:" + "\n");
            String newNumber = scanner.nextLine();
            try {
                pfArray.addNumberToArray(Integer.valueOf(newNumber));
                System.out.println("Number is added. Updated array: " + pfArray.toString() + "\n");
            } catch(NumberFormatException e) {
                actionsWithArray(scanner, pfArray);
            }
        } else if(actionWithArray == 3) {
            pfArray.removeNumberFromArray();
            System.out.println("Number is removed. Updated array: " + pfArray.toString() + "\n");
        } else if(actionWithArray == 4) {
            pfArray.getLargestNumber();
            System.out.println("The largest number of selected array is: " + pfArray.getLargestNumber() + "\n");
        } else if(actionWithArray == 5) {
            pfArray.getSmallestNumber();
            System.out.println("The smallest number of selected array is: " + pfArray.getSmallestNumber() + "\n");
        } else if(actionWithArray == 6) {
            pfArray.sumNumbers();
            System.out.println("The sum of numbers of selected array is: " + pfArray.sumNumbers() + "\n");
        } else if(actionWithArray == 7) {
            pfArray.deleteArray();
            System.out.println("The selected array is deleted" + "\n");
        } else if(actionWithArray == 8) {
            pfArray.regenerateArray();
            System.out.println("Regenerated array: " + pfArray.toString() + "\n");
        } else if(actionWithArray == 9) {
            workWithArray(scanner);
        } else if(actionWithArray == 10) {
            System.out.println("Goodbye");
        } else {
            System.err.println("The entered value is incorrect.");
            actionsWithArray(scanner, pfArray);
        }

    }

    private static long largestSum() {
        if(arrayStorage.isEmpty()) {
            System.out.println("There is no available array to count sum");
            return 0;
        } else {
            long largeSum = arrayStorage.get(0).sumNumbers();
            String nameLargeSum = new String();
            for(PFArray i : arrayStorage) {
                if(i.sumNumbers() > largeSum) {
                    largeSum = i.sumNumbers();
                    nameLargeSum = i.getName();
                }
            }
            System.out.println("Array with the largest sum of numbers is: " + nameLargeSum + ", sum = " + largeSum);
            System.out.println();
            return largeSum;
        }


    }

    private static int smallestSum() {
        int smallSum = Integer.MAX_VALUE;
        String nameSmallSum = new String();
        for(PFArray i : arrayStorage) {
            if(i.sumNumbers() < smallSum) {
                smallSum = i.sumNumbers();
                nameSmallSum = i.getName();
            }
        }
        System.out.println("Array with the smallest sum of numbers is: " + nameSmallSum + ", sum = " + smallSum);
        System.out.println();
        return smallSum;
    }


}