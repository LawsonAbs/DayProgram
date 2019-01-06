package data_algorithm.test;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TestMapper_1 extends Mapper<LongWritable, Text, Text, IntWritable> {

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line []= value.toString().split(" ");
        for (String temp : line) {
            context.write(new Text(temp),new IntWritable(1));
        }
    }
}
