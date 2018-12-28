package hadoopDefinitiveGuide.chapter_2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 01.我不是很清楚，为什么这里的 KEYIN 必须是 LongWritable，而不能是 IntWritable
 */
public class WetherMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    public final int MISS = 9999; //stand for miss value

    public void map(LongWritable keyIN,Text valueIN,Context context) throws IOException, InterruptedException {
        String temp =  valueIN.toString().substring(87,92);
        if(!temp.contains("9999")){
            int temperature = Integer.parseInt(temp); // transform to integer
            String year = valueIN.toString().substring(15,19);
            //System.out.println("年份是："+year);System.out.println("温度值是："+temperature);
            context.write(new Text(year),new IntWritable(temperature));
        }
    }
}
