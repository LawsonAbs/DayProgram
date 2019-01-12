package data_algorithm.chapter_6;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class StockGroup extends WritableComparator {
    public StockGroup(){
        super(Stock.class,true);
    }

    @Override

    public int compare(WritableComparable a, WritableComparable b) {
        Stock s1 = (Stock) a;
        Stock s2 = (Stock) b;
        int compareVale = s1.getCom_name().compareTo(s2.getCom_name());
        return compareVale;
    }
}
