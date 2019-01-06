package data_algorithm.chapter_4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class UserPartitioner extends Partitioner<User,Text> {
        //这里的numPartitions 是什么值？在哪里传参？
        public int getPartition(User user, Text text, int numPartitions) {
            return Math.abs(user.hashCode() % numPartitions);
        }
}
