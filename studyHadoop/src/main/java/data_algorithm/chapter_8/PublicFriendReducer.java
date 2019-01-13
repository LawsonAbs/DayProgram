package data_algorithm.chapter_8;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import scala.tools.cmd.gen.AnyVals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PublicFriendReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Set<String> set = new HashSet<String>();
        StringBuilder sb = new StringBuilder();

        String [] friendList;
        for (Text fr : values) {
            friendList = fr.toString().split(" ");
            for (String fr_id : friendList) {
                if (set.contains(fr_id)) {
                    sb.append(fr_id).append(",");
                } else {
                    set.add(fr_id);
                }
            }
        }
        context.write(key,new Text(sb.toString()));
    }
}
