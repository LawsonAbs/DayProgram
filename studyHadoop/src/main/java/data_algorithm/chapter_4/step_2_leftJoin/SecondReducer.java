package data_algorithm.chapter_4.step_2_leftJoin;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SecondReducer extends Reducer<Text,Text,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Set<String> result = new HashSet<String>();
        for (Text value : values) {
            result.add(value.toString());
        }
        context.write(key, new IntWritable(result.size()));
    }
}
