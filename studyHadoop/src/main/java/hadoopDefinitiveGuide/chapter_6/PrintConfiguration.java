package hadoopDefinitiveGuide.chapter_6;

import org.apache.hadoop.conf.Configuration;

public class PrintConfiguration {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");
        conf.addResource("configuration-2.xml");
        System.out.println(conf.get("color"));

        //getInt: Get the value of the name property as an int.
        //如果不存在值，则直接返回 defaultValue
        System.out.println(conf.getInt("size",0));

        System.out.println("weight: "+conf.get("weight"));

        //variable expansion
        System.out.println("size-weight: "+conf.get("size-weight"));

        //
        System.setProperty("leng", "2");
        System.out.println("leng: "+conf.get("leng"));
    }
}
