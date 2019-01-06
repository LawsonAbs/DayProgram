package data_algorithm.chapter_1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 1.KEYIN =  LongWritable
 * 2.VALUEIN = Text
 * 3.KEYOUT = DateTemperaturePair
 * 4.VALUEOUT = Text
 *
 */
public class SecondarySortMapper extends Mapper<LongWritable,Text,DateTemperaturePair,Text>{

    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String [] tokens = value.toString().split(",");
        String yearMonth = tokens[0] + tokens[1];
        String day = tokens[2];
        int temperature = Integer.parseInt(tokens[3]);

        System.out.println("yearMonth:"+yearMonth+" day"+day+" temperature"+temperature);

        //准备规约器
        DateTemperaturePair reducerKey = new DateTemperaturePair();

        reducerKey.setYearMonth(yearMonth);
        reducerKey.setDay(day);
        reducerKey.setTemperature(temperature);

        //如果是简单的一个mapReduce程序，那么仅仅需要实现
        //context.write(yearMonth,temperature); 即可
        //但是因为这样难以实现二次排序的目的，所以将其封装到了一个Entity —— DateTemperaturePair类中
        //这个DateTemperaturePair 类实现了Writable 和 WritableComparable 两个接口，分别用于实现序列化 和 可比较 两个方法
        //注意这里 Mapper写出的key  是一个对象
        context.write(reducerKey,new Text(Integer.toString(temperature)));
    }
}
