package data_algorithm.chapter_5;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class OrderInversionReducer extends Reducer<PairOfWord,IntWritable,PairOfWord,DoubleWritable> {
    private double totalSum = 0; //表示某种 leftStr 的总数
    private String curString;
    /*@Override
    protected void reduce(PairOfWord key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        System.out.println("reducer start...");
        double partSum = 0;

        //可以保证(W,*)先到 => 然后就计算出了这个单词的上下文总次数
        //get the total sum by (W,*)
        for (IntWritable intWri : values) {
            if (key.getRightStr().equals("*")) {
                totalSum += intWri.get();
                curString = "*"; //当前的字符串还是 *
            }if (key.getRightStr().equals("W2")) {
                partSum += intWri.get();
                curString = "W2";
            }
        }
        System.out.println("totalSum = "+totalSum);
        System.out.println("partSum= "+ partSum);
        //说明是其他字符(W,W1)
        double res = partSum/totalSum;
        context.write(key, new DoubleWritable(res));
        //totalSum = 0;//reset 0 =>
    }*/

    @Override
    protected void reduce(PairOfWord key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        System.out.println("reducer start...");
        double partSum = 0;

        //可以保证(W,*)先到 => 然后就计算出了这个单词的上下文总次数
        //get the total sum by (W,*)
        if (key.getRightStr().equals("*")) {
            for (IntWritable intWri : values) {
                totalSum += intWri.get();
                curString = "*"; //当前的字符串还是 *
            }
        }
        else{
            for (IntWritable intWri : values) {
                partSum += intWri.get();
            }
            System.out.println("totalSum = "+totalSum);
            System.out.println("<"+key.getLeftStr()+","+key.getRightStr()+">"+" ,partSum= "+ partSum);
            //说明是其他字符(W,W1)
            double res = partSum/totalSum;
            context.write(key, new DoubleWritable(res));
            //totalSum = 0;//reset 0
            partSum = 0;//reset 0
        }
    }
}
