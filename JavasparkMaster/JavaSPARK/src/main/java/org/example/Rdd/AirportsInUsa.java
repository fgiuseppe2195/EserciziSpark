package org.example.Rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class AirportsInUsa {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Airports").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\airports.txt");
        JavaRDD<String> airportsInUsa = lines.filter(line -> {
            String[] attributes = line.split(",");
            String country = attributes[3].replaceAll("\"", "");
            return country.equalsIgnoreCase("United States");
        });

        JavaRDD<String> airportInfo = airportsInUsa.map(line -> {
            String[] attributes = line.split(",");
            String airportName = attributes[1].replaceAll("\"", "");
            String cityName = attributes[2].replaceAll("\"", "");
            return "\"" + airportName + "\", \"" + cityName + "\"";
        });

        airportInfo.saveAsTextFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\out\\aeroportiinusa.txt");


        sc.close();
    }
}
