package cz.pf;

import cz.pf.array.PFArray;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final ArrayList<PFArray> arrayStorage = new ArrayList<>();
    private static final String RANDOM = "1";
    private static final String MANUAL = "2";
    private static final String TYPED_NUMBER_ERROR = "Provided value is incorrect. Type number, please.";
    private static final String INCORRECT_VALUE_ERROR = "The entered value is incorrect.\n";
    private static final String NO_AVAILABLE_ARRAY_ERROR = "There is not any available array. First create array, please.";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                System.out.println("Choose and type option from following:\n1 -to create a new array\n2 -to select the array to work with\n3 -to find the array with the largest sum of elements\n4 -to find the array with the smallest sum of elements\n5 -to finish the program");
                int chosenOption = scanner.nextInt();
                scanner.nextLine();

                if (chosenOption == OptionWithArray.CREATE_ARRAY.optionId) {
                    String name = chooseArrayName(scanner);
                    if(name.equals("menu")){
                        continue;
                    }
                    String type = chooseArrayType(scanner);

                    if (type.equals(RANDOM)) {
                        PFArray pfArray = new PFArray(generateArrayRandomly(), name);
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

                } else if (chosenOption == OptionWithArray.SELECT_ARRAY.optionId) {
                        if (arrayStorage.isEmpty()) {
                            System.err.println(NO_AVAILABLE_ARRAY_ERROR);
                            continue;
                        }
                        chooseArrayAction(scanner);

                } else if (chosenOption == OptionWithArray.LARGEST_SUM.optionId) {
                    if (arrayStorage.isEmpty()) {
                        System.err.println(NO_AVAILABLE_ARRAY_ERROR);
                        continue;
                    }
                    PFArray largeSumArray = findArrayWithLargestSum();
                    System.out.println("Array with the largest sum is: " + largeSumArray.getName()+ " with count " + largeSumArray.sumNumbers());
                    System.out.println();
                } else if (chosenOption == OptionWithArray.SMALLEST_SUM.optionId) {
                    if (arrayStorage.isEmpty()) {
                        System.err.println(NO_AVAILABLE_ARRAY_ERROR);
                        continue;
                    }
                    PFArray smallSumArray = findArrayWithSmallestSum();
                    System.out.println("Array with the smallest sum is: " + smallSumArray.getName() + " with count " + smallSumArray.sumNumbers());
                    System.out.println();
                } else if (chosenOption == OptionWithArray.FINISH.optionId) {
                    System.out.println("Goodbye");
                    System.exit(0);
                } else {
                    System.err.println(INCORRECT_VALUE_ERROR);
                }
            } catch (InputMismatchException e) {
                System.err.println(TYPED_NUMBER_ERROR);
                scanner.nextLine();
            }
        }
    }


    public static int[] generateArrayRandomly() {
        Random rand = new Random();
        int[] arr = new int[rand.nextInt(1000)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new SecureRandom().nextInt();
        }
        return arr;
    }

    private static String chooseArrayName(Scanner scanner) {
        String arrayName = null;
        while(arrayName == null) {
            System.out.println("Please provide name of array you want to create:");
            arrayName = scanner.nextLine();
            for (PFArray pfArray : arrayStorage) {
                if (pfArray.getName().equals(arrayName)) {
                    System.err.println("Array with this name already exists. Please, type anything to return to the input of the name or type 'menu' to return to the menu");
                    String chosenOption = scanner.nextLine();
                    if(chosenOption.equals("menu")) {
                        arrayName = "menu";
                    } else {
                        arrayName = null;
                    }
                }
            }
        }
        return arrayName;
    }

    private static String chooseArrayType(Scanner scanner) {
        String chosenArrayType = null;
        while(chosenArrayType == null) {
            System.out.println("Please type:\n1 -to create array with random elements\n2 -to create array manually");
            chosenArrayType = scanner.nextLine();
            if(!chosenArrayType.equals(RANDOM) && !chosenArrayType.equals(MANUAL)) {
                System.err.println(INCORRECT_VALUE_ERROR);
                chosenArrayType = null;
            }
        }
        return chosenArrayType;
    }


    private static int[] generateArrayManually(Scanner scanner) {
        System.out.println("Please provide numbers to store in your array divided by space:");

        String manualNumbers = scanner.nextLine();
        String[] array = manualNumbers.split(" ");
        int[] manualArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            try {
                manualArray[i] = Integer.parseInt(array[i]);
            } catch (NumberFormatException e) {
                System.err.println(INCORRECT_VALUE_ERROR);
            }

        }
        return manualArray;

    }

    private static PFArray selectArray(Scanner scanner) {
        while(true) {
            int arrayId = 1;
            System.out.println("Existing arrays:");
            for (PFArray pfArray : arrayStorage) {
                System.out.println(arrayId + " " + pfArray.getName());
                arrayId++;
            }

            System.out.println("Type number of array you want to work with:");
            try {
                int arrayNumber = scanner.nextInt();
                scanner.nextLine();
                if (arrayNumber < arrayId && arrayNumber > 0) {
                    return arrayStorage.get(arrayNumber - 1);
                }
                System.err.println("Typed element was not found.");
            } catch (InputMismatchException e) {
                System.err.println(TYPED_NUMBER_ERROR);
                scanner.nextLine();
            }
        }
    }

    private static void chooseArrayAction(Scanner scanner) {
        PFArray pfArray = selectArray(scanner);
        while(true) {
            try {
                System.out.println("Choose and type option from following:\n1 -to print selected array\n2 -to add number to the selected array\n3 -to remove number from the selected array\n4 -to find the largest number in selected array\n5 -to find the smallest number in selected array\n6 -to find sum of numbers in the selected array\n7 -to delete the selected array\n8 -to regenerate numbers in the selected array\n9 -to return to the main menu");
                int actionWithArray = scanner.nextInt();
                scanner.nextLine();

                if (actionWithArray == ActionWithArray.PRINT_ARRAY.actionId) {
                    System.out.println(pfArray.toString() + "\n");
                } else if (actionWithArray == ActionWithArray.ADD_NUMBER.actionId) {
                    System.out.println("Type number, that you want to add:" + "\n");
                    pfArray.addNumberToArray(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Number is added. Updated array: " + pfArray.toString() + "\n");
                } else if (actionWithArray == ActionWithArray.REMOVE_NUMBER.actionId) {
                    pfArray.removeNumberFromArray();
                    System.out.println("Number is removed. Updated array: " + pfArray.toString() + "\n");
                } else if (actionWithArray == ActionWithArray.LARGEST_NUMBER.actionId) {
                    pfArray.getLargestNumber();
                    System.out.println("The largest number of selected array is: " + pfArray.getLargestNumber() + "\n");
                } else if (actionWithArray == ActionWithArray.SMALLEST_NUMBER.actionId) {
                    pfArray.getSmallestNumber();
                    System.out.println("The smallest number of selected array is: " + pfArray.getSmallestNumber() + "\n");
                } else if (actionWithArray == ActionWithArray.SUM_NUMBERS.actionId) {
                    pfArray.sumNumbers();
                    System.out.println("The sum of numbers of selected array is: " + pfArray.sumNumbers() + "\n");
                } else if (actionWithArray == ActionWithArray.DELETE_ARRAY.actionId) {
                    arrayStorage.remove(pfArray);
                    System.out.println("The selected array is deleted" + "\n");
                    break;
                } else if (actionWithArray == ActionWithArray.REGENERATE_ARRAY.actionId) {
                    pfArray.regenerateArray();
                    System.out.println("Regenerated array: " + pfArray.toString() + "\n");
                } else if (actionWithArray == ActionWithArray.RETURN_TO_MENU.actionId) {
                    break;
                } else {
                    System.err.println(INCORRECT_VALUE_ERROR);
                }
            } catch (InputMismatchException e) {
                System.err.println(TYPED_NUMBER_ERROR);
                scanner.nextLine();
            }
        }

    }

    private static PFArray findArrayWithLargestSum() {
        PFArray largestArray = arrayStorage.get(0);
        long largeSum = largestArray.sumNumbers();
        for(PFArray pfArray : arrayStorage) {
            if(pfArray.sumNumbers() >= largeSum) {
                largeSum = pfArray.sumNumbers();
                largestArray = pfArray;
            }
        }
        return largestArray;
    }

    private static PFArray findArrayWithSmallestSum() {
        PFArray smallestArray = arrayStorage.get(0);
        long smallSum = smallestArray.sumNumbers();
        for(PFArray pfArray : arrayStorage) {
            if(pfArray.sumNumbers() < smallSum) {
                smallSum = pfArray.sumNumbers();
                smallestArray = pfArray;
            }
        }
        return smallestArray;
    }

}