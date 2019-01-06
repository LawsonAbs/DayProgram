package data_algorithm.chapter_3;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

public class TopN_Reducer extends Reducer<NullWritable,Text,NullWritable,Text> {
    private final int N = 5;

    @Override
    protected void reduce(NullWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        SortedMap<Double, String> finalTop10 = new TreeMap<Double, String>();
        for (Text catRecord : values) {
            String[] tokens = catRecord.toString().split(",");
            Double weight = Double.parseDouble(tokens[0]);
            finalTop10.put(weight, catRecord.toString());

            if (finalTop10.size() > N) {
                finalTop10.remove(finalTop10.firstKey());
            }
        }
        for (String text : finalTop10.values()) {
            context.write(NullWritable.get(), new Text(text));
        }
    }
}
