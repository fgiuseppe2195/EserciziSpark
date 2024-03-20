package org.example.Rdd;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.WARN);
        Logger.getLogger("akka").setLevel(Level.WARN);


        SparkConf conf = new SparkConf().setAppName("WordCount").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);


        JavaRDD<String> lines = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\word_count.txt");
        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
        Map<String, Long> wordCounts = words.countByValue();


        for (Map.Entry<String, Long> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        sc.stop();
    }
}


