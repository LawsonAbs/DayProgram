package data_algorithm.chapter_1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DateTemperaturePair implements Writable,WritableComparable<DateTemperaturePair>{

    private final Text yearMonth = new Text();
    private final Text day = new Text();
    private final IntWritable temperature = new IntWritable();


    public DateTemperaturePair() {
    }

    public DateTemperaturePair(String yearMonth, String day, int temperature) {
        this.yearMonth.set(yearMonth);
        this.day.set(day);
        this.temperature.set(temperature);
    }

    public Text getYearMonthDay() {
        return new Text(yearMonth.toString()+day.toString());
    }

    public Text getYearMonth() {
        return yearMonth;
    }

    public Text getDay() {
        return day;
    }

    public IntWritable getTemperature() {
        return temperature;
    }

    public void setYearMonth(String yearMonthAsString) {
        yearMonth.set(yearMonthAsString);
    }

    public void setDay(String dayAsString) {
        day.set(dayAsString);
    }

    public void setTemperature(int temp) {
        temperature.set(temp);
    }

    public void write(DataOutput out) throws IOException {
        yearMonth.write(out);
        day.write(out);
        temperature.write(out);
    }

    public void readFields(DataInput in) throws IOException {
        yearMonth.readFields(in);
        day.readFields(in);
        temperature.readFields(in);
    }

    //控制每个键相同组的内部顺序【即二次排序的关键实现】
    public int compareTo(DateTemperaturePair pair) {

        //这里先对yearMonth 进行了一个比较，用这个来确定分组
        int compareValue = this.yearMonth.compareTo(pair.getYearMonth());

        if (compareValue == 0) {
            compareValue = temperature.compareTo(pair.getTemperature());
        }
       // return 1*compareValue;     // to sort descending
        return -1*compareValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DateTemperaturePair that = (DateTemperaturePair) o;
        if (temperature != null ? !temperature.equals(that.temperature) : that.temperature != null) {
            return false;
        }
        if (yearMonth != null ? !yearMonth.equals(that.yearMonth) : that.yearMonth != null) {
            return false;
        }

        return true;
    }

//    @Override
//    public int hashCode() {
//        int result = yearMonth != null ? yearMonth.hashCode() : 0;
//        result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
//        return result;
//    }

    /**
     * 1. 这个方法其实只是对这个DateTemperature对象进行输出
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DateTemperaturePair{yearMonth=");
        builder.append(yearMonth);
        builder.append(", day=");
        builder.append(day);
        builder.append(", temperature=");
        builder.append(temperature);
        builder.append("}");
        return builder.toString();
    }
}
