package data_algorithm.chapter_4.step_2_leftJoin;

import data_algorithm.utils.HdfsUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class SecondJobDriver extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.exit(1);
        }
        HdfsUtils.deletePath(args[1]);
        int returnStatus = ToolRunner.run(new SecondJobDriver(), args);
        System.exit(returnStatus);
    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(SecondJobDriver.class);
        Path input = new Path(args[0]);
        Path finalOutput = new Path(args[1]);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setReducerClass(SecondReducer.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setInputFormatClass(TextInputFormat.class);
        FileInputFormat.setInputPaths(job, input);
        FileOutputFormat.setOutputPath(job,finalOutput);

        job.setMapperClass(SecondMapper.class);
        job.setReducerClass(SecondReducer.class);

        boolean status = job.waitForCompletion(true);
        return status? 0: 1;
    }
}
