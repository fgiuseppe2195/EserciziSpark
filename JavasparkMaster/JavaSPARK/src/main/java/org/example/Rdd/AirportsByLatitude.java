package org.example.Rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class AirportsByLatitude {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("AirportsByLatitude").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\airports.txt");
        JavaRDD<String> filteredAirports = lines.filter(line -> {
        String[] attributes = line.split(",");
        return attributes.length >= 7 && isValidDouble(attributes[6]) && Double.parseDouble(attributes[6]) > 40;
        });

        JavaRDD<String> airportInfo = filteredAirports.map(line -> {
            String[] attributes = line.split(",");
            String airportName = attributes[1];
            double latitude = Double.parseDouble(attributes[6]);
            return airportName + ", " + latitude;
        });


        airportInfo.saveAsTextFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\out\\aeroportilatitudine.txt");


        sc.close();
    }


    private static boolean isValidDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
