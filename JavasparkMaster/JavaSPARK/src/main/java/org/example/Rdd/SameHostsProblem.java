package org.example.Rdd;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
public class SameHostsProblem {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("SameHosts").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);


        JavaRDD<String> julyLogs = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\nasa_19950701.tsv");
        JavaRDD<String> augustLogs = sc.textFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\nasa_19950801.tsv");


        String julyHeader = julyLogs.first();
        String augustHeader = augustLogs.first();

        JavaRDD<String> julyLogsWithoutHeader = julyLogs.filter(line -> !line.equals(julyHeader));
        JavaRDD<String> augustLogsWithoutHeader = augustLogs.filter(line -> !line.equals(augustHeader));


        JavaRDD<String> julyHosts = julyLogsWithoutHeader.map(line -> line.split("\t")[0]);
        JavaRDD<String> augustHosts = augustLogsWithoutHeader.map(line -> line.split("\t")[0]);


        JavaRDD<String> sameHosts = julyHosts.intersection(augustHosts);

        sameHosts.saveAsTextFile("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\out\\nasa_logs_same_hosts.csv");

        sc.close();
    }
}
