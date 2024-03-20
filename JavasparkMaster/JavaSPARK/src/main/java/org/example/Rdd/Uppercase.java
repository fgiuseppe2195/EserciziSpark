package org.example.Rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;


public class Uppercase {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Uppercase").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();

        JavaRDD<String> inputRDD = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\uppercase.txt");
        JavaRDD<String> uppercaseRDD = inputRDD.map(String::toUpperCase);


        Dataset<String> ds = spark.createDataset(uppercaseRDD.rdd(), Encoders.STRING());


        ds.show();

        uppercaseRDD.saveAsTextFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\out\\uppercase.txt");
    }
}