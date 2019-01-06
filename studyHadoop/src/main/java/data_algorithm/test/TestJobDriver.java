package data_algorithm.test;

import data_algorithm.utils.HdfsUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class TestJobDriver extends  Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        HdfsUtils.deletePath(args[2]);
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(TestJobDriver.class);
        MultipleInputs.addInputPath(job,new Path(args[0]), TextInputFormat.class,TestMapper_1.class);
        MultipleInputs.addInputPath(job,new Path(args[1]), TextInputFormat.class,TestMapper_2.class);
        job.setReducerClass(TestReducer.class);

        job.setMapOutputValueClass(IntWritable.class);
        job.setMapOutputKeyClass(Text.class);

        FileOutputFormat.setOutputPath(job,new Path(args[2]));
        job.setOutputFormatClass(TextOutputFormat.class);
        boolean status = job.waitForCompletion(true);
        return status? 0: 1;
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("invalid args!");
        }
        ToolRunner.run(new TestJobDriver(), args);
    }
}
