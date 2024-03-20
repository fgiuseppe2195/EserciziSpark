package PariRDD;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import scala.Tuple2;

public class AirportUpperCaseProblem{
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("AirportData").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\airports.txt");
        JavaPairRDD<String, String> airportData = lines.mapToPair(line -> {
            String[] columns = line.split(",");
            return new Tuple2<>(columns[1], columns[3].toUpperCase());
        });

        airportData.saveAsTextFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\out\\airports_uppercase.txt");

        sc.close();
    }
}
