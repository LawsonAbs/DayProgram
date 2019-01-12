package data_algorithm.chapter_6;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Stock implements Writable, WritableComparable<Stock> {
    private String com_name;// company name
    private String date;//the date
    private double price;//stock price

    private double moveAvg;//move average


    public Stock() {
    }

    public Stock(String com_name, String date, double price) {
        this.com_name = com_name;
        this.date = date;
        this.price = price;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMoveAvg() {
        return moveAvg;
    }

    public void setMoveAvg(double moveAvg) {
        this.moveAvg = moveAvg;
    }

    @Override
    public int compareTo(Stock o) {
        int comp = this.com_name.compareTo(o.com_name);
        if (comp == 0) {
            comp = this.date.compareTo(o.date);
        }
        return comp;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(com_name);
        out.writeUTF(date);
        out.writeDouble(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.com_name = in.readUTF();
        this.date = in.readUTF();
        this.price = in.readDouble();
    }

    @Override
    public String toString() {
        return  com_name +
                ", " + date  +
                ", " + price +
                ", " + moveAvg ;
    }
}
