package data_algorithm.chapter_4.step_1_leftJoin;

import data_algorithm.utils.HdfsUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


/**
 * 1.MapReduce 1的实现目标是：将所有的user_id相同的 user 信息，已经卖出的product信息放在一起，并且是先放user的信息，再放product的信息
 * 例如，对于数据记录：
 * user_id ,location
 * 1       ,shanghai
 *
 * user_id ,product_id
 * 1       ,disk
 * 1       ,mouse
 * 那么期望得到的结果是：
 * user_id,location, list[product_id]
 * 1      ,shanghai, [disk,mouse]
 *
 */
public class LeftJoinDriver extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.exit(1);
        }
        HdfsUtils.deletePath(args[2]);
        int returnStatus = ToolRunner.run(new LeftJoinDriver(), args);
        System.exit(returnStatus);
    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(LeftJoinDriver.class);

        Path users = new Path(args[0]);
        Path transactions = new Path(args[1]);

        //利用MultipleInputs类可以使用多个Mapper 类；但是如果只有单个Mapper类，那么只需要使用Job.setMapperClass()即可
        MultipleInputs.addInputPath(job,
                users,
                TextInputFormat.class,
                UserMapper.class);

        MultipleInputs.addInputPath(job,
                transactions,
                TextInputFormat.class,
                TransactionMapper.class);

        //set output file path
        FileOutputFormat.setOutputPath(job,new Path(args[2]));

        job.setMapOutputKeyClass(User.class);
        job.setMapOutputValueClass(Text.class);
        job.setPartitionerClass(UserPartitioner.class);

        job.setGroupingComparatorClass(SecondarySortGroupComparator.class);
        job.setReducerClass(UserReducer.class);
        //job.setNumReduceTasks(3);
        boolean status = job.waitForCompletion(true);
        return status? 0: 1;
    }
}
