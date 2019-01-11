package data_algorithm.chapter_5;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 01.这个PairOfWord 是一个<key-value>对中的 key。 这个key由两个部分组成：分别是leftStr 和 rightStr
 */
public class PairOfWord implements Writable,WritableComparable<PairOfWord>{

    private String leftStr;
    private String rightStr;

    public PairOfWord() {
    }

    public PairOfWord(String leftStr, String rightStr) {
        this.leftStr =leftStr;
        this.rightStr = rightStr;
    }

    public String getLeftStr() {
        return leftStr;
    }

    public void setLeftStr(String leftStr) {
        this.leftStr = leftStr;
    }

    public String getRightStr() {
        return rightStr;
    }

    public void setRightStr(String rightStr) {
        this.rightStr = rightStr;
    }
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.leftStr);
        out.writeUTF(this.rightStr);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.leftStr = in.readUTF();
        this.rightStr = in.readUTF();
    }

    @Override
    public int compareTo(PairOfWord o) {
        //以PairOfWord 的leftStr作为比较对象
        int compareValue = this.leftStr.compareTo(o.leftStr);
        if (compareValue == 0) {
            compareValue = this.rightStr.compareTo(o.getRightStr());
        }
        return compareValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PairOfWord that = (PairOfWord) o;

        if (leftStr != null ? !leftStr.equals(that.leftStr) : that.leftStr != null) return false;
        return rightStr != null ? rightStr.equals(that.rightStr) : that.rightStr == null;
    }

    @Override
    public int hashCode() {
        int result = leftStr != null ? leftStr.hashCode() : 0;
        //result = 31 * result + (rightStr != null ? rightStr.hashCode() : 0);
        //result = 31 * result ;
        System.out.println("result= "+result);
        return result;
    }

    @Override
    public String toString() {
        return "<" + leftStr + ","+ rightStr  + '>';
    }
}
