package cz.pf;

import cz.pf.array.PFArray;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static ArrayList<PFArray> arrayStorage = new ArrayList<>();
    private final static String RANDOM = "random";
    private final static String MANUAL = "manual";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                System.out.println("Choose and type option from following:\n1 -to create a new array\n2 -to select the array to work with\n3 -to find the array with the largest sum of elements\n4 -to find the array with the smallest sum of elements\n5 -to finish the program");
                int chosenOption = scanner.nextInt();
                scanner.nextLine();

                if (chosenOption == OptionWithArray.CREATE_ARRAY.i) {
                    String name = chooseArrayName(scanner);
                    String type = chooseArrayType(scanner);
                    if (type.equals(RANDOM)) {
                        int arraySize = chooseArraySize(scanner);
                        PFArray pfArray = new PFArray(generateArrayRandomly(arraySize), name);
                        arrayStorage.add(pfArray);
                        System.out.println("Array is created:\n" + pfArray.toString());
                        System.out.println();
                    } else if (type.equals(MANUAL)) {
                        int[] manualNumbers = generateArrayManually(scanner);
                        PFArray pfArray = new PFArray(manualNumbers, name);
                        arrayStorage.add(pfArray);
                        System.out.println("Array is created:\n" + pfArray.toString());
                        System.out.println();
                    }

                } else if (chosenOption == OptionWithArray.SELECT_ARRAY.i) {
                    try {
                        if (arrayStorage.isEmpty()) {
                            System.err.println("There is not any available array. First create array, please.");
                            continue;
                        }
                        PFArray pfArray = selectArray(scanner);
                        if (pfArray == null) {
                            continue;
                        }
                        int actionWithArray = chooseArrayAction(scanner);
                        if (actionWithArray == ActionWithArray.PRINT_ARRAY.i) {
                            System.out.println(pfArray.toString() + "\n");
                        } else if (actionWithArray == ActionWithArray.ADD_NUMBER.i) {
                            System.out.println("Type number, that you want to add:" + "\n");
                            pfArray.addNumberToArray(scanner.nextInt());
                            scanner.nextLine();
                            System.out.println("Number is added. Updated array: " + pfArray.toString() + "\n");
                        } else if (actionWithArray == ActionWithArray.REMOVE_NUMBER.i) {
                            pfArray.removeNumberFromArray();
                            System.out.println("Number is removed. Updated array: " + pfArray.toString() + "\n");
                        } else if (actionWithArray == ActionWithArray.LARGEST_NUMBER.i) {
                            pfArray.getLargestNumber();
                            System.out.println("The largest number of selected array is: " + pfArray.getLargestNumber() + "\n");
                        } else if (actionWithArray == ActionWithArray.SMALLEST_NUMBER.i) {
                            pfArray.getSmallestNumber();
                            System.out.println("The smallest number of selected array is: " + pfArray.getSmallestNumber() + "\n");
                        } else if (actionWithArray == ActionWithArray.SUM_NUMBERS.i) {
                            pfArray.sumNumbers();
                            System.out.println("The sum of numbers of selected array is: " + pfArray.sumNumbers() + "\n");
                        } else if (actionWithArray == ActionWithArray.DELETE_ARRAY.i) {
                            pfArray.deleteArray();
                            System.out.println("The selected array is deleted" + "\n");
                        } else if (actionWithArray == ActionWithArray.REGENERATE_ARRAY.i) {
                            pfArray.regenerateArray();
                            System.out.println("Regenerated array: " + pfArray.toString() + "\n");
                        } else {
                            System.err.println("The entered value is incorrect.\n");
                        }
                    } catch (InputMismatchException e) {
                        System.err.println("Provided value is incorrect. Type number, please.");
                        scanner.nextLine();
                    }
                } else if (chosenOption == OptionWithArray.LARGEST_SUM.i) {
                    if (arrayStorage.isEmpty()) {
                        System.err.println("There is no available array to count sum. First create array, please.");
                        continue;
                    }
                    long largeSum = findLargestSum();
                    System.out.println("Array with the largest sum of numbers is: " + largeSum);
                    System.out.println();
                } else if (chosenOption == OptionWithArray.SMALLEST_SUM.i) {
                    if (arrayStorage.isEmpty()) {
                        System.err.println("There is no available array to count sum. First create array, please.");
                        continue;
                    }
                    long smallSum = findSmallestSum();
                    System.out.println("Array with the smallest sum of numbers is: " + smallSum);
                    System.out.println();
                } else if (chosenOption == OptionWithArray.FINISH.i) {
                    System.out.println("Goodbye");
                    System.exit(0);
                } else {
                    System.err.println("The entered value is incorrect.\n");
                }
            } catch (InputMismatchException e) {
                System.err.println("Provided value is incorrect. Type number, please.");
                scanner.nextLine();
            }
        }
    }


    public static int[] generateArrayRandomly(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt();
        }
        return arr;
    }

    private static String chooseArrayName(Scanner scanner) {
        String arrayName = "";
        boolean isUnique = true;
        while(arrayName.isBlank() || isUnique == false) {
            isUnique = true;
            System.out.println("Please provide name of array you want to create:");
            arrayName = scanner.nextLine();
            for (PFArray i : arrayStorage) {
                if (i.getName().equals(arrayName)) {
                    System.err.println("Array with this name already exists. Choose another name, please.\n");
                    isUnique = false;
                }
            }
        }
        return arrayName;
    }

    private static String chooseArrayType(Scanner scanner) {
        String chosenArrayType = "";
        boolean ifCorrect = true;
        while(chosenArrayType.isBlank() || ifCorrect == false) {
            ifCorrect = true;
            System.out.println("Please write 'random' if you want to create array with random elements, or write 'manual' if you want to create array by yourself:");
            chosenArrayType = scanner.nextLine();
            if(!chosenArrayType.equals(RANDOM) && !chosenArrayType.equals(MANUAL)) {
                System.err.println("The entered value is incorrect.");
                ifCorrect = false;
            }
        }
        return chosenArrayType;
    }

    private static int chooseArraySize(Scanner scanner) {
        int chosenSize = 0;
        while (true) {
            try {
                System.out.println("Please provide size of array:");
                chosenSize = scanner.nextInt();
                scanner.nextLine();
                return chosenSize;
            } catch (InputMismatchException e) {
                System.err.println("Provided value is incorrect. Type number, please.");
                scanner.nextLine();
            }
        }
    }

    private static int[] generateArrayManually(Scanner scanner) {
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
        String arrayName = scanner.nextLine();
        for (PFArray i : arrayStorage) {
            if (i.getName().equals(arrayName)) {
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
        long largeSum = arrayStorage.get(0).sumNumbers();
        for(PFArray i : arrayStorage) {
            if(i.sumNumbers() >= largeSum) {
                largeSum = i.sumNumbers();
            }
        }
        return largeSum;
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