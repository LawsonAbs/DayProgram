package data_algorithm.chapter_4.step_2_leftJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 1.用于对用户信息的Mapper
 */
public class SecondMapper extends Mapper<LongWritable,Text,Text,Text>{

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line [] = value.toString().split("[,\t]");//split by tab or space
        System.out.println("value: "+value.toString());
        if (line.length > 2) {
            String location = line[1];
            System.out.println("location = "+location);
            for(int i = 2;i< line.length;i++) {
                context.write(new Text(line[i]),new Text(location));
            }
        }
    }

    public static void main(String[] args) {
        //String str = "u1 UT,p4,p1,p1,p3,";
        String str = "u4\tCA,p4,";
        String line [] = str.toString().split("[, ]");
        for(int i =0;i< line.length;i++) {
            System.out.println("i="+i+" ,"+line[i]);
        }
    }
}
