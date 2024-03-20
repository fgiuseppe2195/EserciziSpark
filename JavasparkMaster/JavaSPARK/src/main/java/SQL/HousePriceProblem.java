package SQL;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class HousePriceProblem {
    public static void main(String[] args) {

        SparkSession spark = SparkSession
                .builder()
                .appName("HousePriceProblem")
                .master("local[*]")
                .getOrCreate();


        StructType schema = DataTypes.createStructType(new StructField[]{
                DataTypes.createStructField("MLS", DataTypes.StringType, true),
                DataTypes.createStructField("Location", DataTypes.StringType, true),
                DataTypes.createStructField("Price", DataTypes.DoubleType, true),
                DataTypes.createStructField("Bedrooms", DataTypes.IntegerType, true),
                DataTypes.createStructField("Bathrooms", DataTypes.IntegerType, true),
                DataTypes.createStructField("Size", DataTypes.IntegerType, true),
                DataTypes.createStructField("Price_SQft", DataTypes.DoubleType, true),
                DataTypes.createStructField("Status", DataTypes.StringType, true)
        });


        JavaRDD<Row> rowRDD = spark.read()
                .option("header", true)
                .schema(schema)
                .csv("C:\\Users\\Giuse\\Desktop\\JSexprivia\\JavaSPARK\\in\\RealEstate.csv")
                .javaRDD();


        Dataset<Row> realEstateDF = spark.createDataFrame(rowRDD, schema);


        Dataset<Row> groupedDF = realEstateDF.groupBy("Location")
                .agg(
                        functions.avg("Price_SQft").alias("avg(Price SQ Ft)"),
                        functions.max("Price").alias("max(Price)")
                );


        groupedDF.show();
        spark.stop();
    }
}
