package RDD;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;

import java.util.Arrays;
import java.util.List;

public class Lista {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf()
                    .setAppName("Lista")
                    .setMaster("local[*]");


        JavaSparkContext sc = new JavaSparkContext(conf);


        List<String> dataList = Arrays.asList("spark", "hadoop", "spark", "hive", "pig", "cassandra", "hadoop");
        JavaRDD<String> rdd = sc.parallelize(dataList);


        List<String> resultList = rdd.collect();


        System.out.println("Lista risultante: " + resultList);


        sc.close();
    }
}
