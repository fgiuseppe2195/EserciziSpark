package PariRDD;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class SortedWordCountProblem {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("SortedWordCountProblem").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\word_count.txt");
        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
        JavaRDD<String> wordCounts = words.mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey(Integer::sum)
                .mapToPair(pair -> new Tuple2<>(pair._2, pair._1))
                .sortByKey(false)
                .map(pair -> pair._2 + " : " + pair._1);

        wordCounts.saveAsTextFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\out\\word1_count_output");

        sc.close();
    }
}
