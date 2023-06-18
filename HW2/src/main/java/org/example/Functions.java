package org.example;

import java.io.*;
import java.util.ArrayList;

public class Functions {
    private static int n = 50;
    private static File fileCn = new File("Cn_100000_new_average.txt");
    private static File fileBn = new File("Bn_100000__average.txt");
    private static File fileLn = new File("Ln_100000__average.txt");
    private static File fileUn = new File("Un_100000__average.txt");
    private static File fileDn = new File("Dn_100000_new_average.txt");
    private static File fileDnCn = new File("Dn-Cn_100000_new_average.txt");


    public static void main(String[] args) throws IOException {
        printResultsInTxt(firstFun(findData(fileBn), n), "a1");
        printResultsInTxt(aFun(findData(fileBn), n), "a2");
        printResultsInTxt(firstFun(findData(fileUn), n), "b1");
        printResultsInTxt(bFun(findData(fileLn), n), "c1");
        printResultsInTxt(cFun(findData(fileLn), n), "c2");
        printResultsInTxt(secondFun(findData(fileLn), n), "c3");
        printResultsInTxt(firstFun(findData(fileCn), n), "d1");
        printResultsInTxt(secondFun(findData(fileCn), n), "d2");
        printResultsInTxt(thirdFun(findData(fileCn), n), "d3");
        printResultsInTxt(firstFun(findData(fileDn), n), "e1");
        printResultsInTxt(secondFun(findData(fileDn), n), "e2");
        printResultsInTxt(thirdFun(findData(fileDn), n), "e3");
        printResultsInTxt(firstFun(findData(fileDnCn), n), "f1");
        printResultsInTxt(secondFun(findData(fileDnCn), n), "f2");
        printResultsInTxt(dFun(findData(fileDnCn), n), "f3");

    }

    private static void printResultsInTxt(ArrayList<Double> results, String fileName) {
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(fileName);
            for (double d : results) {
                myWriter.write(String.valueOf(d));
                myWriter.write(' ');
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static ArrayList<Double> findData(File file) throws IOException {
        ArrayList<Double> data = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            String[] numsSt = st.replace(",", ""). replace("[", "").replace("]", "").split(" ");
            for (String string : numsSt) {
                data.add(Double.parseDouble(string));
            }
        }
        return data;
    }
    //1  x(n)/n
    private static ArrayList<Double> firstFun(ArrayList<Double> data, int n) {
        ArrayList<Double> res = new ArrayList<>();
        for (double d : data) {
            res.add(d/n);
        }
        return res;
    }

    //2  x(n)/(n ln n)
    private static ArrayList<Double> secondFun(ArrayList<Double> data, int n) {
        ArrayList<Double> res = new ArrayList<>();
        double dev = n * Math.log(n);
        for (double d : data) {
            res.add(d/dev);
        }

        return res;
    }
    //3  x(n)/ (n^2)
    private static ArrayList<Double> thirdFun(ArrayList<Double> data, int n) {
        ArrayList<Double> res = new ArrayList<>();
        double dev = n * n;
        for (double d : data) {
            res.add(d/dev);
        }

        return res;
    }



    //a  b(n)/sqrt(n)
    private static ArrayList<Double> aFun(ArrayList<Double> data, int n) {
        ArrayList<Double> res = new ArrayList<>();
        double dev = Math.sqrt(n);
        for (double d : data) {
            res.add(d/dev);
        }

        return res;
    }
    //b   l(n)/ ln n
    private static ArrayList<Double> bFun(ArrayList<Double> data, int n) {
        ArrayList<Double> res = new ArrayList<>();
        double dev = Math.log(n);
        for (double d : data) {
            res.add(d/dev);
        }
        return res;
    }
    //c   l(n) /[(ln n)/(ln ln n)]
    private static ArrayList<Double> cFun(ArrayList<Double> data, int n) {
        ArrayList<Double> res = new ArrayList<>();
        double dev = (Math.log(n))/(Math.log(Math.log(n)));
        for (double d : data) {
            res.add(d/dev);
        }
        return res;
    }
    //d    Dn-Cn/ (n ln ln n)
    private static ArrayList<Double> dFun(ArrayList<Double> data, int n) {
        ArrayList<Double> res = new ArrayList<>();
        double dev = n * (Math.log(Math.log(n)));
        for (double d : data) {
            res.add(d/dev);
        }
        return res;
    }




}
