package cz.pf;

import cz.pf.array.PFArray;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static ArrayList<PFArray> arrayStorage = new ArrayList<>();
    private final static String RANDOM = "random";
    private final static String MANUAL = "manual";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Choose and type option from following:\n1 -to create a new array\n2 -to select the array to work with\n3 -to find the array with the largest sum of elements\n4 -to find the array with the smallest sum of elements\n5 -to finish the program");
            int chosenOption = scanner.nextInt();
            scanner.nextLine();

            if (chosenOption == 1) {
                String name = chooseArrayName(scanner);
                if(name.isBlank()) {
                    continue;
                }

                String type = chooseArrayType(scanner);
                if(type.equals(RANDOM)) {
                    int sizeOfArray = chooseArraySize(scanner);
                    PFArray pfArray = new PFArray(generateRandomNumbers(sizeOfArray), name);
                    arrayStorage.add(pfArray);
                    System.out.println("Array is created:\n" + pfArray.toString());
                    System.out.println();
                } else if (type.equals(MANUAL)) {
                    int [] manualNumbers = inputManualNumbers(scanner);
                    PFArray pfArray = new PFArray(manualNumbers, name);
                    arrayStorage.add(pfArray);
                    System.out.println("Array is created:\n" + pfArray.toString());
                    System.out.println();
                } else {
                    System.err.println("The entered value is incorrect.");
                }

            } else if (chosenOption == 2) {
                PFArray pfArray = selectArray(scanner);
                if(pfArray == null){
                    continue;
                }
                int actionWithArray = chooseArrayAction(scanner);
                if (actionWithArray == 1) {
                    System.out.println(pfArray.toString() + "\n");
                } else if (actionWithArray == 2) {
                    System.out.println("Type number, that you want to add:" + "\n");
                    int newNumber = scanner.nextInt();
                    scanner.nextLine();
                    pfArray.addNumberToArray(newNumber);
                    System.out.println("Number is added. Updated array: " + pfArray.toString() + "\n");
                } else if (actionWithArray == 3) {
                    pfArray.removeNumberFromArray();
                    System.out.println("Number is removed. Updated array: " + pfArray.toString() + "\n");
                } else if (actionWithArray == 4) {
                    pfArray.getLargestNumber();
                    System.out.println("The largest number of selected array is: " + pfArray.getLargestNumber() + "\n");
                } else if (actionWithArray == 5) {
                    pfArray.getSmallestNumber();
                    System.out.println("The smallest number of selected array is: " + pfArray.getSmallestNumber() + "\n");
                } else if (actionWithArray == 6) {
                    pfArray.sumNumbers();
                    System.out.println("The sum of numbers of selected array is: " + pfArray.sumNumbers() + "\n");
                } else if (actionWithArray == 7) {
                    pfArray.deleteArray();
                    System.out.println("The selected array is deleted" + "\n");
                } else if (actionWithArray == 8) {
                    pfArray.regenerateArray();
                    System.out.println("Regenerated array: " + pfArray.toString() + "\n");
                } else {
                    System.err.println("The entered value is incorrect.\n");
                }
            } else if (chosenOption == 3) {
                long largeSum = findLargestSum();
                System.out.println("Array with the largest sum of numbers is: " + largeSum);
                System.out.println();
            } else if (chosenOption == 4) {
                long smallSum = findSmallestSum();
                System.out.println("Array with the smallest sum of numbers is: " + smallSum);
                System.out.println();
            } else if (chosenOption == 5) {
                System.out.println("Goodbye");
                System.exit(0);
            } else {
                System.err.println("The entered value is incorrect.\n");
            }
        }

    }


    public static int[] generateRandomNumbers(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt();
        }
        return arr;
    }

    private static String chooseArrayName(Scanner scanner) {

        System.out.println("Please provide name of array you want to create:");
        String nameOfArray = scanner.nextLine();

        for(PFArray i : arrayStorage) {
            if(i.getName().equals(nameOfArray)) {
                System.err.println("Array with this name already exists. Choose another name, please.\n");
                return "";
            }
        }
        return nameOfArray;
    }

    private static String chooseArrayType(Scanner scanner) {
        System.out.println("Please write 'random' if you want to create array with random elements, or write 'manual' if you want to create array by yourself:");
        String chosenArrayType = scanner.nextLine();
        return chosenArrayType;
    }

    private static int chooseArraySize(Scanner scanner) {
        System.out.println("Please provide size of array:");
        int chosenSize = scanner.nextInt();
        scanner.nextLine();
        return chosenSize;
    }

    private static int[] inputManualNumbers(Scanner scanner) {
        System.out.println("Please provide numbers to store in your array divided by space:");

        String manualNumbers = scanner.nextLine();
        String[] array = manualNumbers.split(" ");
        int[] manualArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            try {
                manualArray[i] = Integer.valueOf(array[i]);
            } catch (NumberFormatException e) {
                System.err.println("Provided elements are incorrect.");
            }

        }
        return manualArray;

    }

    private static PFArray selectArray(Scanner scanner) {
        System.out.println("Existing arrays:");
        for (PFArray i : arrayStorage) {
            System.out.println(i.getName());
        }
        System.out.println("Type name of array you want to work with:");
        String nameOfArray = scanner.nextLine();
        for(PFArray i : arrayStorage) {
            if(i.getName().equals(nameOfArray)) {
                return i;
            }
        }
        System.err.println("Typed element was not found.");
        return null;
    }

    private static int chooseArrayAction(Scanner scanner) {
        System.out.println("Choose and type option from following:\n1 -to print selected array\n2 -to add number to the selected array\n3 -to remove number from the selected array\n4 -to find the largest number in selected array\n5 -to find the smallest number in selected array\n6 -to find sum of numbers in the selected array\n7 -to delete the selected array\n8 -to regenerate numbers in the selected array");
        int actionWithArray = scanner.nextInt();
        scanner.nextLine();
        return actionWithArray;
    }

    private static long findLargestSum() {
        if(arrayStorage.isEmpty()) {
            System.out.println("There is no available array to count sum");
            return 0;
        } else {
            long largeSum = arrayStorage.get(0).sumNumbers();
            for(PFArray i : arrayStorage) {
                if(i.sumNumbers() >= largeSum) {
                    largeSum = i.sumNumbers();
                }
            }
            return largeSum;
        }
    }

    private static long findSmallestSum() {
        long smallSum = Integer.MAX_VALUE;

        for(PFArray i : arrayStorage) {
            if(i.sumNumbers() < smallSum) {
                smallSum = i.sumNumbers();
            }
        }
        return smallSum;
    }
}