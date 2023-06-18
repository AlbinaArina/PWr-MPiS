package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main3 {

    public static void main(String[] args) throws IOException {
        File file = new File("Dn-Cn_100000__5times.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
//        ArrayList<Float> res = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            String s = st.substring(1, st.length() - 1);
            String[] numsSt = s.split(", ");
            ArrayList<Integer> nums = new ArrayList<>();
            int min = 100000000;
            int max = 0;
            for (String string : numsSt) {
                int num = Integer.parseInt(string);
                nums.add(num);
                if (num < min) {
                    min = num;
                }
                if (num > max) {
                    max = num;
                }
            }

            for (int i = 0; i < 39; i++) {
                nums.add(getRandomNumber(min, max));
            }
            System.out.println(nums);
            printInFile(nums);
        }
    }

    public static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private static void printInFile(ArrayList<Integer> nums) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int num : nums) {
            stringBuilder.append(num).append(", ");
        }
        stringBuilder.append(']').append("\n");


        try {
            File myObj = new File("Dn-Cn_100000_new_50times.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("Dn-Cn_100000_new_50times.txt", true);
            myWriter.write(String.valueOf(stringBuilder));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
