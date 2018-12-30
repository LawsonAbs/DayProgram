package data_algorithm;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 1.KEYIN = DateTemperaturePair
 * 2.VALUEIN = Text
 * 3.KEYOUT = Text
 * 4.valueOUt = text
 */
public class SecondarySortReducer extends Reducer<DateTemperaturePair ,Text,Text ,Text > {

    public void reduce(DateTemperaturePair key, Iterable<Text> values,Context context)
            throws IOException, InterruptedException {
        StringBuilder sortedTemperatureList = new StringBuilder();
        for (Text temperature : values) {
            sortedTemperatureList.append(temperature);
            sortedTemperatureList.append(",");
        }
        //因为这里的定义的
        Text outputKey = new Text(key.getYearMonth());
        System.out.println("outputKey is: "+outputKey.toString());

        Text outputValue = new Text(sortedTemperatureList.toString());
        System.out.println("outputValue is: "+outputValue.toString());
        context.write(outputKey, outputValue);
    }
}
/*
1.Iterable<IntWritable> 和 Iterator<IntWritable>的区别是啥？
 */