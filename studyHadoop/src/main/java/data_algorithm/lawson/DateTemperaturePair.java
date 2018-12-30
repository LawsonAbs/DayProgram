package data_algorithm;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DateTemperaturePair implements Writable,WritableComparable<DateTemperaturePair>{
    private Text yearMonth = new Text();
    private Text day = new Text();
    private IntWritable temperature = new IntWritable();

    public Text getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(Text yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Text getDay() {
        return day;
    }

    public void setDay(Text day) {
        this.day = day;
    }

    public IntWritable getTemperature() {
        return temperature;
    }

    public void setTemperature(IntWritable temperature) {
        this.temperature = temperature;
    }

    /**
     * 01. 控制键的排序顺序
     * 02. 将当前对象和传入的DateTemperaturePair 对象pair进行比较
     * @param pair
     * @return
     */
    public int compareTo(DateTemperaturePair pair) {
        int compareValue = this.yearMonth.compareTo(pair.getYearMonth());
        if (compareValue == 0) {
            compareValue = temperature.compareTo(pair.getTemperature());
        }
        return compareValue;
    }

    public void write(DataOutput out) throws IOException {

    }

    public void readFields(DataInput in) throws IOException {

    }
}
