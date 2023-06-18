package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static int amountOfBins;
    private static int times;

    public static void main(String[] args) {
        times = 40;
        amountOfBins = 100000;
        for (int i = 62000; i <= amountOfBins; i += 1000) {
            System.out.println("Times " + i);
//            int[] resultsBn = generateStatistic("Bn", i, times);
//            printResultsAndAverage("Bn", resultsBn);
//            writeResultsToTxt("Bn", resultsBn, amountOfBins);

//            int[] resultsUn = generateStatistic("Un", i, times);
//            printResultsAndAverage("Un", resultsUn);
//            writeResultsToTxt("Un", resultsUn, amountOfBins);
//
//            int[] resultsLn = generateStatistic("Ln", i, times);
//            printResultsAndAverage("Ln", resultsLn);
//            writeResultsToTxt("Ln", resultsLn, amountOfBins);
//
//            int[] resultsCn = generateStatistic("Cn", i, times);
//            printResultsAndAverage("Cn", resultsCn);
//            writeResultsToTxt("Cn", resultsCn, amountOfBins);

//            int[] resultsDn = generateStatistic("Dn", i, times);
//            printResultsAndAverage("Dn", resultsDn);
//            writeResultsToTxt("Dn", resultsDn, amountOfBins);
//
//            int[] resultsSupDnCn = generateStatistic("Dn-Cn", i, times);
//            printResultsAndAverage("Dn-Cn", resultsSupDnCn);
//            writeResultsToTxt("Dn-Cn", resultsSupDnCn, amountOfBins);
        }
    }
    private static void printResultsAndAverage(String name, int[] results) {
        System.out.println(name.toUpperCase());
        System.out.println(name + " results:" + Arrays.toString(results));
        System.out.println("Average value: " + findTheMiddle(results, times));
    }

    private static void writeResultsToTxt(String name, int[] results, int amountOfBins) {
        StringBuilder fileNameResults = new StringBuilder();
        fileNameResults.append(name).append("_").append(amountOfBins).append("__").append(results.length).append("times").append(".txt");

        StringBuilder fileNameAverage = new StringBuilder();
        fileNameAverage.append(name).append("_").append(amountOfBins).append("__").append("average 5times");

        try {
            File myObj = new File(String.valueOf(fileNameResults));

            File myObjAve = new File(String.valueOf(fileNameAverage));

            if(!myObj.exists()) {
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());

                } else {
                    System.out.println("File already exists.");
                }
            }

            if(!myObjAve.exists()) {
                if (myObjAve.createNewFile()) {
                    System.out.println("File created: " + myObjAve.getName());
                } else {
                    System.out.println("File already exists.");
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriterResults = new FileWriter(String.valueOf(fileNameResults),true);
            FileWriter myWriterAve = new FileWriter(String.valueOf(fileNameAverage),true);

            myWriterResults.write(Arrays.toString(results) + "\n");
            myWriterAve.write((findTheMiddle(results, times)) + " ");
            myWriterResults.close();
            myWriterAve.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static int[] generateStatistic(String name,int amountOfBins, int times) {
        int[] results = new int[times];
        for (int i = 0; i < times; i++) {
            switch (name) {
                case "Bn":
                    results[i] = count(name, amountOfBins);
                case "Un":
                    results[i] = count(name, amountOfBins);
                case "Ln":
                    results[i] = count(name, amountOfBins);
                case "Cn":
                    results[i] = count(name, amountOfBins);
                case "Dn":
                    results[i] = count(name, amountOfBins);
                case "Dn-Cn":
                    results[i] = count(name, amountOfBins);
            }
        }
        return results;
    }

    private static int count(String name, int amountOfBins) {
        ArrayList<Integer> bins = makeStructure(amountOfBins);
        switch (name) {
            case "Bn" -> {
                int countB = 0;
                while (true) {
                    countB++;
                    int randomNum = getRandomNumber(amountOfBins);
                    int currentBin = randomNum - 1;
                    int currentBinValue = bins.get(randomNum - 1);
                    bins.set(currentBin, ++currentBinValue);
                    if (currentBinValue > 1) {
                        break;
                    }
                }
                return countB;
            }
            case "Un" -> {
                for (int i = 0; i < amountOfBins; i++) {
                    int randomNum = getRandomNumber(amountOfBins);
                    int currentBin = randomNum - 1;
                    int currentBinValue = bins.get(randomNum - 1);
                    bins.set(currentBin, ++currentBinValue);
                }
                int countEmptyBins = 0;
                for (int bin : bins) {
                    if (bin == 0) {
                        countEmptyBins++;
                    }
                }
                return countEmptyBins;
            }
            case "Ln" -> {
                int max = 0;
                for (int i = 0; i < amountOfBins; i++) {
                    int randomNum = getRandomNumber(amountOfBins);
                    int currentBin = randomNum - 1;
                    int currentBinValue = bins.get(randomNum - 1);
                    bins.set(currentBin, ++currentBinValue);
                    if (currentBinValue + 1 > max) {
                        max = currentBinValue;
                    }
                }
                return max;
            }
            case "Cn" -> {
                int countC = 0;
                while (bins.contains(0)) {
                    countC++;
                    int randomNum = getRandomNumber(amountOfBins);
                    int currentBin = randomNum - 1;
                    int currentBinValue = bins.get(randomNum - 1);
                    bins.set(currentBin, ++currentBinValue);
                }
                return countC;
            }
            case "Dn" -> {
                int countD = 0;
                while (bins.contains(0) || bins.contains(1)) {
                    countD++;
                    int randomNum = getRandomNumber(amountOfBins);
                    int currentBin = randomNum - 1;
                    int currentBinValue = bins.get(randomNum - 1);
                    bins.set(currentBin, ++currentBinValue);
                }
                return countD;
            }
            case "Dn-Cn" -> {
                int countK1 = 0;
                while (bins.contains(0)) {
                    countK1++;
                    int randomNum = getRandomNumber(amountOfBins);
                    int currentBin = randomNum - 1;
                    int currentBinValue = bins.get(randomNum - 1);
                    bins.set(currentBin, ++currentBinValue);
                }
                int countK2 = countK1;
                while (bins.contains(1)) {
                    countK2++;
                    int randomNum = getRandomNumber(amountOfBins);
                    int currentBin = randomNum - 1;
                    int currentBinValue = bins.get(randomNum - 1);
                    bins.set(currentBin, ++currentBinValue);
                }
                return countK2 - countK1;
            }
            default -> throw new IllegalArgumentException("the wrong fun was given");
        }
    }
    public static int getRandomNumber(int amountOfBins) {
        return ThreadLocalRandom.current().nextInt(1, amountOfBins + 1);
    }

    private static ArrayList<Integer> makeStructure(int amountOfBins) {
        ArrayList<Integer> bins = new ArrayList<>();
        for (int i = 0; i < amountOfBins; i++) {
            bins.add(i, 0);
        }
        return bins;
    }

    private static double findTheMiddle(int[] results, int amountOfBins) {
        int sum = 0;
        for(int result : results)
            sum += result;
        return (double) sum/amountOfBins;
    }
}