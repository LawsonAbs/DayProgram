package data_algorithm.chapter_6;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MoveAvgReducer extends Reducer<Stock,DoubleWritable,Stock,NullWritable> {
    private int window = 2;//the move windows number

    @Override
    protected void reduce(Stock key,Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double res =0 ;
        double priorVal = 0; // save the prior value
        int count = 0;
        String priorKey ="";
        for (DoubleWritable dw : values) {
            key.setMoveAvg(0); //针对这个地方我不是很清楚
            System.out.println("1------priorKey = "+priorKey+", key.Com_name = "+key.getCom_name());
            if (count != 0 && priorKey.equals(key.getCom_name())) {
                System.out.println("2------priorKey = "+priorKey+", key.Com_name = "+key.getCom_name());
                //System.out.println(key);
                res = (dw.get() + priorVal) / window;
                key.setMoveAvg(res);// set the final move average value
                //System.out.println("dw_1 = "+dw);
            }
            priorVal = dw.get();
            priorKey = key.getCom_name();
            count++;
            res = 0;//reset
            context.write(key,NullWritable.get());
        }
        count = 0;
    }
}
