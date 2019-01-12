package data_algorithm.chapter_7;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class MBAMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //T1:crackers,icecream,coke,apple
        String [] line = value.toString().split("[,:]");// split by comma or colon
        Arrays.sort(line); // sort
        System.out.println(Arrays.toString(line));
        for(int i = 1;i<line.length;i++) {
            String curWord = line[i];
            String combineKey ;
            for(int j = 1;j < line.length && i!=j; j++) {
                combineKey = "[" + curWord + "," + line[j] + "]";
                context.write(new Text(combineKey),new IntWritable(1));
            }
        }
    }

    public static void main(String[] args) {
        String value = "T1:crackers,icecream,coke,apple";
        String [] line = value.toString().split("[,:]");// split by comma or colon
        Arrays.sort(line);
        for(int i = 1;i<line.length;i++) {
            System.out.print(line[i]+" ");
        }
    }
}
