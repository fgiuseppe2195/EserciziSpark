package org.example.Rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CountByValueJob {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("CountByValueJob").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);


        List<String> data = Arrays.asList("spark", "hadoop", "spark", "hive", "pig", "cassandra", "hadoop");
        JavaRDD<String> rdd = sc.parallelize(data);


        Map<String, Long> counts = rdd.countByValue();


        for (Map.Entry<String, Long> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        sc.close();
    }
}
