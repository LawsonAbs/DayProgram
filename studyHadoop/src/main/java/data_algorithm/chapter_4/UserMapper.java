package data_algorithm.chapter_4;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 1.用于对用户信息的Mapper
 */
public class UserMapper extends Mapper<LongWritable,Text,User,Text>{

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line [] = value.toString().split(" ");
        String user_id = line[0];

        String user_loc = line[1];
        //写入一个user对象 -> 其中product_name 字段为空
        User user = new User(user_id,1,user_loc,"empty");

        //输出的格式是：user user_loc
        context.write(user,new Text(user_loc));
        //System.out.println("UserMapper End...");
    }
}
