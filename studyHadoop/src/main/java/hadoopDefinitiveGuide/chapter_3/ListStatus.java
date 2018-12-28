package hadoopDefinitiveGuide.chapter_3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class ListStatus {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri),conf);

        Path[] paths = new Path[args.length];
        //get the instance of Path
        for(int i = 0;i< paths.length;i++) {
            paths[i] = new Path(args[i]);
        }

        //FileStatus: as the name, is a status of a file
        //FileStatus: Interface that represents the client side information for a file.
        //listStatus: Filter files/directories in the given list of paths using default path filter.
        FileStatus[] status = fs.listStatus(paths);
        for(int i = 0;i< status.length;i++) {
            System.out.println("accessTime: "+status[i].getAccessTime());
            System.out.println("owner: "+status[i].getOwner());
        }

        //stat2Paths: convert an array of FileStatus to an array of Path
        //in fact, the stat2Paths call the status[i].getPath()
        Path[] listedPaths = FileUtil.stat2Paths(status);
        for (Path p : listedPaths) {
            System.out.println(p);
        }
    }
}
