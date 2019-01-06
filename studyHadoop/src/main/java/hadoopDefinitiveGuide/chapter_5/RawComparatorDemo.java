package hadoopDefinitiveGuide.chapter_5;

import hadoopDefinitiveGuide.chapter_5.WritableDemo;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.WritableComparator;

import java.io.IOException;

public class RawComparatorDemo {
    public static void main(String[] args) throws IOException {
        RawComparator<IntWritable> comparator = WritableComparator.get(IntWritable.class);
        IntWritable w1 = new IntWritable(163);
        IntWritable w2 = new IntWritable(87);

        //because the w2 greater than w1 ,so return 1
        System.out.println("w1 = w2 ?"+comparator.compare(w1, w2));

        //but you can comparator use byte[]. As following code:
        byte[] b1 = WritableDemo.serialize(w1);
        byte[] b2 = WritableDemo.serialize(w2);

        System.out.println("w1 = w2 ?"+comparator.compare(b1,0,b1.length,b2,0,b2.length));
    }
}
