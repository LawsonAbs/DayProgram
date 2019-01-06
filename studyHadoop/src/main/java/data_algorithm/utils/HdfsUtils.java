package data_algorithm.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URISyntaxException;

public class HdfsUtils  {
    public static void deletePath(String path) throws URISyntaxException, IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://server4:9000");
        FileSystem fs = FileSystem.get(conf);
        //FileSystem fs = FileSystem.get(new URI(path), conf);
        Path deleteDir = new Path(path);
        //deletePath();
        boolean ifExist = fs.isDirectory(deleteDir);
        if (ifExist) {
            System.out.println(deleteDir.getName()+" have exists!");
            fs.delete(deleteDir,true);
            System.out.println(deleteDir.getName()+" have been deleted!");
        }
    }

}
