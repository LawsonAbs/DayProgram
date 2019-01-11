package data_algorithm.chapter_5;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class OrderInversionMapper extends Mapper<LongWritable,Text,PairOfWord,IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split(" ");
        System.out.println(Arrays.toString(line));
        for (int i = 0;i< line.length;i++) {
            String curWord = line[i];
            System.out.println("curWord: "+curWord);
            if (i - 2 >= 0) {
                System.out.println(curWord+","+line[i-2]);
                this.emit(curWord,line[i-2],context);
            }
            if (i - 1 >= 0) {
                System.out.println(curWord+","+line[i-1]);
                this.emit(curWord,line[i-1],context);
            }

            if (i + 1 < line.length) {
                System.out.println(curWord+","+line[i+1]);
                this.emit(curWord,line[i+1],context);
            }

            if (i + 2 < line.length) {
                System.out.println(curWord+","+line[i+2]);
                this.emit(curWord,line[i+2],context);
            }
        }
    }

    public void emit(String curWord,String env_word,Context context) throws IOException, InterruptedException {
        PairOfWord genePow = new PairOfWord(curWord,"*");
        PairOfWord curPow = new PairOfWord(curWord, env_word);

        context.write(genePow,new IntWritable(1));
        context.write(curPow,new IntWritable(1));
        //System.out.println("......");
    }
}
