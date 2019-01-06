package data_algorithm.chapter_4;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 1.用于对用户信息的Mapper
 */
public class TransactionMapper extends Mapper<LongWritable,Text,User,Text>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line [] = value.toString().split(" ");
        String user_prd = line[1];
        String user_id = line[2];

        //构建一个user对象，其中location为空
        User user = new User(user_id,2,"empty",user_prd);
        //输出的格式是 user user_prd
        context.write(user, new Text(user_prd));
    }
}
