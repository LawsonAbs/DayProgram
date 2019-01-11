package data_algorithm.chapter_5;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupPair extends WritableComparator {
    public GroupPair(){
        super(PairOfWord.class,true);
    }



    @Override

    public int compare(WritableComparable a, WritableComparable b) {
        System.out.println("");
        PairOfWord p1 = (PairOfWord) a;
        PairOfWord p2 = (PairOfWord) b;
        int status = p1.getLeftStr().compareTo(p2.getLeftStr());
        if (status == 0) {
            status = p1.getRightStr().compareTo(p2.getRightStr());
        }
        return status;
    }
}
