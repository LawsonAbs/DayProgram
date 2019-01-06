package data_algorithm.chapter_3;

import org.apache.hadoop.io.Text;

import java.util.SortedMap;
import java.util.TreeMap;

public class TestSortedMap {
    public static void main(String[] args) {
        testText();
    }


    public static void testString(){
        SortedMap<Double,String> record = new TreeMap<Double, String>();
        record.put(1.0, "Hadoop");
        record.put(2.0, "MapReduce");
        record.put(3.0, "spark");

        for (String value : record.values()) {
            System.out.println("value : "+value);
        }
    }

    public static void testText() {
        SortedMap<Double,Text> record = new TreeMap<Double, Text>();
        record.put(1.0, new Text("Hadoop"));
        record.put(2.0, new Text("MapReduce"));
        record.put(3.0, new Text("spark"));

        for (Text value : record.values()) {
            System.out.println("value : "+value);
        }
    }


}
