package data_algorithm.chapter_3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

public class TopN_Mapper  extends Mapper<LongWritable,Text,NullWritable,Text>{
    private SortedMap<Double, String> top10Cats = new TreeMap<Double, String>();
    private int N = 5;//默认的top 10

    /*
    1.对应每个mapper执行一次setup()函数
    2.建立“top N cat list” (top10Cats)
    3.setup(): Called once at the beginning of the task.
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        N = Integer.parseInt(conf.get("top.n"));//其中top.n 由作业的驱动器设置
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] tokens = value.toString().split(",");
        Double weight = Double.parseDouble(tokens[0]);
        System.out.println("weight: "+weight+" value: "+ value.toString());

        top10Cats.put(weight, value.toString());
        System.out.println("weight:"+weight+" "+top10Cats.get(weight));
        if (top10Cats.size() > N) {
            //删除频度最小的元素
            top10Cats.remove(top10Cats.firstKey());
        }
    }

    /**
     * 1. 发出 top N列表
     *  01. 使用一个键（通过调用NullWritable.get()获取，从而保证所有映射器的输出都将由一个归约器处理）
     * 2.cleanup(): Called once at the end of the task.
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //System.out.println("top10Cats size : "+top10Cats.size());
        //System.out.println(top10Cats.get(23.0));
//        System.out.println("top10Cats.firstKey : "+top10Cats.firstKey());
//        System.out.println("top10Cats.firstValue: "+ top10Cats.firstKey());
//        for (Double key : top10Cats.keySet()) {
//            System.out.println("key: "+key+"value: "+top10Cats.get(key).toString());
//        }


//        for (Text text : top10Cats.values()) {
//            System.out.println("key is: "+top10Cats.firstKey() +"value is: "+text.toString());
//

        for (String catAttributes : top10Cats.values()) {
            System.out.println("catAttributes: "+catAttributes);
            //context.write(NullWritable.get(),catAttributes);
            context.write(NullWritable.get(),new Text(catAttributes));
        }
    }
}
