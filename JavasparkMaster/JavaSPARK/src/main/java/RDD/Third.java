package RDD;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;

import java.util.Arrays;
import java.util.List;

public class Third {
    public static void main(String[] args) {


        // Configurazione di Spark
        SparkConf conf = new SparkConf()
                .setAppName("Third")
                .setMaster("local[*]"); // Usa tutti i core della CPU


        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> dataList = Arrays.asList("spark", "hadoop", "spark", "hive", "pig", "cassandra", "hadoop");
        JavaRDD<String> rdd = sc.parallelize(dataList);


        String thirdElement = rdd.take(5).get(2);

        System.out.println("Terzo elemento dell'RDD: " + thirdElement);


        sc.close();
    }
}
