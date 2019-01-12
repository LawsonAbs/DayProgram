package data_algorithm.chapter_6;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class MoveAvgMapper extends Mapper<LongWritable,Text,Stock,DoubleWritable>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split("[,]");
        emit(line,context);
    }
    public void emit(String []line,Context context) throws IOException, InterruptedException {
        if (line.length < 3) {
            System.out.println("invalid line! And it's value is "+ Arrays.toString(line));
        }
        String com_name = line[0];//the first para is company name
        String date = line[1];
        double price = Double.parseDouble(line[2]);
        Stock stock = new Stock(com_name,date,price);
        //context.write(new Text(com_name),stock);
        context.write(stock,new DoubleWritable(price));
    }
}

/*
1.输入数据的格式是
公司名，  日期 股价
com_name date price
现在的想法是，根据这个输入数据，求出移动平均值。

2.针对以下输入：
GOOG,2004-11-04,184.70
GOOG,2004-11-03,191.67
GOOG,2004-11-02,194.87
GOOG,2013-07-19,896.60
GOOG,2013-07-18,910.68
GOOG,2004-07-17,918.55

如果其设置的窗口值为2，则其输出应该是：

GOOG,2004-11-02,194.87  null
GOOG,2004-11-03,191.67  193.27
GOOG,2004-11-04,184.70  188.185
...



 */