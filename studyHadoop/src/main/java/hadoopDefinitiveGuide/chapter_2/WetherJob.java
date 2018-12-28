package hadoopDefinitiveGuide.chapter_2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;


public class WetherJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        Job wetherMax = Job.getInstance(configuration);

        wetherMax.setJarByClass(WetherJob.class);
        wetherMax.setMapperClass(WetherMapper.class);
        wetherMax.setReducerClass(WetherReducer.class);

        wetherMax.setMapOutputKeyClass(Text.class);
        wetherMax.setMapOutputValueClass(IntWritable.class);

        //because the input File have multiple,so there use the Path[]
        Path[] inputFile = new Path[]{
                new Path("hdfs://192.168.211.4:9000/input/1901"),
                new Path("hdfs://192.168.211.4:9000/input/1902")};

        FileInputFormat.setInputPaths(wetherMax,inputFile);
        FileOutputFormat.setOutputPath(wetherMax,new Path("hdfs://192.168.211.4:9000/output/wether"));

        wetherMax.waitForCompletion(true);
    }
}
