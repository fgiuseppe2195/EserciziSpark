package PariRDD;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class AirportByCountryProblem {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("AirportByCountryProblem").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airports = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\airports.txt");

        airports.mapToPair(line -> {
            String[] columns = line.split(",");
            return new Tuple2<>(columns[3], columns[1]);
        }).groupByKey().foreach(tuple -> {
            System.out.println("\"" + tuple._1 + "\", " + tuple._2.toString());
        });

        sc.close();
    }
}
