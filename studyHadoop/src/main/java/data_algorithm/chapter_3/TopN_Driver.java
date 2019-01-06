package data_algorithm.chapter_3;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class TopN_Driver  extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("exception...");
        }
        int returnStatus = ToolRunner.run(new TopN_Driver(), args);
        System.exit(returnStatus);
    }

    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("top.n","5");
        Job job = Job.getInstance(conf);
        job.setJarByClass(TopN_Driver.class);

        job.setMapperClass(TopN_Mapper.class);
        job.setReducerClass(TopN_Reducer.class);
        job.setJobName("top_n");

        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean status = job.waitForCompletion(true);
        return status ? 0: 1;// if status is true,return 0,else return 1
    }
}
