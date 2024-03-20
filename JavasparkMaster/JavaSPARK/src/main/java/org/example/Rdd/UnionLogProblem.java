package org.example.Rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class UnionLogProblem {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("UnionLogProblem").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);


        JavaRDD<String> julyLogs = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\nasa_19950701.tsv");
        JavaRDD<String> augustLogs = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\nasa_19950801.tsv");


        String julyHeader = julyLogs.first();
        String augustHeader = augustLogs.first();

        JavaRDD<String> julyLogsWithoutHeader = julyLogs.filter(line -> !line.equals(julyHeader));
        JavaRDD<String> augustLogsWithoutHeader = augustLogs.filter(line -> !line.equals(augustHeader));


        JavaRDD<String> combinedLogs = julyLogsWithoutHeader.union(augustLogsWithoutHeader);
        JavaRDD<String> sampledLogs = combinedLogs.sample(false, 0.1);


        sampledLogs.saveAsTextFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\out\\sample_nasa_logs.tsv");

        sc.close();
    }
}
