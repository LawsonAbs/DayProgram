package data_algorithm.chapter_1;

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
        DateTemperaturePair pair2 = (DateTemperaturePair)wc2;
        //System.out.println("yearMonth: "+pair.getYearMonth()+" temperature: "+pair.getTemperature());
        boolean res = pair.getYearMonth().equals(pair2.getYearMonth());
        int status =  res ? 0 : 1;
        return status;  // 1 stand for a sole group, but the 0 stand for a united group
        //return status;
        //return pair.getYearMonth().compareTo(pair2.getYearMonth());
    }
}
