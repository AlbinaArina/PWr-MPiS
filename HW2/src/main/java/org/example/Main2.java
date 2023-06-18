package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main2 {

    public static void main(String[] args) throws IOException {
        File file = new File("Cn_100000__50times.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        ArrayList<Float> res = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            String s = st.substring(1, st.length() - 1);
            String[] numsSt = s.split(", ");
            ArrayList<Integer> nums = new ArrayList<>();
            int sum = 0;
            for (String string : numsSt) {
                nums.add(Integer.parseInt(string));
                sum = sum + Integer.parseInt(string);
            }
            res.add((float) (sum/50.0));
        }


        System.out.println(res);
//_______________________________________________
        try {
            File myObj = new File("Cn_______100000_new_average.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("Cn_______100000_new_average.txt");
            myWriter.write(String.valueOf(res));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }




    }

    public static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
