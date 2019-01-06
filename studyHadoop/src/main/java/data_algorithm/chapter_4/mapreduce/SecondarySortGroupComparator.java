package data_algorithm.chapter_4.mapreduce;

import edu.umd.cloud9.io.pair.PairOfStrings;
import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.RawComparator;


/** 
 * This is an plug-in class.
 * The SecondarySortGroupComparator class indicates how to compare the userIDs.
 * 
 * @author Mahmoud Parsian
 *
 */
public class SecondarySortGroupComparator 
	implements RawComparator<PairOfStrings> {

    /**
     *  Group only by userID
     *
     *
     *  1.但是现在的问题是：如果出现了如下两组数据，该怎么比较
     *  outputKey.set(u1, "1");    // set user_id
        outputValue.set("L", GA);  // set location_id

        2.而且具体调用哪一个 compare() 方法呢？
     */
    public int compare(PairOfStrings first, PairOfStrings second) {
       return first.getLeftElement().compareTo(second.getLeftElement());
    }

    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2 ) {
    	DataInputBuffer buffer = new DataInputBuffer();
    	PairOfStrings a = new PairOfStrings();
    	PairOfStrings b = new PairOfStrings();
      	try {
        	buffer.reset(b1, s1, l1);
        	a.readFields(buffer);
        	buffer.reset(b2, s2, l2);
        	b.readFields(buffer);
        	return compare(a,b);  
      	} 
      	catch(Exception ex) {
        	return -1;
      	}  
    }
}
