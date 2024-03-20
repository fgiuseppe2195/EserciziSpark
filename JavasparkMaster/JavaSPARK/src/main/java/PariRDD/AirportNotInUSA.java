package PariRDD;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class AirportNotInUSA {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("AirportNotInUSA").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airports = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\airports.txt");

        JavaPairRDD<String, String> airportPairRDD = airports.mapToPair(line -> {
            String[] columns = line.split(",");
            return new Tuple2<>(columns[1], columns[3]);
        });

        JavaPairRDD<String, String> airportsNotInUSA = airportPairRDD.filter(pair -> !pair._2.equals("\"United States\""));

        airportsNotInUSA.saveAsTextFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\out\\airportsnotinusapairrdd.txt");

        sc.close();
    }
}
