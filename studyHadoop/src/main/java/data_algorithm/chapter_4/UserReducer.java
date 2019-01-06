package data_algorithm.chapter_4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class UserReducer extends Reducer<User,Text,Text,Text> {
    @Override
    protected void reduce(User key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        for (Text tx : values) {
            sb.append(tx.toString()) .append( ",");
        }
        System.out.println("key："+key+",value: "+sb.toString());
        context.write(new Text(key.getUser_id()),new Text(sb.toString()));
    }
}
