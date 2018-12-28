package hadoopDefinitiveGuide.chapter_2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WetherReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int max = 0;
        for(IntWritable v: values) {
            if (v.get() > max){
                max = v.get();
            }
        }
        context.write(key,new IntWritable(max));
    }
}
