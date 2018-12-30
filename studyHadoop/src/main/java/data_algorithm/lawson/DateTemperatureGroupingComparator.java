package data_algorithm;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;


/**
 * 1.定制比较器（Comparator） 对 Reducer 值排序。
 * 2.它的功能是【完成排序】
 *
 */

public class DateTemperatureGroupingComparator extends WritableComparator{

    public DateTemperatureGroupingComparator() {
        super(DateTemperaturePair.class,true);
    }

    @Override
    public int compare(WritableComparable wc1, WritableComparable wc2) {
        System.out.println("start group comparator....");
        DateTemperaturePair pair = (DateTemperaturePair)wc1;
        //System.out.println(pair.getYearMonth().toString()+pair.getDay()+pair.getTemperature());
        DateTemperaturePair pair2 = (DateTemperaturePair)wc2;
        return pair.getYearMonth().compareTo(pair2.getYearMonth());
    }
}
