package data_algorithm.chapter_5;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class PairPartition extends Partitioner<PairOfWord,IntWritable> {
    @Override
    public int getPartition(PairOfWord pairOfWord, IntWritable intWritable, int numPartitions) {
        return Math.abs(pairOfWord.hashCode() % numPartitions);
    }

    public static void main(String[] args) {
        System.out.println("language:"+"language".hashCode());
        System.out.println("great:"+"great".hashCode());

        /**
         * 1.因为string 的hashcode 可能得出的值负整数，所以导致 跟numPartitions 取余的时候，就会导致出现
         */
    }
}
