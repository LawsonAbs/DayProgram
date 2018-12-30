package data_algorithm;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 1.Partition 的作用是：根据mapper的输出键来决定 mapper的输出发送到指定的Reducer中
 * 2.自然键指的是Reducer中的输出键； 组合键指的是Reducer中输出键+另外的键，是组合而来的
 * 3.它的功能是  【完成分区，并发送给相应的 Reducer】
 */
public class DateTemperaturePartitioner extends Partitioner<DateTemperaturePair,Text>{

    //这里的numPartitions 是什么值？在哪里传参？
    public int getPartition(DateTemperaturePair pair, Text text, int numPartitions) {
        System.out.println("start partition....");
        return Math.abs(pair.getYearMonth().hashCode() % numPartitions);
    }
}
