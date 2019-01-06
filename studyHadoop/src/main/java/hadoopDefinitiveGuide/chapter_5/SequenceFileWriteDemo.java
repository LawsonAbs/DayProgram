package hadoopDefinitiveGuide.chapter_5;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.net.URI;


public class SequenceFileWriteDemo {
    public static final String[] DATA = {
            "One, two, buckle my shoe",
            "Three, four, shut the door",
            "Five, six, pick up sticks",
            "Seven, eight, lay them straight",
            "Nine, ten, a big fat hen"
    };

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("no args!");
            System.exit(1);
        }

        //step1: get uri, and get a Path instance through uri
        String uri = args[0];
        Path path = new Path(uri);

        //step 2:get a configuration
        Configuration conf = new Configuration();
        //step 3:get a FileSystem instance by uri and conf
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        System.out.println("path.name "+path.getName()+path.getParent());

        //step 4:get key and value
        IntWritable key = new IntWritable();
        Text value = new Text();

        //step 5:get a SequenceFile writer
        SequenceFile.Writer writer = null;

        //step 6: get the instance through fs,conf,path,key.Class,value.Class
        writer = SequenceFile.createWriter(fs, conf, path,key.getClass(),value.getClass());

        //step 7: write data into file
        for( int i = 0;i < 2;i++) {
            key.set(100 - i);
            value.set(DATA[i % DATA.length]);
            System.out.printf("[%s]\t%s\t%s\n",writer.getLength(),key,value);
            writer.append(key,value);
        }

        //step 8:close the writer
        IOUtils.closeStream(writer);
    }
}
/*
 1.The keys in the sequence file are integers,represented as IntWritable objects.
 2.The value are Text objects.
 3.getLength() method to discover the current position in the file
 */