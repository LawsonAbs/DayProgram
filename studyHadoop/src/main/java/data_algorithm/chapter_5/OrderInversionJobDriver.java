package data_algorithm.chapter_5;

import data_algorithm.utils.HdfsUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class OrderInversionJobDriver  extends Configured implements Tool{
    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("args exception!");
            System.exit(1);
        }
        HdfsUtils.deletePath(args[1]);
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        Path inputPath = new Path(args[0]);
        Path outPath = new Path(args[1]);

        job.setJarByClass(OrderInversionJobDriver.class);


        job.setMapperClass(OrderInversionMapper.class);
        job.setReducerClass(OrderInversionReducer.class);

        job.setMapOutputKeyClass(PairOfWord.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setPartitionerClass(PairPartition.class);
        job.setGroupingComparatorClass(GroupPair.class);

        FileInputFormat.setInputPaths(job,inputPath);
        FileOutputFormat.setOutputPath(job, outPath);

        job.setNumReduceTasks(5);

        int status = job.waitForCompletion(true)?0:1;
        return status;
    }

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new OrderInversionJobDriver(), args);
    }
}
