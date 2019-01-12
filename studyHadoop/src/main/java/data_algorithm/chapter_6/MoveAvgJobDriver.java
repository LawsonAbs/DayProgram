package data_algorithm.chapter_6;

import data_algorithm.utils.HdfsUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class MoveAvgJobDriver extends Configured  implements  Tool {
    @Override
    public int run(String[] args) throws Exception {
        HdfsUtils.deletePath(args[1]);
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        Path inPath = new Path(args[0]);
        Path outPath = new Path(args[1]);
        job.setJarByClass(MoveAvgJobDriver.class);
        FileInputFormat.setInputPaths(job,inPath);
        FileOutputFormat.setOutputPath(job,outPath);

        job.setMapperClass(MoveAvgMapper.class);
        job.setReducerClass(MoveAvgReducer.class);

        job.setMapOutputKeyClass(Stock.class);
        job.setMapOutputValueClass(DoubleWritable.class);
        job.setGroupingComparatorClass(StockGroup.class);
        int status = job.waitForCompletion(true) ? 0:1;
        return status;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("parameter is not valid!");
            System.exit(1);
        }
        ToolRunner.run(new MoveAvgJobDriver(),args);
    }
}

